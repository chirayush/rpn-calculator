package com.dalelotts.rpn;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

final class Calculator {

    private final PrintStream printStream;
    private final Scanner scanner;

    Calculator(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
    }

    void run() {
        final List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            inputList.add(scanner.next());
        }

        final Stack<Integer> operatorStack = new Stack<>();
        for (String input : inputList) {
            if (!input.equals("+")) {
                operatorStack.push(Integer.parseInt(input));
            } else {
                int value1 = operatorStack.pop();
                int value2 = operatorStack.pop();
                operatorStack.push(value1 + value2);
            }
        }
        operatorStack.forEach(printStream::println);
    }
}
