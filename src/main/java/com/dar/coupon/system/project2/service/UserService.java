package com.dar.coupon.system.project2.service;

import com.dar.coupon.system.project2.beans.Category;
import com.dar.coupon.system.project2.beans.Coupon;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;

import java.util.List;

public interface UserService {
    List<Coupon> getAllCoupons();
    List<Coupon> GetCouponsByCategory(Category category);
    List<Coupon> GetCouponsByPrice(double price);
    List<Coupon> GetCouponsByCategoryAndPrice(Category category,double price);
    List<Coupon> getRandCoupons();
    Coupon getSingleCoupon(int couponId) throws CouponSystemExceptions;
}
