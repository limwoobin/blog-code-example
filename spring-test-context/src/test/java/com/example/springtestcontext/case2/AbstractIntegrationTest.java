package com.example.springtestcontext.case2;

import com.example.springtestcontext.domain.Coupon;
import com.example.springtestcontext.domain.CouponRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest
public abstract class AbstractIntegrationTest {

  @Autowired
  private CouponRepository couponRepository;

  private static boolean initialize = false;

  @BeforeAll
  void setUp() {
    if (!initialize) {
      System.out.println("BeforeAll Test Prepare ...");

      Coupon coupon = new Coupon("test Coupon");
      couponRepository.save(coupon);
      initialize = true;
    }
  }
}
