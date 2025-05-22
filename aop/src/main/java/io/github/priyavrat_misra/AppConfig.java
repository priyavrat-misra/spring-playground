package io.github.priyavrat_misra;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "io.github.priyavrat_misra")
@EnableAspectJAutoProxy
public class AppConfig {}
