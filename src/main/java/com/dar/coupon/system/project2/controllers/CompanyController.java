package com.dar.coupon.system.project2.controllers;

import com.dar.coupon.system.project2.beans.Category;
import com.dar.coupon.system.project2.beans.Company;
import com.dar.coupon.system.project2.beans.Coupon;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import com.dar.coupon.system.project2.exceptions.ErrMsg;
import com.dar.coupon.system.project2.login.ClientType;
import com.dar.coupon.system.project2.security.TokenService;
import com.dar.coupon.system.project2.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private TokenService tokenService;

    @GetMapping("coupons")
    public Coupon[] getAllCoupons(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        int companyId = tokenService.idFromUuid(token);
        return companyService.getCompanyCoupons(companyId).toArray(new Coupon[0]);
    }

    @GetMapping("/coupons/{couponId}")
    public Coupon getSingleCoupon(@RequestHeader(value = "Authorization") UUID token, @PathVariable int couponId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        int companyId = tokenService.idFromUuid(token);
        return companyService.getSingle(companyId, couponId);
    }

    @GetMapping("coupons/category")
    public Coupon[] getCouponsByCategory(@RequestHeader(value = "Authorization") UUID token, @RequestParam String val) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        int companyId = tokenService.idFromUuid(token);
        return companyService.getCompanyCoupons(companyId, Category.valueOf(val)).toArray(new Coupon[0]);
    }

    @GetMapping("coupons/price")
    public Coupon[] getCouponsByPrice(@RequestHeader(value = "Authorization") UUID token, @RequestParam double val) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        int companyId = tokenService.idFromUuid(token);
        return companyService.getCompanyCoupons(companyId, val).toArray(new Coupon[0]);
    }

    @GetMapping("details")
    public Company companyDetails(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        int companyId = tokenService.idFromUuid(token);
        return companyService.getCompanyDetails(companyId);
    }

    @PostMapping("coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public Coupon addCoupon(@RequestHeader(value = "Authorization") UUID token, @RequestBody Coupon coupon) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        int companyId = tokenService.idFromUuid(token);
        return companyService.addCoupon(companyId, coupon);
    }

    @PutMapping("/coupons/{couponId}")
    public Coupon updateCoupon(@RequestHeader(value = "Authorization") UUID token, @PathVariable int couponId, @RequestBody Coupon coupon) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        int companyId = tokenService.idFromUuid(token);
        return companyService.updateCoupon(companyId, couponId, coupon);
    }

    @DeleteMapping("coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@RequestHeader(value = "Authorization") UUID token, @PathVariable int couponId) throws CouponSystemExceptions {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemExceptions(ErrMsg.NOT_AUTHORIZED);
        }
        int companyId = tokenService.idFromUuid(token);
        companyService.deleteCoupon(companyId, couponId);
    }


}
