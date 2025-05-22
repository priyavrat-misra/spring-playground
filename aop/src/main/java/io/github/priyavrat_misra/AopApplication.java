package io.github.priyavrat_misra;

import io.github.priyavrat_misra.service.AdminService;
import io.github.priyavrat_misra.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopApplication {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx =
        new AnnotationConfigApplicationContext(AppConfig.class);

    UserService userService = ctx.getBean(UserService.class);
    AdminService adminService = ctx.getBean(AdminService.class);

    System.out.println("UserService proxy: " + userService.getClass());
    System.out.println("AdminService proxy: " + adminService.getClass());

    System.out.println("\n-- Call getUser (loggable & get* method) --");
    userService.getUser("alice");

    System.out.println("\n-- Call updateUser (not loggable) --");
    userService.updateUser("bob");

    System.out.println("\n-- Call performAdminTask (not loggable) --");
    adminService.performAdminTask();

    System.out.println("\n-- Call errorProneMethod (throws exception) --");
    try {
      userService.errorProneMethod();
    } catch (Exception ignored) {
    }
  }
}
