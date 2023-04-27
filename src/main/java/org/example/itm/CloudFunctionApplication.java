package org.example.itm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class CloudFunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudFunctionApplication.class, args);
    }

    @Bean
    public Function<String, String> lambdaHandlerWithFunction() {
        return value -> String.format("Lambda function handler was triggered: %s", new StringBuilder(value).reverse().toString());
    }

    @Bean
    public Supplier<String> lambdaHandlerWithSupplier() {
        return () -> "Lambda supplier handler was triggered";
    }

    @Bean
    public Consumer<String> lambdaHandlerWithConsumer() {
        return str -> System.out.println("Lambda consumer handler was triggered:" + str);
    }

}