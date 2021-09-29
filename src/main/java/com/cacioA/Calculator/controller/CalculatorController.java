package com.cacioA.Calculator.controller;


import com.cacioA.Calculator.entity.Calculator;

import com.cacioA.Calculator.service.CalculatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class CalculatorController {



    @Autowired
    private CalculatorServiceImpl calculatorServiceImpl;

    @Autowired
    private Calculator calc;
    private double firstNumber;
    private double secondNumber;

    @RequestMapping("/calculator")
    public String calculatorPage(Model model){
        calculatorServiceImpl.clear();
       model.addAttribute("calc",new Calculator());
        model.addAttribute("result",0);
        return "mainScreen";
    }

    @GetMapping("/clear")
    public String clearMemory(){
        calculatorServiceImpl.clear();
        return "mainScreen";
    }


    @RequestMapping(value="/calculator", method = RequestMethod.POST, params="add")
    public String add(@Valid @ModelAttribute("calc")  Calculator calculator,
                      BindingResult result, Model model ){
        if(result.hasErrors()){
            //perform operation with 0
        }
        this.calc.setFirstStringNumber(calculator.getFirstStringNumber());


        model.addAttribute("historyRes", calculatorServiceImpl.getCalculationsHistory());


        return "mainScreen";
    }

    @RequestMapping(value="/calculator", method = RequestMethod.POST,params="subtract")
    public String subtract(@Valid @ModelAttribute("calc")  Calculator calculator,
                           BindingResult result, Model model ){

        if(result.hasErrors()){

        }
        this.calc.setFirstStringNumber(calculator.getFirstStringNumber());


        model.addAttribute("historyRes", calculatorServiceImpl.getCalculationsHistory());

        return "mainScreen";
    }

    @RequestMapping(value="/calculator", method = RequestMethod.POST,params="divide")
    public String divide(@Valid @ModelAttribute("calc")  Calculator calculator,
                         BindingResult result, Model model ){

        if(result.hasErrors()){

        }

        this.calc.setFirstStringNumber(calculator.getFirstStringNumber());

        model.addAttribute("historyRes", calculatorServiceImpl.getCalculationsHistory());

        return "mainScreen";
    }

    @RequestMapping(value="/calculator", method = RequestMethod.POST, params="multiply")
    public String multiply(@Valid @ModelAttribute("calc")  Calculator calculator,
                           BindingResult result, Model model ){
        if(result.hasErrors()){

        }
        this.calc.setFirstStringNumber(calculator.getFirstStringNumber());


        model.addAttribute("historyRes", calculatorServiceImpl.getCalculationsHistory());

        return "mainScreen";
    }


    @RequestMapping(value="/calculator", method = RequestMethod.POST,params="squareroot")
    public String sqrt(@Valid @ModelAttribute("calc")  Calculator calculator,
                       BindingResult result, Model model ){

        if(result.hasErrors()){

        }


        model.addAttribute("result", calculatorServiceImpl.sqrt(calculator));
        model.addAttribute("historyRes", calculatorServiceImpl.getCalculationsHistory());

        return "mainScreen";
    }

    @RequestMapping(value="/calculator",method = RequestMethod.POST,params="equal")
    public String equal(@Valid @ModelAttribute("calc") Calculator calculator,
                        BindingResult result, Model model){

        if(result.hasErrors()){

        }


        if(calculator.getFirstStringNumber().equals("")){
            model.addAttribute("result","No second number selected. Please clear and try again.");
            return "mainScreen";
        }
        this.calc.setSecondNumber(Double.parseDouble((calculator.getFirstStringNumber())));

        if(calculator.getOperation().equals("+")){
            this.calc.setOperation(calculator.getOperation());
            model.addAttribute("result",calculatorServiceImpl.add(this.calc));

        }

        else if(calculator.getOperation().equals("-")){
            this.calc.setOperation(calculator.getOperation());
            model.addAttribute("result",calculatorServiceImpl.subtract(this.calc));

        }

        else if(calculator.getOperation().equals("/")){
            this.calc.setOperation(calculator.getOperation());
            model.addAttribute("result",calculatorServiceImpl.divide(this.calc));
        }

        else if(calculator.getOperation().equals("*")){
            this.calc.setOperation(calculator.getOperation());
            model.addAttribute("result",calculatorServiceImpl.multiply(this.calc));
        }

        model.addAttribute("historyRes",calculatorServiceImpl.getCalculationsHistory());
        return "mainScreen";
    }

    @RequestMapping(value="/calculator",method = RequestMethod.POST,params="answer")
    public String answer(@Valid @ModelAttribute("calc") Calculator calculator,
                         BindingResult result, Model model){

        model.addAttribute("inputValue",calculatorServiceImpl.getTotalSum());
        model.addAttribute("historyRes",calculatorServiceImpl.getCalculationsHistory());
        this.calc.setFirstStringNumber("");
        return "mainScreen";
    }

    }





