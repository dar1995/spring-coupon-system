package com.dar.coupon.system.project2.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "coupons")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Coupon {
    @Id
    @GeneratedValue
    private int id = 0;
    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Company company;
    @Column(nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false, length = 45)
    private String title;
    @Column(nullable = false, length = 200)
    private String description;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private Date endDate;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private String image;


}
