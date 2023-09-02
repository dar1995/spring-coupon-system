package com.dar.coupon.system.project2.repositories;

import com.dar.coupon.system.project2.models.NameAndId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NameAndIdRepository extends JpaRepository<NameAndId, Integer> {
    @Query(value = "select id, first_name as name from customers where email = ? and password = ?", nativeQuery = true)
    NameAndId findCustomerNameAndIdByEmailAndPassword(String email, String password);

    @Query(value = "select id, name from companies where email = ? and password = ?", nativeQuery = true)
    NameAndId findCompanyNameAndIdByEmailAndPassword(String email, String password);
}
