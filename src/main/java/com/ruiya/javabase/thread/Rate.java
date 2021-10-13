package com.ruiya.javabase.thread;

import lombok.SneakyThrows;

public class Rate implements Runnable{

    private static String winner;

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if ("liusi".equals(Thread.currentThread().getName()) &&  1 %10 == 0){
                Thread.sleep(100000);
            }
            if (gameover(i)){
                break;
            }
            System.out.println(Thread.currentThread().getName() + "->>> mia"+ i);
        }
    }
    private boolean gameover(int steps) {
        if (winner != null){
            return true;
        }
        if (steps>=100){
            winner = Thread.currentThread().getName();
            System.out.println("winner = " + winner);
            return true;
        }
        return  false;
    }

    public static void main(String[] args) {
        Rate rate = new Rate();
        new Thread(rate, "wu").start();
        new Thread(rate, "liusi").start();
    }
}
