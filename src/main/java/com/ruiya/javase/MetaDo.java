package com.ruiya.javase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetaDo {
    public static void main(String[] args) {
//        int i  =23;
//        int a =  i & 255;
//        System.out.println("aL: "+a);
//
//        System.out.println("v&255: "+( i>>>8 & 255) );
//        System.out.println("v>>> "+ (a>>>8));
//        System.out.println("aL: "+a);

//        System.out.println("a & '\\uffff' = " +( i & '\uffff'));

//        System.out.println("（>>2） = " + (i>>2));

        Pattern pattern = Pattern.compile("[a-zA-Z0-9]");
        String  str = "0SFSJKSJF3424";
        boolean matches = str.matches("[a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(str);


    }
    static final String REGEX_VARIABLE = "[a-zA-Z_]{1,}[a-zA-Z0-9_-]*";
    static final String REGEX_COMMAND = "[:]{0,1}[a-zA-Z]{1,}[a-zA-Z0-9_-]*";
     String getCommand(final String line) {
        String out = "";
        Pattern  patternCommand = Pattern.compile("^\\s*" + REGEX_VARIABLE + "=(" + REGEX_COMMAND + ")(\\s+.*|$)");
        Matcher matcher = patternCommand.matcher(line);
        if (matcher.find()) {
            out = matcher.group(1);
        } else {
            out = line.trim().split("\\s+")[0];
            int idx = out.indexOf("=");
            if (idx > -1) {
                out = out.substring(idx + 1);
            }
            if (!out.matches(REGEX_COMMAND)) {
                out = "";
            }
        }
        return out;
    }


}
