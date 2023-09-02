package com.dar.coupon.system.project2.service;

import com.dar.coupon.system.project2.models.NameAndId;
import com.dar.coupon.system.project2.repositories.CompanyRepository;
import com.dar.coupon.system.project2.repositories.CouponRepository;
import com.dar.coupon.system.project2.repositories.CustomerRepository;
import com.dar.coupon.system.project2.repositories.NameAndIdRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CouponRepository couponRepository;

    @Autowired
    protected NameAndIdRepository nameAndIdRepository;

    public abstract NameAndId login(String email, String password);


}