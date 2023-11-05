package com.example.springtestcontext.case1;

import com.example.springtestcontext.domain.Coupon;
import com.example.springtestcontext.domain.CouponRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class IntegrationTestA extends AbstractIntegrationTest {

  @Autowired
  private CouponRepository couponRepository;

  @DisplayName("TestExecution Listener Test A Run...")
  @Test
  void test() {
    List<Coupon> result = couponRepository.findAll();
    System.out.println("coupon size: " + result.size());
  }

}
