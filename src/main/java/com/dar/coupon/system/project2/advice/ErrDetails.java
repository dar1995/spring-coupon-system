package com.dar.coupon.system.project2.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrDetails {
    private final String title = "Coupon System";
    private String description;
}
