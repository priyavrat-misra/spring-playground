# Spring Aspect Oriented Programming

## Overview

This project demonstrates the following Spring AOP concepts:

- Aspect, advice, join point, pointcut, weaving, proxy
- Multiple pointcut types: `execution`, `within`, and custom annotation
- Logical operators (`&&`, `||`, `!`) in pointcut expressions
- All advice types: `@Before`, `@After`, `@AfterReturning`, `@AfterThrowing`, `@Around`
- Advice ordering
- Logging method name, arguments, execution time, and class (proxy) name
- Custom annotation for fine-grained advice

| Concept    | Description                                              | Example                              |
|------------|----------------------------------------------------------|--------------------------------------|
| Aspect     | Cross-cutting concern (logging)                          | `LoggingAspect`                      |
| Advice     | Code run at join points (`@Before`, `@After`, etc.)      | Methods in `LoggingAspect`           |
| Join Point | A point in execution (method call)                       | Service methods                      |
| Pointcut   | Expression matching join points                          | `execution`, `within`, `@annotation` |
| Weaving    | Linking aspects to code (Spring uses proxies at runtime) | Handled by Spring                    |
| Proxy      | Object returned by Spring that applies aspects           | See printed proxy class              |

## Pointcut Examples

- `within(io.github.priyavrat_misra.service..*)`  
  Matches all methods in `service` package and subpackages
- `execution(* io.github.priyavrat_misra.service.UserService.get*(..))`  
  Matches all `get*` methods in `UserService`
- `@annotation(io.github.priyavrat_misra.aop.Loggable)`  
  Matches methods annotated with `@Loggable`
- Logical operators:
    - `&&` (and): `allServiceMethods() && !loggableMethods()`
    - `||` (or): `userServiceGetMethods() || loggableMethods()`
    - `!` (not): `!loggableMethods()`

## Advice Types

- `@Before`: Runs before matched method
- `@After`: Runs after (even if exception thrown)
- `@AfterReturning`: Runs after successful return
- `@AfterThrowing`: Runs after exception
- `@Around`: Wraps method, can modify arguments/return, measures execution time

## When to Use Each Pointcut

- `execution`: For matching by method signature (name, parameters, etc.)
- `within`: For matching all methods in a class/package
- `@annotation`: For explicit, fine-grained control

## Advice Ordering: When Multiple Advices Match

When multiple advices match the same join point, **Spring AOP determines the execution order as follows**:

### **Advice Type Precedence**

Spring AOP always applies advice types in this order (from outermost to innermost):

1. `@Around`
2. `@Before`
3. `@After`
4. `@AfterReturning`
5. `@AfterThrowing`

> For example, `@Around` advice always wraps `@Before`, and so on.

### **Aspect Ordering with `@Order` or `Ordered`**

If multiple aspects (classes) have advices matching the same join point:

- Use the `@Order` annotation or implement the `Ordered` interface on aspect classes.
- **Lower values have higher precedence** (`@Order(1)` runs before `@Order(2)`).
- If not specified, order is undefined but consistent within a JVM run.

**Example:**
```java
@Aspect
@Order(1)
public class LoggingAspect { /*...*/ }

@Aspect
@Order(2)
public class SecurityAspect { /*...*/ }
```
Here, `LoggingAspect` advice will run before `SecurityAspect` advice of the same type.

### **Order Within a Single Aspect**

If multiple advices of the same type are declared in the same aspect and match the same join point, **the order is by method declaration in the source file** (though not formally guaranteed by the spec).

## Running the Example

1. Build and run `AopDemoApplication`.
2. Observe:
    - Proxy class names
    - Which advices fire for each method
    - How pointcuts combine with logical operators
    - Logging of execution time, method name, arguments, and exceptions

## Output

```raw
UserService proxy: class io.github.priyavrat_misra.service.UserService$$SpringCGLIB$$0
AdminService proxy: class io.github.priyavrat_misra.service.AdminService$$SpringCGLIB$$0

-- Call getUser (loggable & get* method) --
[BEFORE] UserService.getUser([alice]), class: UserService
[AFTER RETURNING] UserService.getUser([alice]), result: User: alice
[AROUND] UserService.getUser([alice]), time: 1.158802 ms

-- Call updateUser (not loggable) --
[BEFORE] UserService.updateUser([bob]), class: UserService
Updating user: bob
[AFTER] UserService.updateUser([bob])

-- Call performAdminTask (not loggable) --
[BEFORE] AdminService.performAdminTask([]), class: AdminService
Performing admin task...
[AFTER] AdminService.performAdminTask([])

-- Call errorProneMethod (throws exception) --
[BEFORE] UserService.errorProneMethod([]), class: UserService
[AFTER THROWING] UserService.errorProneMethod([]), exception: Simulated failure
[AFTER] UserService.errorProneMethod([])
```
