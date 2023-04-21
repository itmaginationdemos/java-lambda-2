package org.example;

import java.util.function.Function;

public class FunctionHandler implements Function<String, String> {

    @Override
    public String apply(String s) {
        return String.format("Lambda function handler from class was triggered: %s", s);
    }
}