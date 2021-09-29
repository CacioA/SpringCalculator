package com.cacioA.Calculator.DAO;

import com.cacioA.Calculator.entity.Calculator;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DaoCalcHistoryImpl {

    private double totalSum=0;
    private List<String> calcHistory = new ArrayList<>();





    public void saveCalculation(Calculator calculator) {
        if(calculator.getFirstStringNumber().isBlank()){
            this.calcHistory.add(calculator.getOperation()+ calculator.getSecondNumber());
            return;
        }
        else if(calculator.getSecondNumber()==0){
            this.calcHistory.add(calculator.getOperation()+ calculator.getNumber());
            return;
        }
        this.calcHistory.add(calculator.getNumber()+calculator.getOperation()+ calculator.getSecondNumber());

    }


    public void addNumber(Calculator calculator) {
        if(calculator.getFirstStringNumber().isBlank()){
            this.totalSum+=calculator.getSecondNumber();
        }
       else{
            calculator.setNumber(Double.parseDouble(calculator.getFirstStringNumber()));
           this.totalSum=calculator.getNumber()+ calculator.getSecondNumber();
       }
        saveCalculation(calculator);
    }


    public void subtractNumber(Calculator calculator) {
        if(calculator.getFirstStringNumber().isBlank()) {
            this.totalSum -= calculator.getSecondNumber();
        }
        else{
            calculator.setNumber(Double.parseDouble(calculator.getFirstStringNumber()));
            this.totalSum=calculator.getNumber()-calculator.getSecondNumber();
        }
        saveCalculation(calculator);
    }


    public void divideNumber(Calculator calculator) {
        if(calculator.getFirstStringNumber().isBlank()) {

            this.totalSum /= calculator.getSecondNumber();
            saveCalculation(calculator);
            return;

            }

        else{
            calculator.setNumber(Double.parseDouble(calculator.getFirstStringNumber()));
            this.totalSum=calculator.getNumber()/calculator.getSecondNumber();
        }
        saveCalculation(calculator);
    }


    public void multiplyNumber(Calculator calculator) {
        if(calculator.getFirstStringNumber().isBlank()) {
            this.totalSum *= calculator.getSecondNumber();
        }
        else{
            calculator.setNumber(Double.parseDouble(calculator.getFirstStringNumber()));
            this.totalSum=calculator.getNumber()*calculator.getSecondNumber();
        }

        saveCalculation(calculator);
    }


    public double doSqrt(Calculator calculator) {
        double result = Double.parseDouble(calculator.getFirstStringNumber());
        if(result < 0) { return 0;}
        this.totalSum=Math.sqrt(result);
        calculator.setNumber(result);

        saveCalculation(calculator);
        return this.totalSum;

    }


    public double getTotalSum(){

        return this.totalSum;

    }


    public void clearMemory() {
        this.totalSum=0;
        this.calcHistory.clear();
    }



    public List<String> getAllCalculations() {
       return calcHistory;
    }
}
