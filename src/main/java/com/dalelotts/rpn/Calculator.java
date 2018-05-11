package com.dalelotts.rpn;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

final class Calculator {

    private final static String errorMessage = "Please enter an integer as the 1st 2 inputs";
    private final static List<String> operator = Collections.unmodifiableList(Arrays.asList("+", "-"));
    private final PrintStream printStream;
    private final Scanner scanner;

    Calculator(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
    }

    private void addComputedNumberToResultList(List<Integer> resultList, List<Integer> computeList) {
        int result = compute(computeList);
        if (result != 0) {
            resultList.add(result);
        }
    }

    private int compute(List<Integer> computeList) {
        return computeList.stream()
                .mapToInt(a -> a)
                .sum();
    }

    private List<String> createReversedInputList() {
        final List<String> inputList = new ArrayList<>();
        while (scanner.hasNext()) {
            final String next = scanner.next();
            inputList.add(next);
        }
        Collections.reverse(inputList);
        return Collections.unmodifiableList(inputList);
    }

    void run() {
        final List<String> inputList = createReversedInputList();

        if (inputList.size() < 3 && inputList.contains("+")) {
            printStream.println(errorMessage);
            return;
        }

        final List<Integer> resultList = new ArrayList<>();
        List<Integer> computeList = new ArrayList<>();
        boolean operator = false;
        for (String inputItem : inputList) {

            if (Calculator.operator.contains(inputItem)) {
                addComputedNumberToResultList(resultList, computeList);
                operator = true;
                computeList = new ArrayList<>();
                continue;
            }

            if (operator) {
                computeList.add(Integer.parseInt(inputItem));
            } else {
                resultList.add(Integer.parseInt(inputItem));
            }
        }

        addComputedNumberToResultList(resultList, computeList);
        Collections.reverse(resultList);
        resultList.forEach(printStream::println);

    }
}