package com.dar.coupon.system.project2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerRegisterReq {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
