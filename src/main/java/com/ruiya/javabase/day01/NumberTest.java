package com.ruiya.javabase.day01;

/***
 *
 */
public class NumberTest {


    public static void main(String[] args) {

        System.out.println("args 1= " + numberOfLeadingZeros(0));
        System.out.println("args 2= " + numberOfLeadingZeros(1));
        System.out.println("args 3= " + numberOfLeadingZeros(2));
        System.out.println("args 4= " + numberOfLeadingZeros(3));
        System.out.println("args 5= " + numberOfLeadingZeros(4));
        System.out.println("args 6= " + numberOfLeadingZeros(5));

    }



    public static int numberOfLeadingZeros(int i) {
        // HD, Count leading 0's
        if (i <= 0)
            return i == 0 ? 32 : 0;
        int n = 31;
        if (i >= 1 << 16) {
            n -= 16;
            i >>>= 16;
        }
        if (i >= 1 << 8) {
            n -= 8;
            i >>>= 8;
        }
        if (i >= 1 << 4) {
            n -= 4;
            i >>>= 4;
        }
        if (i >= 1 << 2) {
            n -= 2;
            i >>>= 2;
        }
        return n - (i >>> 1);
    }


}