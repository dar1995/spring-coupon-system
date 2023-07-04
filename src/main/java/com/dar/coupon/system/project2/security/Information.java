package com.dar.coupon.system.project2.security;

import com.dar.coupon.system.project2.login.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Information {
    private int id;
    private LocalDateTime time;
    private ClientType clientType;
    boolean isExpired = false;
}
