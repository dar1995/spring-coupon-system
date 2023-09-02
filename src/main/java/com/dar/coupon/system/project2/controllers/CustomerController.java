package com.dar.coupon.system.project2.controllers;

import com.dar.coupon.system.project2.beans.Category;
import com.dar.coupon.system.project2.beans.Coupon;
import com.dar.coupon.system.project2.beans.Customer;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import com.dar.coupon.system.project2.exceptions.ErrMsg;
import com.dar.coupon.system.project2.login.ClientType;
import com.dar.coupon.system.project2.security.TokenService;
import com.dar.coupon.system.project2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
   private CustomerService customerService;
    @Autowired
    private TokenService tokenService;

    @GetMapping("coupons")
    public Coupon[] getAllCoupons(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        int customerId = tokenService.idFromUuid(token);
        return customerService.getCustomerCoupons(customerId).toArray(new Coupon[0]);
    }

    @GetMapping("coupons/{couponId}")
    public Coupon getSingleCoupon(@RequestHeader(value = "Authorization") UUID token, @PathVariable int couponId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        int customerId = tokenService.idFromUuid(token);
        return customerService.getSingle(customerId, couponId);
    }

    @GetMapping("coupons/category")
    public Coupon[] getCouponsByCategory(@RequestHeader(value = "Authorization") UUID token, @RequestParam String val) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        int customerId = tokenService.idFromUuid(token);
        return customerService.getCustomerCoupons(customerId, Category.valueOf(val)).toArray(new Coupon[0]);
    }

    @GetMapping("coupons/price")
    public Coupon[] getCouponsByPrice(@RequestHeader(value = "Authorization") UUID token, @RequestParam double val) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        int customerId = tokenService.idFromUuid(token);
        return customerService.getCustomerCoupons(customerId, val).toArray(new Coupon[0]);
    }

    @GetMapping("details")
    public Customer customerDetails(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        int customerId = tokenService.idFromUuid(token);
        return customerService.getCustomerDetails(customerId);
    }

    @PostMapping("coupons/{couponId}")
    public Coupon purchaseCoupon(@RequestHeader(value = "Authorization") UUID token, @PathVariable int couponId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        int customerId = tokenService.idFromUuid(token);
        return customerService.purchaseCoupon(customerId, couponId);
    }

}
