package org.example;

public class Main {
    public static void main(String[] args) {
        String s = "\r\n10\r\n";
        String t = s.replace("\r\n","");
        int i = Integer.parseInt(t);
        System.out.println("Hello, World!");
    }
}