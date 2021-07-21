package com.fjj.designPatterns;

/**
 * @author: fjjdragon
 * @date: 2019-11-21 10:29
 */
public class Base {
    String name = "Base";

    public void foo(Base this) {
        System.out.println("Base . foo()");
        return;
    }

    public void test() {
        System.out.println("Base . test()");
        this.foo();
        return;
    }
}