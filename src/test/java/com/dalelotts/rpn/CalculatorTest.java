package com.dalelotts.rpn;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

    private Calculator calculator;
    private OutputStream outputStream = new ByteArrayOutputStream();
    private PrintStream printStream = new PrintStream(outputStream);

    @Test
    public void runShouldPrintNumberWhenNumberIsEntered() {
        calculator = new Calculator(new Scanner("3"), printStream);
        calculator.run();
        assertThat(outputStream.toString(), equalTo("3\n"));
    }

    @Test
    public void runShouldPrint2NumbersWhen2NumbersEntered() {
        calculator = new Calculator(new Scanner("3 5"), printStream);
        calculator.run();
        assertThat(outputStream.toString(), equalTo("3\n5\n"));
    }

    @Test
    public void runShouldPrintSumWhen2NumbersFollowedByAPlusSign() {
        calculator = new Calculator(new Scanner("6 4 +"), printStream);
        calculator.run();
        assertThat(outputStream.toString(), equalTo("10\n"));
    }
    
    @Test
    public void runShouldSumTwiceIfFollowedBy2PlusSigns() {
        calculator = new Calculator(new Scanner("2 5 6 + +"), printStream);
        calculator.run();
        assertThat(outputStream.toString(), equalTo("13\n"));
    }
}
