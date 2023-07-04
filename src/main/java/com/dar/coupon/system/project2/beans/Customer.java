package com.dar.coupon.system.project2.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Customer {
    @Id
    @GeneratedValue
    private int id = 0;
    @Column(nullable = false, length = 45)

    private String firstName;
    @Column(nullable = false, length = 45)

    private String lastName;
    @Column(nullable = false, length = 45)

    private String email;
    @Column(nullable = false, length = 45)

    private String password;
    @ManyToMany
    @ToString.Exclude
    @JsonIgnore
    private List<Coupon> coupons;

    public void purchaseCoupon(Coupon coupon) {
        this.coupons.add(coupon);
    }


}
