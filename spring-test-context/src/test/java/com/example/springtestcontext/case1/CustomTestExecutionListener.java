package com.example.springtestcontext.case1;

import com.example.springtestcontext.domain.Coupon;
import com.example.springtestcontext.domain.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class CustomTestExecutionListener implements TestExecutionListener {
  private static boolean initialize = false;

  @Autowired
  private CouponRepository couponRepository;

  @Override
  public void prepareTestInstance(TestContext testContext) throws Exception {
    testContext.getApplicationContext()
      .getAutowireCapableBeanFactory()
      .autowireBean(this);

//    if (!initialize) {
//      Coupon coupon = new Coupon(1L, "coupon");
      Coupon coupon = new Coupon("coupon");
      couponRepository.save(coupon);

      System.out.println("Test Prepare");
      initialize = true;
//    }
  }
}
