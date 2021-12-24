package com.ruiya.util.filedownloadzip;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/17 17:56
 * @history:
 */
public class JEditorPane3 {

    public static void main(String[] args) {
        JEditorPane editPane = null;
        try {
            URL address = new URL("http://www.sina.com.cn");
            editPane = new JEditorPane(address);
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL:" + e);
        } catch (IOException e) {
            System.out.println("IOException:" + e);
        }
        editPane.setEditable(false);



//        JFrame f = new JFrame("JEditorPane3");
//        f.setContentPane(new JScrollPane(editPane));
//        f.setSize(200, 250);
//        f.show();
//        f.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });
    }
}
