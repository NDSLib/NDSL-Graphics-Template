package com.ndsl.graphics.templates.util;

public class SoutRunnable implements Runnable{
    private String s;
    public SoutRunnable(String s){this.s=s;}

    @Override
    public void run() {
        System.out.println(s);
    }
}
