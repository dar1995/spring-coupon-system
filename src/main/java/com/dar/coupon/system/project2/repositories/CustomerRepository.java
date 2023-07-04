package com.dar.coupon.system.project2.repositories;

import com.dar.coupon.system.project2.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);

    @Query(value = "SELECT coupon_system.customers.id  from coupon_system.customers where\n" +
            "customers.email = ? and customers.password = ?", nativeQuery = true)
    int findIdByEmailAndPassword(String email, String password);
}
