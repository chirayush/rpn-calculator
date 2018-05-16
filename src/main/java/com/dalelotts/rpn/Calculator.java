package com.dalelotts.rpn;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.DoubleBinaryOperator;

final class Calculator {

    private static final Map<String, DoubleBinaryOperator> OPERATOR_MAP;
    private final PrintStream printStream;
    private final Scanner scanner;

    static {
        Map<String, DoubleBinaryOperator> operations = new HashMap<>();
        operations.put("+", Double::sum);
        operations.put("-", (a, b) -> a - b);
        operations.put("*", (a, b) -> a * b);
        operations.put("/", (a, b) -> a / b);
        OPERATOR_MAP = Collections.unmodifiableMap(operations);
    }

    Calculator(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
    }

    void run() {
        final List<String> inputList = readInputList();

        try {
            calculateRPN(inputList);
        } catch (EmptyStackException ex) {
            printStream.println("Please enter valid input");
        }
    }

    private List<String> readInputList() {
        final List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.next());
        }
        return inputList;
    }

    private void calculateRPN(List<String> inputList) {
        final Stack<Double> operatorStack = new Stack<>();
        inputList.forEach(input -> {
            if (!OPERATOR_MAP.keySet().contains(input)) {
                operatorStack.push(Double.parseDouble(input));
            } else {
                double value1 = operatorStack.pop();
                double value2 = operatorStack.pop();
                double value = OPERATOR_MAP.get(input).applyAsDouble(value2, value1);
                operatorStack.push(value);
            }
        });
        for (Double value : operatorStack) {
            DecimalFormat decimalFormat = new DecimalFormat("0.##");
            printStream.println(decimalFormat.format(value));
        }
    }
}
