package com.dalelotts.rpn;

import java.util.Scanner;

public final class Main {

    private Main() {
    }

    public static void main(final String... args) {
        System.out.println("Please enter values followed by operation symbols:");
        System.out.println("(Press Q THEN ENTER to end the program):");
        final Scanner scanner = new Scanner(System.in);

        new Calculator().run(scanner, System.out);
    }
}
