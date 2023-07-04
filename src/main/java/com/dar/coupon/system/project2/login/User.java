package com.dar.coupon.system.project2.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    private int id;
    private String password;
    private String email;
    private ClientType clientType;
    private String firstName;
    private String lastName;
    private String companyName;
}
