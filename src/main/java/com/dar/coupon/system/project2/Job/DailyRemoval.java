package com.dar.coupon.system.project2.Job;

import com.dar.coupon.system.project2.repositories.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyRemoval {
    @Autowired
   private CouponRepository couponRepository;

    @Scheduled(initialDelay = 1000 * 20, fixedRate = 1000 * 60 * 60)
    public void remove() {
        System.out.println("Coupons before daily removal:");
        couponRepository.findAll().forEach(System.out::println);
        System.out.println("daily removal starting");
        couponRepository.deletePurchasedExpiredCoupons();
        couponRepository.deleteExpiredCoupons();
        System.out.println("daily removal finished..");
        System.out.println("Coupons after daily removal:");
        couponRepository.findAll().forEach(System.out::println);

    }


}
