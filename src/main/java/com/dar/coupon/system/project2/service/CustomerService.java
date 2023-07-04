package com.dar.coupon.system.project2.service;

import com.dar.coupon.system.project2.beans.Category;
import com.dar.coupon.system.project2.beans.Coupon;
import com.dar.coupon.system.project2.beans.Customer;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;

import java.util.List;

public interface CustomerService {
    void purchaseCoupon(int customerId, int couponID) throws CouponSystemExceptions;

    List<Coupon> getCustomerCoupons(int customerId) throws CouponSystemExceptions;

    List<Coupon> getCustomerCoupons(int customerId, Category category) throws CouponSystemExceptions;

    List<Coupon> getCustomerCoupons(int customerId, double maxPrice) throws CouponSystemExceptions;

    Coupon getSingle(int customerId, int couponId) throws CouponSystemExceptions;

    Customer getCustomerDetails(int customerId) throws CouponSystemExceptions;

    void register(Customer customer) throws CouponSystemExceptions;
}
