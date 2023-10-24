package com.example.springtestcontext;

import com.example.springtestcontext.domain.Coupon;
import com.example.springtestcontext.domain.CouponRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class IntegrationTestB extends AbstractIntegrationTest {

  @Autowired
  private CouponRepository couponRepository;

  @DisplayName("Integration Test B Run...")
  @Test
  void test() {
    List<Coupon> result = couponRepository.findAll();
    System.out.println("user size: " + result.size());
  }
}
