package com.example.springconfig.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
public class ExamTest {

  @Autowired
  private ExamConfiguration examConfiguration;

  @Autowired
  private ExamComponent examComponent;

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  void configuration_test() {
    Exam exam = examConfiguration.exam();
    Exam exam2 = examConfiguration.exam();
    Exam exam3 = examConfiguration.exam();
    Exam exam4 = examConfiguration.exam();
    Exam exam5 = examConfiguration.exam();

    System.out.println("exam: " + exam);
    System.out.println("exam2: " + exam2);
    System.out.println("exam3: " + exam3);
    System.out.println("exam4: " + exam4);
    System.out.println("exam5: " + exam5);
  }

  @Test
  void component_test() {
    Exam exam = examComponent.exam();
    Exam exam2 = examComponent.exam();
    Exam exam3 = examComponent.exam();
    Exam exam4 = examComponent.exam();
    Exam exam5 = examComponent.exam();

    System.out.println("exam: " + exam);
    System.out.println("exam2: " + exam2);
    System.out.println("exam3: " + exam3);
    System.out.println("exam4: " + exam4);
    System.out.println("exam5: " + exam5);
  }

  @Test
  void test2() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(ExamConfiguration.class);
    ApplicationContext ac2 = new AnnotationConfigApplicationContext(ExamComponent.class);

    Object o = ac.getBean("exam");
    Object o2 = ac2.getBean("exam2");

    System.out.println(o);
    System.out.println(o2);

    Object result = applicationContext.getBean("exam");
    Object result2 = applicationContext.getBean("exam2");
//
    System.out.println(result);
    System.out.println(result2);

//    boolean result = applicationContext.containsBean("exam");
//    boolean result2 = applicationContext.containsBean("exam2");
//
//    System.out.println(result);
//    System.out.println(result2);
  }
}
