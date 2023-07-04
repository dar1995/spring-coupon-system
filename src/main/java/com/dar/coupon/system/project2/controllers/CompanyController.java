package com.dar.coupon.system.project2.controllers;

import com.dar.coupon.system.project2.beans.Category;
import com.dar.coupon.system.project2.beans.Company;
import com.dar.coupon.system.project2.beans.Coupon;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import com.dar.coupon.system.project2.exceptions.ErrMsg;
import com.dar.coupon.system.project2.login.ClientType;
import com.dar.coupon.system.project2.models.CouponToObject;
import com.dar.coupon.system.project2.security.TokenService;
import com.dar.coupon.system.project2.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/company/{companyId}")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @Autowired
    TokenService tokenService;

    @GetMapping
    public CouponToObject getAllCoupons(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)){
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return new CouponToObject(companyService.getCompanyCoupons(companyId));
    }

    @GetMapping("{couponId}")
    public Coupon getSingleCoupon(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId, @PathVariable int couponId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)){
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return companyService.getSingle(companyId, couponId);
    }

    @GetMapping("by-category")
    public CouponToObject getCouponsByCategory(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId, @RequestParam String val) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)){
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return new CouponToObject(companyService.getCompanyCoupons(companyId, Category.valueOf(val)));
    }

    @GetMapping("by-price")
    public CouponToObject getCouponsByPrice(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId, @RequestParam double val) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)){
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return new CouponToObject(companyService.getCompanyCoupons(companyId, val));
    }

    @GetMapping("details")
    public Company companyDetails(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)){
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        return companyService.getCompanyDetails(companyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId, @RequestBody Coupon coupon) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)){
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        companyService.addCoupon(companyId, coupon);
    }

    @PutMapping("/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCoupon(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId, @PathVariable int couponId,@RequestBody Coupon coupon ) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)){
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        companyService.updateCoupon(companyId, couponId, coupon);
    }

    @DeleteMapping("/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@RequestHeader(value = "Authorization") UUID token, @PathVariable int companyId, @PathVariable int couponId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)){
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        companyService.deleteCoupon(companyId, couponId);
    }


}
