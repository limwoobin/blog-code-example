package com.example.springtestexample.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Set;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomFeatureRegisterer implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    MultiValueMap<String, Object> getAnnotatedFeatureAttributeMap =
      importingClassMetadata.getAllAnnotationAttributes(Component.class.getName());

//    Reflections reflections = new Reflections(new ConfigurationBuilder()
//      .setUrls(ClasspathHelper.forPackage("com.myrealtrip.common"))
//      .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner())
//    );
//
//    Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(MyrealtripCommonFeatureConditionalBean.class);

    registerBeanDefinition(registry, Component.class);
  }

  private void registerBeanDefinition(BeanDefinitionRegistry registry, Class<?> featureClass) {
    BeanDefinition definition = BeanDefinitionBuilder.genericBeanDefinition(featureClass)
      .setScope(BeanDefinition.SCOPE_SINGLETON)
      .setLazyInit(false)
      .setAbstract(false)
      .setDependencyCheck(AbstractBeanDefinition.DEPENDENCY_CHECK_ALL)
      .getBeanDefinition();

    registry.registerBeanDefinition(featureClass.getName(), definition);
  }
}
