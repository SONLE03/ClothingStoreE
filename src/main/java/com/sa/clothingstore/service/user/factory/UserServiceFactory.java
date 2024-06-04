package com.sa.clothingstore.service.user.factory;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.model.attribute.Image;
import com.sa.clothingstore.model.user.Role;
import com.sa.clothingstore.model.user.Status;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.repository.attribute.ImageRepository;
import com.sa.clothingstore.repository.user.UserRepository;
import com.sa.clothingstore.service.user.service.UserDetailService;
import com.sa.clothingstore.util.fileUpload.FileUploadImp;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class UserServiceFactory  {
    private final PasswordEncoder passwordEncoder;
    private final ImageRepository imageRepository;
    private final UserDetailService userDetailService;
    private final UserRepository userRepository;
    private final FileUploadImp fileUploadImp;
    protected abstract User createUser(User user, UserRequest userRequest);

    protected abstract User updateUser(User user, UserRequest userRequest);
    protected abstract List<User> getAllUsersByRole(Integer role);

    @Transactional
    public User create(UserRequest userRequest, Role role) throws IOException {
        userRepository.findByEmail(userRequest.getEmail()).ifPresent(user -> {
            throw new BusinessException(APIStatus.EMAIL_ALREADY_EXISTED);
        });
        userRepository.findByPhone(userRequest.getPhone()).ifPresent(user -> {
            throw new BusinessException(APIStatus.PHONE_ALREADY_EXISTED);
        });
        User user = User.builder()
                .fullName(userRequest.getFullName())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .dateOfBirth(userRequest.getDateOfBirth())
                .enabled(userRequest.getEnable() == Status.ACTIVE.ordinal())
                .role(role)
                .build();
        user.setCommonCreate(userDetailService.getIdLogin());

        var userImage = userRequest.getImage();
        if(userImage != null){
            BufferedImage bi = ImageIO.read(userImage.getInputStream());
            if (bi == null) {
                throw new BusinessException(APIStatus.IMAGE_NOT_FOUND);
            }
            Map result = fileUploadImp.upload(userImage, "avatars");
            Image image =  Image.builder()
                    .name((String) result.get("original_filename"))
                    .url((String) result.get("url"))
                    .cloudinaryId((String) result.get("public_id"))
                    .build();
            imageRepository.save(image);
            user.setImage(image);
        }
        return createUser(user, userRequest);
    }

    public User update(UUID userId, UserRequest userRequest) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(() ->{
                throw new BusinessException(APIStatus.USER_NOT_FOUND);
            }
        );
        String oldEmail = user.getEmail();
        String newEmail = userRequest.getEmail();
        String oldPhone = user.getPhone();
        String newPhone = userRequest.getPhone();
        userRepository.findByEmail(newEmail).ifPresent(u -> {
            if(!oldEmail.equals(newEmail)) {
                throw new BusinessException(APIStatus.EMAIL_ALREADY_EXISTED);
            }
        });
        userRepository.findByPhone(newPhone).ifPresent(u -> {
            if(!oldPhone.equals(newPhone)) {
                throw new BusinessException(APIStatus.PHONE_ALREADY_EXISTED);
            }
        });
        user.setEmail(userRequest.getEmail());
        user.setFullName(userRequest.getFullName());
        user.setPhone(userRequest.getPhone());
        user.setDateOfBirth(userRequest.getDateOfBirth());
        user.setEnabled(userRequest.getEnable() == Status.ACTIVE.ordinal());

        var userImage = userRequest.getImage();
        if(userImage != null){
            fileUploadImp.delete(user.getImage().getCloudinaryId());
            BufferedImage bi = ImageIO.read(userImage.getInputStream());
            if (bi == null) {
                throw new BusinessException(APIStatus.IMAGE_NOT_FOUND);
            }
            Map result = fileUploadImp.upload(userImage, "avatars");
            Image image =  Image.builder()
                    .name((String) result.get("original_filename"))
                    .url((String) result.get("url"))
                    .cloudinaryId((String) result.get("public_id"))
                    .build();
            imageRepository.save(image);
            user.setImage(image);
        }
        user.setCommonUpdate(userDetailService.getIdLogin());

        return updateUser(user, userRequest);
    }

    public List<User> getAllUsers(Integer role){
        return userRepository.findByRole(Role.convertIntegerToRole(role))
                .orElse(null);
    }
}
