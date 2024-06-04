package com.sa.clothingstore.service.user.impl;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.user.Role;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.repository.user.UserRepository;
import com.sa.clothingstore.service.user.factory.StaffServiceFactory;
import com.sa.clothingstore.service.user.service.StaffService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StaffServiceImp implements StaffService {

    private final StaffServiceFactory staffServiceFactory;
    private final UserRepository userRepository;
    @Override
    public List<User> getAllUsersByRole(Integer role){
        return staffServiceFactory.getAllUsers(role);
    }
    @Override
    public void createUser(UserRequest userRequest, Role role) throws IOException {
        userRepository.save(staffServiceFactory.create(userRequest, role));
    }

    @Override
    public void updateUser(UUID userId, UserRequest userRequest) throws IOException {
        userRepository.save(staffServiceFactory.update(userId, userRequest));
    }

    @Override
    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new BusinessException(APIStatus.USER_NOT_FOUND));
        user.setEnabled(false);
        userRepository.save(user);
    }

}
