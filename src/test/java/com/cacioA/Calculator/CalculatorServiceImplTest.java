package com.cacioA.Calculator;

import com.cacioA.Calculator.entity.Calculator;
import com.cacioA.Calculator.service.CalculatorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@AutoConfigureMockMvc
public class CalculatorServiceImplTest {



    @Autowired
    MockMvc mockMvc;

    @Autowired
    private CalculatorServiceImpl calculatorServiceImpl;



    private Calculator calculator;

    @Before
    public void init(){
        this.calculator = new Calculator();
        this.calculator.setFirstStringNumber("10");
        this.calculator.setSecondNumber(5);
        this.calculatorServiceImpl.clear();
    }

    @Test
    public void add(){

        String result = calculatorServiceImpl.add(calculator);
        assertEquals("15", result);
    }
    @Test
    public void multiplyWithNonZeroSum(){



        // Set both numbers for multiplication
        calculator.setFirstStringNumber("5");
        calculator.setSecondNumber(10);
        String result = calculatorServiceImpl.multiply(calculator);

        assertEquals("50", result);

    }


    @Test
    public void multiplyWithZero(){

        // Change value and multiply
        calculator.setFirstStringNumber("0");
        calculator.setSecondNumber(10);
        String result = calculatorServiceImpl.multiply(calculator);

        assertEquals("0", result);
    }
    @Test
    public void multiplyWithSum(){

        // Increase sum and leave first number blank so it is considered a continued operation
        calculator.setFirstStringNumber("20");
        calculator.setSecondNumber(10);
        calculatorServiceImpl.add(calculator);

        calculator.setFirstStringNumber("");


        String result = calculatorServiceImpl.multiply(calculator);

        assertEquals("300", result);
    }


    @Test
    public void divideIntegerNumbers(){

        calculator.setFirstStringNumber("100");
        calculator.setSecondNumber(2);

        String result = calculatorServiceImpl.divide(calculator);

        assertEquals("50", result);


    }

    @Test
    public void divideDecimalNumbers(){

        calculator.setFirstStringNumber("10");
        calculator.setSecondNumber(4);
        String result = calculatorServiceImpl.divide(calculator);

        assertEquals("2.5", result);
    }

    @Test
    public void divideByZero(){

        calculator.setNumber(30);
        calculator.setSecondNumber(0);
        String result = calculatorServiceImpl.divide(calculator);

        assertEquals("undefined", result);
    }

    @Test
    public void moreThan9Digits(){
        calculator.setFirstStringNumber("1111111111");
        calculator.setSecondNumber(10000);


        String result = calculatorServiceImpl.add(calculator);

        assertEquals("E", result);
    }


    @Test
    public void clearMemory(){
        calculatorServiceImpl.add(calculator);
        calculatorServiceImpl.clear();


        String result = calculatorServiceImpl.getTotalSum();
        List<String> historyResult = calculatorServiceImpl.getCalculationsHistory();

        assertEquals("0", result);
        List<String> compareHistory = new ArrayList<>();

        //the history list should be empty
        assertEquals(compareHistory,historyResult);

    }

    @Test
    public void checkFormatIfNumberHasDecimals(){
        calculatorServiceImpl.add(calculator);


    }

    @Test
    public void wasOperationWasAdded(){
        calculator.setOperation("+");
        calculator.setNumber(90);
        calculator.setSecondNumber(50);
        calculatorServiceImpl.add(calculator);

        List<String> compareHistory = new ArrayList<>();
        compareHistory.add(calculator.getNumber()+calculator.getOperation()+calculator.getSecondNumber());

        calculator.setFirstStringNumber("");
        calculator.setSecondNumber(2);
        calculator.setOperation("/");
        calculatorServiceImpl.divide(calculator);

        List<String> historyResult = calculatorServiceImpl.getCalculationsHistory();

        compareHistory.add(calculator.getOperation()+calculator.getSecondNumber());

        assertEquals(compareHistory,historyResult);

    }

    @Test
    public void roundDecimalNumber(){

        double result  = calculatorServiceImpl.round(25.1433);

        assertEquals(25.14,result);
    }

    @Test
    public void roundDecimalNumberUp(){

        double result  = calculatorServiceImpl.round(30.1563);

        assertEquals(30.16,result);
    }




}
