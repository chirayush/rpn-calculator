package com.dalelotts.rpn;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.Stack;

final class Calculator {

    private final Scanner scanner;
    private final PrintStream printStream;

    Calculator(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
        if (scanner.next().equals("+")) {
            throw new IllegalArgumentException(printStream.print("2 operands are required"));
        }
    }

    void run() {
        Stack<Integer> operatingStack = new Stack<>();
        while (scanner.hasNext()) {
            final String input = scanner.next();
            if (input.equals("Q")) {
                printGoodbye(printStream);
                return;
            }

            if (input.equals("+")) {
                int value1 = operatingStack.pop();
                int value2 = operatingStack.pop();
                operatingStack.push(value1 + value2);

            } else {
                operatingStack.push(Integer.parseInt(input));
            }
            operatingStack.forEach(value -> printStream.print("[" + value + "]"));
            printStream.println();
        }

    }

    private void printGoodbye(PrintStream printStream) {
        printStream.print("Goodbye");
    }
}
