package com.sa.clothingstore.service.user.impl;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.dto.request.user.UserUpdateRequest;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.user.Role;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.repository.user.UserRepository;
import com.sa.clothingstore.service.email.EmailService;
import com.sa.clothingstore.service.user.factory.StaffServiceFactory;
import com.sa.clothingstore.service.user.service.StaffService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StaffServiceImp implements StaffService {

    private final StaffServiceFactory staffServiceFactory;
    private final UserRepository userRepository;
    private final EmailService emailService;
    @Override
    public List<User> getAllUsersByRole(Integer role){
        return staffServiceFactory.getAllUsers(role);
    }
    @Override
    @Transactional
    public void createUser(UserRequest userRequest, Role role, MultipartFile image) throws IOException {
        userRepository.save(staffServiceFactory.create(userRequest, role, image));
        emailService.sendUserCredential(userRequest.getEmail(), userRequest.getFullName(), userRequest.getPassword());
    }

    @Override
    @Transactional
    public void updateUser(UUID userId, UserUpdateRequest userRequest, MultipartFile image) throws IOException {
        userRepository.save(staffServiceFactory.update(userId, userRequest, image));
    }

    @Override
    @Transactional
    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new BusinessException(APIStatus.USER_NOT_FOUND));
        userRepository.delete(user);
    }

}
