package com.dar.coupon.system.project2.models;

import com.dar.coupon.system.project2.beans.Coupon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CouponToObject {
    private List<Coupon> coupons;

}
