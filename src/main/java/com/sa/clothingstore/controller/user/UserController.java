package com.sa.clothingstore.controller.user;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.user.ChangePasswordRequest;
import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.model.user.Role;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.service.customer.CustomerService;
import com.sa.clothingstore.service.user.service.*;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RequestMapping(APIConstant.USERS)
@RestController
@AllArgsConstructor
public class UserController {
    private final AdminService adminService;
    private final CustomerService customerService;
    private final StaffService staffService;
    private final UserDetailService userDetailService;
    private final Map<Integer, UserService> roleToServiceMap;

    @PostConstruct
    private void initRoleToServiceMap() {
        roleToServiceMap.put(0, adminService);
        roleToServiceMap.put(1, staffService);
    }
    @GetMapping(APIConstant.GET_ALL)
    public List<User> getAllUsersByRole(@PathVariable Integer role){
        return roleToServiceMap.get(role).getAllUsersByRole(role);
    }

    @GetMapping(APIConstant.USER_ID)
    public User getUserById(@PathVariable UUID userId){
        return userDetailService.getProfile(userId);
    }

    @PostMapping(APIConstant.CHANGE_PASSWORD)
    @PreAuthorize("permitAll()")
    @ResponseStatus(HttpStatus.OK)
    public String changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, @PathVariable String email){
       return userDetailService.changePassword(changePasswordRequest, email);
    }
    @PostMapping(APIConstant.VERIFY_OTP)
    @ResponseStatus(HttpStatus.OK)
    public String verifyOtp(@PathVariable Integer otp, @PathVariable String email){
        return userDetailService.verifyOtp(otp, email);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody @Valid UserRequest userRequest) throws IOException {
        int userRole = userRequest.getRole();
        roleToServiceMap.get(userRole).createUser(userRequest, Role.convertIntegerToRole(userRole));
        return "User created successfully";
    }
    @PutMapping(APIConstant.USER_ID)
    @ResponseStatus(HttpStatus.OK)
    public String updateUser(@PathVariable UUID userId, @RequestBody @Valid UserRequest userRequest) throws IOException {
        roleToServiceMap.get(userDetailService.getRoleById(userId)).updateUser(userId, userRequest);
        return "User modified successfully";
    }
    @DeleteMapping(APIConstant.USER_ID)
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable UUID userId){
        roleToServiceMap.get(userDetailService.getRoleById(userId)).deleteUser(userId);
        return "User was deleted successfully";
    }
}
