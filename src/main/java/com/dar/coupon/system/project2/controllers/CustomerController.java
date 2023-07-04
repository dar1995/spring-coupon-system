package com.dar.coupon.system.project2.controllers;

import com.dar.coupon.system.project2.beans.Category;
import com.dar.coupon.system.project2.beans.Coupon;
import com.dar.coupon.system.project2.beans.Customer;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import com.dar.coupon.system.project2.exceptions.ErrMsg;
import com.dar.coupon.system.project2.login.ClientType;
import com.dar.coupon.system.project2.models.CouponToObject;
import com.dar.coupon.system.project2.security.TokenService;
import com.dar.coupon.system.project2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customer/{customerId}")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    TokenService tokenService;

    @GetMapping
    public CouponToObject getAllCoupons(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return new CouponToObject(customerService.getCustomerCoupons(customerId));
    }

    @GetMapping("{couponId}")
    public Coupon getSingleCoupon(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId, @PathVariable int couponId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return customerService.getSingle(customerId, couponId);
    }

    @GetMapping("by-category")
    public CouponToObject getCouponsByCategory(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId, @RequestParam String val) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return new CouponToObject(customerService.getCustomerCoupons(customerId, Category.valueOf(val)));
    }

    @GetMapping("by-price")
    public CouponToObject getCouponsByPrice(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId, @RequestParam double val) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return new CouponToObject(customerService.getCustomerCoupons(customerId, val));
    }

    @GetMapping("details")
    public Customer customerDetails(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return customerService.getCustomerDetails(customerId);
    }

    @PostMapping("{couponId}")
    public void purchaseCoupon(@RequestHeader(value = "Authorization") UUID token, @PathVariable int customerId, @PathVariable int couponId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        customerService.purchaseCoupon(customerId, couponId);
    }

}
