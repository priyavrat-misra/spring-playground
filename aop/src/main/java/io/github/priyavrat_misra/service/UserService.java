package io.github.priyavrat_misra.service;

import io.github.priyavrat_misra.aop.Loggable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Loggable
  public String getUser(String username) {
    return "User: " + username;
  }

  public void updateUser(String username) {
    System.out.println("Updating user: " + username);
  }

  public void errorProneMethod() {
    throw new RuntimeException("Simulated failure");
  }
}
