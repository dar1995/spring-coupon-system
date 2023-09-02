package com.dar.coupon.system.project2.repositories;

import com.dar.coupon.system.project2.beans.Category;
import com.dar.coupon.system.project2.beans.Company;
import com.dar.coupon.system.project2.beans.Coupon;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    @Transactional
    @Modifying
    @Query(value = """
            delete coupon_system.customers_coupons from coupon_system.customers_coupons
            join coupon_system.coupons on customers_coupons.coupons_id = coupons.id
            where coupons.company_id = ?;""", nativeQuery = true)
    void deletePurchase(int companyId);

    boolean existsByTitleAndCompany(String title, Company company);

    boolean existsByIdAndCompany(int couponId, Company company);

    List<Coupon> findByCompany(Company company);

    Coupon findByIdAndCompany(int couponId, Company company);
    Coupon findByTitleAndCompany(String title, Company company);

    List<Coupon> findByCompanyAndCategory(Company company, Category category);

    List<Coupon> findByCompanyAndPriceLessThan(Company company, double price);
    List<Coupon> findByCategory(Category category);
    List<Coupon> findByPriceLessThan(double price);
    List<Coupon> findByCategoryAndPriceLessThan(Category category, double price);



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

    @Query(value = """
            select coupon_system.coupons.* from  coupon_system.coupons
                         join coupon_system.customers_coupons on coupons.id = customers_coupons.coupons_id
                         where customers_coupons.customer_id = ? and coupons.category = ?""", nativeQuery = true)
    List<Coupon> customerCoupons(int customerId, String category);

    @Query(value = """
            select coupon_system.coupons.* from  coupon_system.coupons
                         join coupon_system.customers_coupons on coupons.id = customers_coupons.coupons_id
                         where customers_coupons.customer_id = ? and coupons.price < ?""", nativeQuery = true)
    List<Coupon> customerCoupons(int customerId, double maxPrice);

    @Transactional
    @Modifying
    @Query(value = """
            delete coupon_system.customers_coupons from coupon_system.customers_coupons
                        join coupon_system.coupons on customers_coupons.coupons_id = coupons.id
                        where coupons.end_date < curdate()""", nativeQuery = true)
    void deletePurchasedExpiredCoupons();

    @Transactional
    @Modifying
    @Query(value = "delete  from coupon_system.coupons\n" +
            "where coupons.end_date < curdate() and coupons.id <> 0", nativeQuery = true)
    void deleteExpiredCoupons();

    @Query(value = "SELECT * FROM coupon_system.coupons order by rand() limit 3;", nativeQuery = true)
    List<Coupon> getRandCoupons();


}
