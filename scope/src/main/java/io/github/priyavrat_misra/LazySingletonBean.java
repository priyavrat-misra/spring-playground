package io.github.priyavrat_misra;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Lazy
public class LazySingletonBean {
  public LazySingletonBean() {
    System.out.println("LazySingletonBean created");
  }
}
