package com.dar.coupon.system.project2.repositories;

import com.dar.coupon.system.project2.beans.Category;
import com.dar.coupon.system.project2.beans.Company;
import com.dar.coupon.system.project2.beans.Coupon;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CouponRepository  extends JpaRepository<Coupon, Integer> {

//    @Query(value = "INSERT INTO coupon_system.customers_coupons (`customer_id`, `coupons_id`) VALUES (?, ?)", nativeQuery = true)
//     void purchaseCoupon(int customerId, int couponId);
    @Transactional
    @Modifying
    @Query(value = "delete coupon_system.customers_coupons from coupon_system.customers_coupons\n" +
            "join coupon_system.coupons on customers_coupons.coupons_id = coupons.id\n" +
            "where coupons.company_id = ?;", nativeQuery = true)
    void deletePurchase(int companyId);

    boolean existsByTitleAndCompany(String title, Company company);
    boolean existsByIdAndCompany(int couponId, Company company);
    List<Coupon> findByCompany(Company company );
    Coupon findByIdAndCompany(int couponId, Company company);
    List<Coupon> findByCompanyAndCategory(Company company, Category category);
    List<Coupon> findByCompanyAndPriceLessThan(Company company, double price);
    @Query(value = "select case when exists(select 1 from coupon_system.customers_coupons where customer_id = ? and coupons_id = ?)\n" +
            " then 'true' else 'false' end as is_exists", nativeQuery = true)
    boolean isPurchased(int customerId, int couponId);
    @Query(value = " select coupon_system.coupons.* from  coupon_system.coupons\n" +
            " join coupon_system.customers_coupons on coupons.id = customers_coupons.coupons_id\n" +
            " where customers_coupons.customer_id = ?", nativeQuery = true)
    List<Coupon> customerCoupons(int customerId);

    @Query(value = " select coupon_system.coupons.* from  coupon_system.coupons\n" +
            " join coupon_system.customers_coupons on coupons.id = customers_coupons.coupons_id\n" +
            " where customers_coupons.customer_id = ? and customers_coupons.coupons_id = ?", nativeQuery = true)
    Coupon customerSingleCoupons(int customerId, int couponId);
    @Query(value = "select coupon_system.coupons.* from  coupon_system.coupons\n" +
            "             join coupon_system.customers_coupons on coupons.id = customers_coupons.coupons_id\n" +
            "             where customers_coupons.customer_id = ? and coupons.category = ?", nativeQuery = true)
    List<Coupon> customerCoupons(int customerId, String category);

    @Query(value = "select coupon_system.coupons.* from  coupon_system.coupons\n" +
            "             join coupon_system.customers_coupons on coupons.id = customers_coupons.coupons_id\n" +
            "             where customers_coupons.customer_id = ? and coupons.price < ?", nativeQuery = true)
    List<Coupon> customerCoupons(int customerId, double maxPrice);

    @Transactional
    @Modifying
    @Query(value = "delete coupon_system.customers_coupons from coupon_system.customers_coupons\n" +
            "            join coupon_system.coupons on customers_coupons.coupons_id = coupons.id\n" +
            "            where coupons.end_date < curdate()", nativeQuery = true)
    void deletePurchasedExpiredCoupons();

    @Transactional
    @Modifying
    @Query(value = "delete  from coupon_system.coupons\n" +
            "where coupons.end_date < curdate() and coupons.id <> 0", nativeQuery = true)
    void deleteExpiredCoupons();


}
