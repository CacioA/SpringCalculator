package com.cacioA.Calculator;

import com.cacioA.Calculator.entity.Calculator;
import com.cacioA.Calculator.service.CalculatorServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

//@SpringBootTest
@ContextConfiguration(classes = CalculatorApplication.class)
@WebMvcTest(controllers= Calculator.class)
class CalculatorApplicationTests {



	@InjectMocks
	private CalculatorServiceImpl calculatorServiceImpl;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}


}
