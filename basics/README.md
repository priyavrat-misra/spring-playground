# Spring Inversion of Control & Dependency Injection

## Overview

- **GreetingService**: An interface for greeting messages.
- **EnglishGreetingService** & **SpanishGreetingService**: Implementations of the interface, discovered via `@Component`.
- **FrenchGreetingService**: Provided as a lambda via a `@Bean` method in `AppConfig`, marked with `@Primary`.
- **GreetingController**: Depends on `GreetingService`, injected via *constructor injection*.
- **AppConfig**: Configuration class with `@Configuration` and `@ComponentScan`.
- **BasicsApplication**: Main class that starts the Spring context and triggers the greeting.

## How It Works

1. **Spring scans for components** in the package (`@ComponentScan` in `AppConfig`).
2. **Beans are registered** for each class annotated with `@Component` and those defined via `@Bean` methods.
3. **Dependency injection** is performed automatically by Spring, preferring the `@Primary` bean when multiple candidates exist.
4. **GreetingController** receives a `GreetingService` implementation and prints a greeting.
5. The main application fetches the controller bean and calls its `greet()` method.

## Annotations

| Annotation       | Description                                                                                     |
|------------------|-------------------------------------------------------------------------------------------------|
| `@Component`     | Marks a class as a Spring bean/component to be managed by the Spring container.                 |
| `@Configuration` | Indicates that the class contains Spring bean definitions (Java-based configuration).           |
| `@ComponentScan` | Tells Spring where to search for annotated components to register as beans.                     |
| `@Bean`          | Declares a bean to be managed by Spring, usually within a `@Configuration` class.               |
| `@Primary`       | Marks a bean as the default when multiple candidates exist for autowiring.                      |
| `@Autowired`     | Instructs Spring to inject the required dependency automatically (constructor or field/setter). |

## Output

```raw
Bonjour!
```
