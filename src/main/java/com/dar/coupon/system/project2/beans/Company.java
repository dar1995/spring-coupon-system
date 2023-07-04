package com.dar.coupon.system.project2.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
    @OneToMany( mappedBy = "company", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @ToString.Exclude
    @JsonIgnore
    private List<Coupon> coupons;

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
        if (!this.coupons.isEmpty()){
            for (Coupon coupon:this.coupons) {
                coupon.setCompany(this);
        }}
    }

    public void addCoupon(Coupon coupon){
        coupon.setCompany(this);
        this.coupons.add(coupon);
    }
    //    public Coupon getSingleCoupon(int idx) {
//        return coupons.get(idx);
//    }

//    @Override
//    public String toString() {
//        return "Company{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                " coupons=\n" + (coupons != null ? coupons.stream().map(Coupon::toString).collect(Collectors.joining("\n")) : "") +
//                '}' + "\n";
//    }
}
