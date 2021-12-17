package com.ruiya.net;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/13 21:10
 * @history:
 */
public class TalkTeacher {
    public static void main(String[] args) {
        new Thread(new TalkSend("localhost", 8888, 5555)).start();


        new Thread(new TalkReceive(9999, "学生")).start();
    }
}
