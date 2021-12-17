package com.ruiya.net;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/13 21:09
 * @history:
 */
public class TalkStudent {

    public static void main(String[] args) {
        new Thread(new TalkSend("localhost", 9999,  7777)).start();

        new Thread(new TalkReceive(8888, "老师")).start();

    }
}
