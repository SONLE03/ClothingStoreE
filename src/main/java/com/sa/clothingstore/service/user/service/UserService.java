package com.sa.clothingstore.service.user.service;

import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.model.user.Role;
import com.sa.clothingstore.model.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsersByRole(Integer role);
    void createUser(UserRequest userRequest, Role role, MultipartFile image) throws IOException;
    void updateUser(UUID userId, UserRequest userRequest, MultipartFile image) throws IOException;
    void deleteUser(UUID userId);
}
