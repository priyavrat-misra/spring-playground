# Spring Bean Scopes & Instantiation

## Overview

- **EagerSingletonBean**: A default (eager) singleton bean.
- **LazySingletonBean**: A singleton bean created lazily (on first use).
- **PrototypeBean**: A prototype-scoped bean (a new instance every time you ask for it).
- **ScopeApplication**: Main class to observe instantiation and scope behavior.
- **AppConfig**: Configuration class with `@Configuration` and `@ComponentScan`.

## How It Works

1. **Spring scans for components** in the package (`@ComponentScan` in `AppConfig`).
2. **EagerSingletonBean** is instantiated as soon as the context starts.
3. **LazySingletonBean** is not instantiated until it is first requested from the context.
4. **PrototypeBean** is instantiated every time it is requested from the context.
5. The main application fetches each bean twice and prints whether the two references are the same (singleton) or
   different (prototype).

## Annotations

| Annotation | Description                                                                                                 |
|------------|-------------------------------------------------------------------------------------------------------------|
| `@Scope`   | Specifies the scope of the bean (`BeanDefinition.SCOPE_SINGLETON`, `BeanDefinition.SCOPE_SINGLETON`, etc.). |
| `@Lazy`    | Marks a bean to be lazily initialized (created only when first requested).                                  |

## Output

```raw
Starting Spring context...
EagerSingletonBean created

--- Singleton (Eager) ---
Are eager beans the same? true

--- Singleton (Lazy) ---
LazySingletonBean created
Are lazy beans the same? true

--- Prototype ---
PrototypeBean created
PrototypeBean created
Are prototype beans the same? false
```
