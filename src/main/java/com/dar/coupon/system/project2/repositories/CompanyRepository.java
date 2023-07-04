package com.dar.coupon.system.project2.repositories;

import com.dar.coupon.system.project2.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByName(String name);

    boolean existsByEmail(String email);

    @Query(value = "SELECT coupon_system.companies.id FROM coupon_system.companies\n" +
            "where companies.email = ? and companies.password = ?", nativeQuery = true)
    int findIdByEmailAndPassword(String email, String password);
}