package com.dar.coupon.system.project2.service;

import com.dar.coupon.system.project2.repositories.CompanyRepository;
import com.dar.coupon.system.project2.repositories.CouponRepository;
import com.dar.coupon.system.project2.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CouponRepository couponRepository;

    public abstract int login(String email, String password);


}