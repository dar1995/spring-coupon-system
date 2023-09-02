package com.dar.coupon.system.project2.repositories;

import com.dar.coupon.system.project2.beans.Company;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByName(String name);

    boolean existsByEmail(String email);

    Company findByEmailAndPassword(String email, String password);


}