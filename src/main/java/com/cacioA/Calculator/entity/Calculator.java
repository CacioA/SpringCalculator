package com.cacioA.Calculator.entity;



import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Component
public class Calculator {


    private double number;
    private double secondNumber;
    private String operation;
    private String firstStringNumber;

    public Calculator(){

    }

    public Calculator(double number, String operation){
        this.number=number;
        this.operation=operation;
    }

    public Calculator(double number){
        this.number=number;
    }

    public Calculator(String firstStringNumber){
        this.firstStringNumber=firstStringNumber;
    }

    public double getNumber() {
        return this.number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public double getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(double secondNumber) {
        this.secondNumber = secondNumber;
    }

    public String getFirstStringNumber() {
        return firstStringNumber;
    }

    public void setFirstStringNumber(String firstStringNumber) {
        this.firstStringNumber = firstStringNumber;
    }

    public void setOperation(String operation){
        this.operation=operation;
    }

    public String getOperation(){
        return this.operation;
    }



}
