package com.sa.clothingstore.service.user.impl;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.user.AddressRequest;
import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.dto.request.user.UserUpdateRequest;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.model.user.customer.Address;
import com.sa.clothingstore.model.user.customer.Customer;
import com.sa.clothingstore.model.user.Role;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.repository.customer.AddressRepository;
import com.sa.clothingstore.repository.customer.CustomerRepository;
import com.sa.clothingstore.repository.user.UserRepository;
import com.sa.clothingstore.service.user.factory.CustomerServiceFactory;
import com.sa.clothingstore.service.user.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class CustomerServiceImp implements CustomerService {
    private final CustomerServiceFactory customerServiceFactory;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    @Override
    public List<Address> getAddressByCustomer(UUID customerId) {
        if(!customerRepository.existsById(customerId)){
            throw new BusinessException(APIStatus.CUSTOMER_NOT_FOUND);
        }
        return addressRepository.getAllCustomerAddress(customerId);
    }

    @Override
    @Transactional
    public void createAddress(UUID userId, AddressRequest addressRequest) {
        Customer customer = customerRepository.findById(userId).orElseThrow(
                () -> new BusinessException(APIStatus.CUSTOMER_NOT_FOUND));
        Address address = Address.builder()
                .postalCode(addressRequest.getPostalCode())
                .ward(addressRequest.getWard())
                .specificAddress(addressRequest.getSpecificAddress())
                .district(addressRequest.getDistrict())
                .province(addressRequest.getProvince())
                .phone(addressRequest.getPhone())
                .isDefault(addressRequest.isDefault())
                .customer(customer)
                .build();
        addressRepository.save(address);
    }
    @Override
    @Transactional
    public void updateAddress(UUID customerId, UUID addressId, AddressRequest addressRequest){
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new BusinessException(APIStatus.CUSTOMER_NOT_FOUND));
        if(!customerRepository.existsById(customerId)){
            throw new BusinessException(APIStatus.CUSTOMER_NOT_FOUND);
        }
        if(!addressRepository.existsAddressForCustomer(customerId,addressId)){
            throw new BusinessException(APIStatus.CUSTOMER_ADDRESS_NOT_FOUND);
        }
        address.setPostalCode(addressRequest.getPostalCode());
        address.setWard(addressRequest.getWard());
        address.setSpecificAddress(addressRequest.getSpecificAddress());
        address.setDistrict(addressRequest.getDistrict());
        address.setProvince(addressRequest.getProvince());
        address.setPhone(addressRequest.getPhone());
        address.setDefault(addressRequest.isDefault());
        addressRepository.save(address);
    }

    @Override
    public void deleteAddress(UUID addressId) {
        if(!addressRepository.existsById(addressId)){
            throw new BusinessException(APIStatus.ADDRESS_NOT_FOUND);
        }
        addressRepository.deleteById(addressId);
    }

    @Override
    public List<User> getAllUsersByRole(Integer role) {
        return customerServiceFactory.getAllUsers(role);
    }

    @Override
    @Transactional
    public void createUser(UserRequest userRequest, Role role, MultipartFile image) throws IOException {
        userRepository.save(customerServiceFactory.create(userRequest, role, image));
    }

    @Override
    @Transactional
    public void updateUser(UUID userId, UserUpdateRequest userRequest, MultipartFile image) throws IOException {
        userRepository.save(customerServiceFactory.update(userId, userRequest, image));
    }

    @Override
    @Transactional
    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new BusinessException(APIStatus.USER_NOT_FOUND));
        user.setEnabled(false);
        userRepository.save(user);
    }
}
