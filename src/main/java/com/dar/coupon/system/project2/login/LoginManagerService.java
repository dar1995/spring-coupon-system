package com.dar.coupon.system.project2.login;
import com.dar.coupon.system.project2.dto.CompanyRegisterReq;
import com.dar.coupon.system.project2.dto.CustomerRegisterReq;
import com.dar.coupon.system.project2.dto.LoginResDto;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;


public interface LoginManagerService {
    void CompanyRegister(CompanyRegisterReq companyRegisterReq) throws CouponSystemExceptions;
    void  CustomerRegister(CustomerRegisterReq customerRegisterReq) throws  CouponSystemExceptions;

   LoginResDto login(String email, String password, ClientType clientType) throws CouponSystemExceptions;

}
