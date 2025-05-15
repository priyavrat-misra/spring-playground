package io.github.priyavrat_misra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "io.github.priyavrat_misra")
public class AppConfig {
  @Bean
  @Primary
  public GreetingService frenchGreetingService() {
    return () -> "Bonjour!";
  }
}
