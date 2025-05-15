package io.github.priyavrat_misra;

import org.springframework.stereotype.Component;

@Component
public class EnglishGreetingService implements GreetingService {
  @Override
  public String greet() {
    return "Hello!";
  }
}
