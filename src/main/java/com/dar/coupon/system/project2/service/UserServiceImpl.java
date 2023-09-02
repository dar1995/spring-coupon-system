package com.dar.coupon.system.project2.service;

import com.dar.coupon.system.project2.beans.Category;
import com.dar.coupon.system.project2.beans.Coupon;
import com.dar.coupon.system.project2.exceptions.CouponSystemExceptions;
import com.dar.coupon.system.project2.exceptions.ErrMsg;
import com.dar.coupon.system.project2.repositories.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private CouponRepository couponRepository;
    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public List<Coupon> GetCouponsByCategory(Category category) {
        return couponRepository.findByCategory(category);
    }

    @Override
    public List<Coupon> GetCouponsByPrice(double price) {
        return couponRepository.findByPriceLessThan(price);
    }

    @Override
    public List<Coupon> GetCouponsByCategoryAndPrice(Category category, double price) {
        return couponRepository.findByCategoryAndPriceLessThan(category, price);
    }

    @Override
    public List<Coupon> getRandCoupons() {
        return couponRepository.getRandCoupons();
    }

    @Override
    public Coupon getSingleCoupon(int couponId) throws CouponSystemExceptions {
        return couponRepository.findById(couponId).orElseThrow(()->new CouponSystemExceptions(ErrMsg.COUPON_NOT_EXIST));
    }
}
