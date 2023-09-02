package com.dar.coupon.system.project2.repositories;


import com.dar.coupon.system.project2.beans.Customer;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);
    Customer findByEmailAndPassword(String email, String password);

}

