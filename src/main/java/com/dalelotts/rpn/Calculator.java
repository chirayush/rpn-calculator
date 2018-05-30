package com.dalelotts.rpn;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.Stack;

final class Calculator {

    void run(Scanner scanner, PrintStream printStream) {
        Stack<Integer> operatingStack = new Stack<>();
        while (scanner.hasNext()) {
            final String input = scanner.next();
            if (input.equals("+")) {
                int value1 = operatingStack.pop();
                int value2 = operatingStack.pop();
                operatingStack.push(value1 + value2);
            } else {
                operatingStack.push(Integer.parseInt(input));
            }
        }
        operatingStack.forEach(printStream::println);
    }
}
