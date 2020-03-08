package com.mySampleApplication.client;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BinaryOperator;


class Calculator
{
    private Map<String, BinaryOperator<Double>> operations = new TreeMap<String, BinaryOperator<Double>>()
    {{
        put("+", (a, b) -> a + b);
        put("-", (a, b) -> a - b);
        put("/", (a, b) -> a / b);
        put("*", (a, b) -> a * b);
        put("%", (a, b) -> a % b);
        put("//", (a, b) -> (double) (a.intValue() / b.intValue()));
        put("^", Math::pow);
    }};


    Double calculate(String operation, double v1, double v2)
    {
        return operations.get(operation).apply(v1, v2);
    }

    Set<String> supportedOperations()
    {
        return operations.keySet();
    }
}
