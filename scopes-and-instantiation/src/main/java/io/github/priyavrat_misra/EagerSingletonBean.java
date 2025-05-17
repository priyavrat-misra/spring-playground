package io.github.priyavrat_misra;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON) // default scope, optional to mention
public class EagerSingletonBean {
  public EagerSingletonBean() {
    System.out.println("EagerSingletonBean created");
  }
}
