package com.dalelotts.rpn;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public final class Main {

    private Main() {
    }

    public static void main(final String... args) {
        System.out.println("Please enter values followed by operation symbols:");
        System.out.println("(Press CTRL+Z to end the program):");
        final Scanner scanner = new Scanner(System.in);
        final OutputStream outputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(outputStream);

        new Calculator().run(scanner, printStream);
    }
}
