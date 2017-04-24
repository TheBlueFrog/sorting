package com.mike.exp;

/**
 * Created by mike on 3/24/2017.
 */
public class Log {

    public static void d(String tag, String msg) {
        System.out.println(String.format("%30s:%s", tag, msg));
    }
}
