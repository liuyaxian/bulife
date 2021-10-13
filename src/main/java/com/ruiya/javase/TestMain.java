package com.ruiya.javase;

public class TestMain {
    public static void main(String[] args) {

        char [] numbeCharArr = {'1','3','5','8','0','-','2'};

        char [] numberNewCharArr = {'1','3','5','8','0','-','2'};
        for (int i = 0; i < numbeCharArr.length; i++) {
            boolean notZero;
            for (int j = 0; j < numberNewCharArr.length; j++) {
                if (numberNewCharArr[j] != '0'){
                    notZero = true;
                    System.out.println("numberNewCharArr = " + numberNewCharArr[j]);
                    break;
                }
            }
            System.out.println("numbeCharArr = " + numbeCharArr[i]);
        }
    }
}
