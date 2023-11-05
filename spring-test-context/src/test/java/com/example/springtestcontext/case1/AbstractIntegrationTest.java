package com.example.springtestcontext.case1;

import com.example.springtestcontext.domain.CouponRepository;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestExecutionListeners(value = {
  CustomTestExecutionListener.class,
  DependencyInjectionTestExecutionListener.class
})
@SpringBootTest
public class AbstractIntegrationTest {

  @Autowired
  private CouponRepository couponRepository;

  private static boolean initialize = false;

//  @BeforeAll
//  void setUp() {
//    if (!initialize) {
//      Coupon coupon = new Coupon("coupon");
//      couponRepository.save(coupon);
//
//      System.out.println("init...");
//      initialize = true;
//    }
//  }

}
