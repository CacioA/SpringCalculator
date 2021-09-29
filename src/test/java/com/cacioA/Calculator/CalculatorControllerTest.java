package com.cacioA.Calculator;

import com.cacioA.Calculator.controller.CalculatorController;
import com.cacioA.Calculator.entity.Calculator;
import com.cacioA.Calculator.service.CalculatorServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@AutoConfigureMockMvc
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CalculatorServiceImpl calculatorServiceImpl;


    private Calculator calculator;


    @Before()
    public void init(){
        this.calculator = new Calculator();
        this.calculator.setFirstStringNumber("10");
        this.calculator.setSecondNumber(5);
    }

    // Tests to check if the data is passed correctly to the service layer and added to the model

    @Test
    public void addNumberPOST() throws Exception{
        calculator.setOperation("+");


        this.mockMvc.perform(
                post("/calculator?add=").flashAttr("calc",calculator));

        this.mockMvc.perform(
                post("/calculator?equal=").flashAttr("calc",calculator)

                ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("result",
                        Matchers.equalTo(calculatorServiceImpl.getTotalSum())))
                .andExpect(MockMvcResultMatchers.model().attribute("historyRes",
                        Matchers.equalTo(calculatorServiceImpl.getCalculationsHistory())));

    }

    @Test
    public void subtractNumberPOST() throws Exception{
        calculator.setOperation("-");

        List<String> historyResultComp = calculatorServiceImpl.getCalculationsHistory();

        this.mockMvc.perform(
                post("/calculator?subtract").flashAttr("calc",calculator));
        this.mockMvc.perform(
                        post("/calculator?equal=").flashAttr("calc",calculator)

                ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("result",
                        Matchers.equalTo(calculatorServiceImpl.getTotalSum())))
                .andExpect(MockMvcResultMatchers.model().attribute("historyRes",
                        Matchers.equalTo(calculatorServiceImpl.getCalculationsHistory())));

    }

    @Test
    public void divideNumberWithZero() throws Exception{


        calculator.setOperation("/");
        calculator.setFirstStringNumber("10");


        this.mockMvc.perform(
                post("/calculator?divide=").flashAttr("calc",calculator));

        calculator.setFirstStringNumber("0");
        this.mockMvc.perform(
                        post("/calculator?equal=").flashAttr("calc",calculator)

                ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("result",

                        Matchers.equalTo("undefined")))
                .andExpect(MockMvcResultMatchers.model().attribute("historyRes",
                        Matchers.equalTo(calculatorServiceImpl.getCalculationsHistory())));

    }

    @Test
    public void multiplyNumberPOST() throws Exception{
        // Add number so init sum != 0
        calculator.setOperation("*");
        calculator.setFirstStringNumber("15");
        calculator.setSecondNumber(3);

        this.mockMvc.perform(post("/calculator?multiply=").flashAttr("calc",calculator));



        this.mockMvc.perform(
                post("/calculator?equal=").flashAttr("calc",calculator))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("result",
                        Matchers.equalTo(calculatorServiceImpl.getTotalSum())))
                .andExpect(MockMvcResultMatchers.model().attribute("historyRes",
                        Matchers.equalTo(calculatorServiceImpl.getCalculationsHistory())));
    }

    @Test
    public void getSqrtFromInteger() throws Exception{


        calculator.setFirstStringNumber("25");
        calculator.setOperation("sqrt ");
        this.mockMvc.perform(post("/calculator?squareroot").flashAttr("calc",calculator))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("result",
                        Matchers.equalTo(calculatorServiceImpl.getTotalSum())))
                .andExpect(MockMvcResultMatchers.model().attribute("historyRes",
                        Matchers.equalTo(calculatorServiceImpl.getCalculationsHistory())));
    }





}
