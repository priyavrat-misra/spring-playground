package io.github.priyavrat_misra.aop;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1) // Advice ordering: lower numbers have higher precedence
public class LoggingAspect {

  // Pointcut: All methods in service package
  @Pointcut("within(io.github.priyavrat_misra.service..*)")
  public void allServiceMethods() {}

  // Pointcut: Methods with @Loggable annotation
  @Pointcut("@annotation(io.github.priyavrat_misra.aop.Loggable)")
  public void loggableMethods() {}

  // Pointcut: All get* methods in UserService using execution
  @Pointcut("execution(* io.github.priyavrat_misra.service.UserService.get*(..))")
  public void userServiceGetMethods() {}

  // Combined pointcut: All service methods except those with @Loggable
  @Pointcut("allServiceMethods() && !loggableMethods()")
  public void serviceMethodsExceptLoggable() {}

  // Combined pointcut: All get* methods OR any loggable method
  @Pointcut("userServiceGetMethods() || loggableMethods()")
  public void getOrLoggableMethods() {}

  // BEFORE advice: All service methods
  @Before("allServiceMethods()")
  public void logBefore(JoinPoint joinPoint) {
    System.out.println(
        "[BEFORE] "
            + signature(joinPoint)
            + ", class: "
            + joinPoint.getTarget().getClass().getSimpleName());
  }

  // AFTER advice: All service methods except those with @Loggable
  @After("serviceMethodsExceptLoggable()")
  public void logAfter(JoinPoint joinPoint) {
    System.out.println("[AFTER] " + signature(joinPoint));
  }

  // AFTER RETURNING: Any get* or @Loggable method
  @AfterReturning(pointcut = "getOrLoggableMethods()", returning = "result")
  public void logAfterReturning(JoinPoint joinPoint, Object result) {
    System.out.println("[AFTER RETURNING] " + signature(joinPoint) + ", result: " + result);
  }

  // AFTER THROWING: Any method in UserService
  @AfterThrowing(pointcut = "within(io.github.priyavrat_misra.service.UserService)", throwing = "ex")
  public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
    System.out.println(
        "[AFTER THROWING] " + signature(joinPoint) + ", exception: " + ex.getMessage());
  }

  // AROUND: Only @Loggable methods, logs time taken
  @Around("loggableMethods()")
  public Object logExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
    long start = System.nanoTime();
    try {
      Object result = pjp.proceed();
      long end = System.nanoTime();
      System.out.println(
          "[AROUND] " + signature(pjp) + ", time: " + ((end - start) / 1_000_000.0) + " ms");
      return result;
    } catch (Throwable t) {
      long end = System.nanoTime();
      System.out.println(
          "[AROUND EXCEPTION] "
              + signature(pjp)
              + ", time: "
              + ((end - start) / 1_000_000.0)
              + " ms, exception: "
              + t.getMessage());
      throw t;
    }
  }

  private String signature(JoinPoint joinPoint) {
    MethodSignature sig = (MethodSignature) joinPoint.getSignature();
    return sig.getDeclaringType().getSimpleName()
        + "."
        + sig.getName()
        + "("
        + java.util.Arrays.toString(joinPoint.getArgs())
        + ")";
  }
}
