package com.dar.coupon.system.project2.controllers;

import com.dar.coupon.system.project2.dto.CompanyRegisterReq;
import com.dar.coupon.system.project2.dto.CustomerRegisterReq;
import com.dar.coupon.system.project2.dto.LoginReqDto;
import com.dar.coupon.system.project2.dto.LoginResDto;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import com.dar.coupon.system.project2.login.ClientType;
import com.dar.coupon.system.project2.login.LoginManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/auth")
@CrossOrigin
public class AuthController {
    @Autowired
   private LoginManagerService loginManagerService;

    @PostMapping("/register-company")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void registerCompany(@RequestBody CompanyRegisterReq companyRegisterReq) throws CouponSystemExceptions {
        loginManagerService.CompanyRegister(companyRegisterReq);
    }

    @PostMapping("/register-customer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void registerCustomer(@RequestBody CustomerRegisterReq customerRegisterReq) throws CouponSystemExceptions {
        loginManagerService.CustomerRegister(customerRegisterReq);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto login(@RequestBody LoginReqDto loginReqDto) throws CouponSystemExceptions {
            String email = loginReqDto.getEmail();
            String password = loginReqDto.getPassword();
            ClientType clientType = ClientType.valueOf(loginReqDto.getClientType());
        return loginManagerService.login(email, password,clientType);
    }



}
