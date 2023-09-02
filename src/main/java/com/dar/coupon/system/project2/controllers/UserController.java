package com.dar.coupon.system.project2.controllers;

import com.dar.coupon.system.project2.beans.Category;
import com.dar.coupon.system.project2.beans.Coupon;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import com.dar.coupon.system.project2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("coupons")
    public Coupon[] getAllCoupons(){
        return userService.getAllCoupons().toArray(new Coupon[0]);
    }

    @GetMapping("coupons-category")
    public Coupon[] getCouponsByCategory(@RequestParam String category){
        return userService.GetCouponsByCategory(Category.valueOf(category)).toArray(new Coupon[0]);
    }

    @GetMapping("coupons-price")
    public Coupon[] getCouponsByPrice(@RequestParam double price){
        return userService.GetCouponsByPrice(price).toArray(new Coupon[0]);
    }

    @GetMapping("coupons-category-price")
    public Coupon[] getCouponsByCategoryAndPrice(@RequestParam String category, @RequestParam double price){
        return userService.GetCouponsByCategoryAndPrice(Category.valueOf(category), price).toArray(new Coupon[0]);
    }

    @GetMapping("coupons/{couponId}")
    public Coupon getSingleCoupon(@PathVariable int couponId) throws CouponSystemExceptions {
        return userService.getSingleCoupon(couponId);
    }

    @GetMapping("coupons/random")
    public Coupon[] getRandCoupons(){
        return userService.getRandCoupons().toArray(new Coupon[0]);
    }
}
