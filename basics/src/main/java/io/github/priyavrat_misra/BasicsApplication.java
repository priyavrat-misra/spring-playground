package io.github.priyavrat_misra;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BasicsApplication {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    /*
    Alternatively, instead of @ComponentScan in AppConfig.java,
    ApplicationContext context = new AnnotationConfigApplicationContext(
            AppConfig.class, EnglishGreetingService.class, SpanishGreetingService.class, GreetingController.class
    );
    */

    GreetingController controller = context.getBean(GreetingController.class);
    controller.greet();
  }
}
