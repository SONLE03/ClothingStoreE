package com.sa.clothingstore.service.user.impl;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.dto.request.user.UserUpdateRequest;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.model.user.Role;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.repository.user.UserRepository;
import com.sa.clothingstore.service.email.EmailService;
import com.sa.clothingstore.service.user.factory.AdminServiceFactory;
import com.sa.clothingstore.service.user.service.AdminService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AdminServiceImp implements AdminService {
    private final AdminServiceFactory adminServiceFactory;
    private final UserRepository userRepository;


    @Override
    public List<User> getAllUsersByRole(Integer role){
        return adminServiceFactory.getAllUsers(role);
    }
    @Override
    @Transactional
    public void createUser(UserRequest userRequest, Role role, MultipartFile image) throws IOException {
        userRepository.save(adminServiceFactory.create(userRequest, role, image));
    }

    @Override
    @Transactional
    public void updateUser(UUID userId, UserUpdateRequest userRequest, MultipartFile image) throws IOException {
        userRepository.save(adminServiceFactory.update(userId, userRequest, image));
    }

    @Override
    @Transactional
    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new BusinessException(APIStatus.USER_NOT_FOUND));
//        user.setEnabled(false);
//        userRepository.save(user);
        userRepository.delete(user);
    }

}
