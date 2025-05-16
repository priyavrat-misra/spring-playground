package io.github.priyavrat_misra;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PrototypeBean {
  public PrototypeBean() {
    System.out.println("PrototypeBean created");
  }
}
