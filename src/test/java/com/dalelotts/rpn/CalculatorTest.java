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
    }

    @Test
    public void runShouldPrintNumberIfANumberIsEntered() {
        calculator = new Calculator(new Scanner("3"), printStream);
        calculator.run();
        assertThat(outputStream.toString(), equalTo("[3]\n"));
    }

    @Test
    public void runShouldPrint2NumbersWhen2NumbersEntered() {
        calculator = new Calculator(new Scanner("3 5"), printStream);
        calculator.run();
        String[] output = outputStream.toString().split(System.getProperty("line.separator"));
        assertThat(output[0], equalTo("[3]"));
        assertThat(output[1], equalTo("[3][5]"));
    }

    @Test
    public void runShouldAddNumbersWhen2NumbersFollowedByAPlusSign() {
        calculator = new Calculator(new Scanner("5 7 +"), printStream);
        calculator.run();
        String[] output = outputStream.toString().split(System.getProperty("line.separator"));
        assertThat(output[0], equalTo("[5]"));
        assertThat(output[1], equalTo("[5][7]"));
        assertThat(output[2], equalTo("[12]"));
    }

    @Test
    public void runShouldPrintGoodbyeOnQ() {
        calculator = new Calculator(new Scanner("Q"), printStream);
        calculator.run();
        assertThat(outputStream.toString(), equalTo("Goodbye"));
    }

    @Test
    public void runShouldThrowExceptionIfThe1stParameterIsAnOperator() {
        calculator = new Calculator(new Scanner("+"), printStream);
        calculator.run();
        assertThat(outputStream.toString(), equalTo("2 operands are required\n\n"));
    }

}
