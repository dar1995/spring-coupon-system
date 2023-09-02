package com.dar.coupon.system.project2.beans;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Company {
    @Id
    @GeneratedValue
    private int id = 0;
    @Column(length = 45, nullable = false)
    private String name;
    @Column(length = 45, nullable = false)

    private String email;
    @Column(length = 45, nullable = false)

    private String password;
    @OneToMany(mappedBy = "company", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @ToString.Exclude
    private List<Coupon> coupons;

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
        if (!this.coupons.isEmpty()) {
            for (Coupon coupon : this.coupons) {
                coupon.setCompany(this);
            }
        }
    }


}
