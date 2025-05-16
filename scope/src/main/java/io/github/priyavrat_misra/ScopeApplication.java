package io.github.priyavrat_misra;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScopeApplication {
  public static void main(String[] args) {
    System.out.println("Starting Spring context...");
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    System.out.println("\n--- Singleton (Eager) ---");
    EagerSingletonBean eager1 = context.getBean(EagerSingletonBean.class);
    EagerSingletonBean eager2 = context.getBean(EagerSingletonBean.class);
    System.out.println("Are eager beans the same? " + (eager1 == eager2));

    System.out.println("\n--- Singleton (Lazy) ---");
    LazySingletonBean lazy1 = context.getBean(LazySingletonBean.class);
    LazySingletonBean lazy2 = context.getBean(LazySingletonBean.class);
    System.out.println("Are lazy beans the same? " + (lazy1 == lazy2));

    System.out.println("\n--- Prototype ---");
    PrototypeBean proto1 = context.getBean(PrototypeBean.class);
    PrototypeBean proto2 = context.getBean(PrototypeBean.class);
    System.out.println("Are prototype beans the same? " + (proto1 == proto2));
  }
}
