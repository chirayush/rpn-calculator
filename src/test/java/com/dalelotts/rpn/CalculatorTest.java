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

    private final OutputStream outPutStream = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(outPutStream);
    private Calculator calculator;

    @Before
    public void before() {

    }

    @Test
    public void runShouldOutputTheSameInput() {

        calculator = new Calculator(new Scanner("3"), printStream);
        calculator.run();

        assertThat(outPutStream.toString(), equalTo("3\n"));
    }

    @Test
    public void runShouldOutputErrorMessageWhenOnlyOperatorEntered() {
        calculator = new Calculator(new Scanner("+"), printStream);
        calculator.run();

        assertThat(outPutStream.toString(), equalTo("Please enter an integer as the 1st 2 inputs\n"));
    }

    @Test
    public void runShouldOutputErrorMessageWhenANumberAndOperatorEntered() {
        calculator = new Calculator(new Scanner("5 +"), printStream);
        calculator.run();

        assertThat(outPutStream.toString(), equalTo("Please enter an integer as the 1st 2 inputs\n"));
    }

    @Test
    public void runShouldOutput2NumbersWhen2NumbersEntered() {
        calculator = new Calculator(new Scanner("3 5"), printStream);
        calculator.run();

        assertThat(outPutStream.toString(), equalTo("3\n5\n"));
    }

    @Test
    public void runShouldOutputTheSumWhen2NumbersWithSumOperatorEntered() {
        calculator = new Calculator(new Scanner("6 8 +"), printStream);
        calculator.run();

        assertThat(outPutStream.toString(), equalTo("14\n"));
    }

    @Test
    public void runShouldOutputTheSumWhen3NumbersWithSumOperatorEntered() {
        calculator = new Calculator(new Scanner("5 8 10 +"), printStream);
        calculator.run();

        assertThat(outPutStream.toString(), equalTo("23\n"));
    }

    @Test
    public void runShouldOutputSumAndOtherNumbersAfterOperator() {
        calculator = new Calculator(new Scanner("2 4 + 5"), printStream);
        calculator.run();

        assertThat(outPutStream.toString(), equalTo("6\n5\n"));
    }

    @Test
    public void runShouldOutputSumOfAllNumbersBefore2PlusOperators() {
        calculator = new Calculator(new Scanner("2 4 + 5 7 +"), printStream);
        calculator.run();

        assertThat(outPutStream.toString(), equalTo("6\n12\n"));
    }
}