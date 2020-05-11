package com.enjoy.asminject;


public class InjectTest {

    @ASMTest
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 0) {
            System.out.println("hi");
        }
    }

}
