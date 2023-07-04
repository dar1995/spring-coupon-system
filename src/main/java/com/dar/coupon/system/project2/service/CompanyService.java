package com.dar.coupon.system.project2.service;

import com.dar.coupon.system.project2.beans.Category;
import com.dar.coupon.system.project2.beans.Company;
import com.dar.coupon.system.project2.beans.Coupon;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;

import java.util.List;

public interface CompanyService {
    void addCoupon(int companyId, Coupon coupon) throws CouponSystemExceptions;

    void updateCoupon(int companyId, int couponID, Coupon coupon) throws CouponSystemExceptions;

    void deleteCoupon(int companyId, int couponID) throws CouponSystemExceptions;

    Coupon getSingle(int companyId, int couponId) throws CouponSystemExceptions;

    List<Coupon> getCompanyCoupons(int companyId) throws CouponSystemExceptions;

    List<Coupon> getCompanyCoupons(int companyId, Category category) throws CouponSystemExceptions;

    List<Coupon> getCompanyCoupons(int companyId, double maxPrice) throws CouponSystemExceptions;

    Company getCompanyDetails(int companyId) throws CouponSystemExceptions;

    void register(Company company) throws CouponSystemExceptions;
}
