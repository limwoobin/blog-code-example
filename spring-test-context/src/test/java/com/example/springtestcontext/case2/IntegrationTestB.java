package com.example.springtestcontext.case2;

import com.example.springtestcontext.domain.Coupon;
import com.example.springtestcontext.domain.CouponRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IntegrationTestB extends AbstractIntegrationTest {

  @Autowired
  private CouponRepository couponRepository;

  @DisplayName(value = "BeforeAll Test B Run...")
  @Test
  void test() {
    List<Coupon> coupons = couponRepository.findAll();
    System.out.println("coupon size: " + coupons.size());
  }

}
