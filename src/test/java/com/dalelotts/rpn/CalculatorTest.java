package com.dalelotts.rpn;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

    private Calculator calculator;
    private OutputStream outputStream;
    private PrintStream printStream;

    @Before
    public void before() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        calculator = new Calculator();
    }

    @Test
    public void runShouldPrintNumberIfANumberIsEntered() {
        calculator.run(new Scanner("3"), printStream);
        assertThat(outputStream.toString(), equalTo("3\n"));
    }

    @Test
    public void runShouldPrint2NumbersWhen2NumbersEntered() {
        calculator.run(new Scanner("3 5"), printStream);
        assertThat(outputStream.toString(), equalTo("3\n5\n"));
    }

    @Test
    public void runShouldAddNumbersWhen2NumbersFollowedByAPlusSign() {
        calculator.run(new Scanner("5 7 +"), printStream);
        assertThat(outputStream.toString(), equalTo("12\n"));
    }

    @Test
    public void runShouldPrintGoodbyeOnQ() {
        calculator.run(new Scanner("Q"), printStream);
        assertThat(outputStream.toString(), equalTo("Goodbye"));
    }


}
