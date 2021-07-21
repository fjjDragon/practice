package com.fjj.designPatterns.ISP;

/**
 * @author: fjjdragon
 * @date: 2019-11-20 16:43
 */
public class Test {

    public static void main(String[] args) {
        OrderA orderA = Order.OrderA();
        OrderB orderB = Order.OrderB();
        OrderC orderC = Order.OrderC();
        System.out.println(orderC.getOrder());
    }
}