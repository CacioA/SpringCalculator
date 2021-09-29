package com.cacioA.Calculator.service;


import com.cacioA.Calculator.DAO.DaoCalcHistoryImpl;
import com.cacioA.Calculator.entity.Calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculatorServiceImpl {


    @Autowired
    private DaoCalcHistoryImpl daoCalcHistory;


    public String checkNumberFormat(double number){

        //Check number of digits
        boolean reversed = false;

        //Negative numbers would not work with any data type out of the box
        //reversing if negative
        if(number<0) {
            number *=  -1;

            reversed = true;

        }

        final int numberOfDigits = 1 + (int) Math.floor(Math.log10(number));

        if (numberOfDigits > 9) {
            return "E";
        }

        // reverse back to initial number
        if(reversed==true) {
            number *= -1;
        }


        int comparisonNumber = (int)number;

        if( number == comparisonNumber){

            return String.format("%d",(long)number);
        }
        else{
            return String.format("%s",number);
        }
    }


    public String add(Calculator calculator){

        daoCalcHistory.addNumber(calculator);

        return checkNumberFormat(round(daoCalcHistory.getTotalSum()));
    }


    public String subtract(Calculator calculator){
        daoCalcHistory.subtractNumber(calculator);
        return checkNumberFormat(round(daoCalcHistory.getTotalSum()));
    }


    public String divide(Calculator calculator){
        if(calculator.getSecondNumber() == 0){
                return "undefined";
        }
        daoCalcHistory.divideNumber(calculator);
        return checkNumberFormat(round(daoCalcHistory.getTotalSum()));

    }


    public String multiply(Calculator calculator){
        daoCalcHistory.multiplyNumber(calculator);
        return checkNumberFormat(round(daoCalcHistory.getTotalSum()));
    }


    public String sqrt(Calculator calculator){
        daoCalcHistory.doSqrt(calculator);
        return  checkNumberFormat(round(daoCalcHistory.getTotalSum()));
    }


    public void clear(){
        daoCalcHistory.clearMemory();
    }


    public List<String> getCalculationsHistory(){
        return daoCalcHistory.getAllCalculations();
    }


    public String getTotalSum() {
        return checkNumberFormat(round(daoCalcHistory.getTotalSum()));
    }


    public double round(double number){
        return Math.round(number*100.00)/100.00;
    }
}
