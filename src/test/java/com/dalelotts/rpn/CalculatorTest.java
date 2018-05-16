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

    @Test
    public void runShouldSumTwiceIfNumberFollowedByPlusSign() {
        calculator = new Calculator(new Scanner("3 4 + 9 +"), printStream);
        calculator.run();
        assertThat(outputStream.toString(), equalTo("16\n"));
    }

    @Test
    public void runShouldSubtractIfFollowedByDifferenceSign() {
        calculator = new Calculator(new Scanner("2 5 -"), printStream);
        calculator.run();
        assertThat(outputStream.toString(), equalTo("-3\n"));
    }

    @Test
    public void runShouldAddAndThenSubtractWhenPlusFollowedByAMinusSign() {
        calculator = new Calculator(new Scanner("7 5 2 - +"), printStream);
        calculator.run();
        assertThat(outputStream.toString(), equalTo("10\n"));
    }

    @Test
    public void runShouldMultiplyIfFollowedByAStarSign() {
        calculator = new Calculator(new Scanner("2 5 *"), printStream);
        calculator.run();
        assertThat(outputStream.toString(), equalTo("10\n"));
    }
    
    @Test
    public void runShouldDivideIfFollowdBySlashSign() {
        calculator = new Calculator(new Scanner("10 5 /"), printStream);
        calculator.run();
        assertThat(outputStream.toString(), equalTo("2\n"));
    }

    @Test
    public void runShouldAddThenSubtractAndThenMultiplyDoubleNumbers() {
        calculator = new Calculator(new Scanner("2.3 5.5 6.2 + - 3.6 * 6.7"), printStream);
        calculator.run();
        assertThat(outputStream.toString(), equalTo("-33.84\n6.7\n"));
    }

    @Test
    public void runShouldErrorIfBeginsWithAnOperator() {
        calculator = new Calculator(new Scanner("-"), printStream);
        calculator.run();
        assertThat(outputStream.toString(), equalTo("Please enter valid input\n"));
    }

    @Test
    public void runShouldErrorIf1NumberFollowedByOperator() {
        calculator = new Calculator(new Scanner("5 +"), printStream);
        calculator.run();
        assertThat(outputStream.toString(), equalTo("Please enter valid input\n"));
    }

    @Test
    public void runShouldErrorIfMoreOperatorsThanNumbers() {
        calculator = new Calculator(new Scanner("3 3 / - +"), printStream);
        calculator.run();
        assertThat(outputStream.toString(), equalTo("Please enter valid input\n"));
    }
}
