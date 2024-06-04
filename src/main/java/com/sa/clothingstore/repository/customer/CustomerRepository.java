package com.sa.clothingstore.repository.customer;

import com.sa.clothingstore.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findByPhone(String phone);
    @Query("SELECT c FROM Customer c WHERE LOWER(c.phone) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Customer> searchCustomer(String keyword);
}