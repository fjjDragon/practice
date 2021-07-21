package com.fjj.designPatterns.ISP;

/**
 * @author: fjjdragon
 * @date: 2019-11-20 16:41
 */
public class Order implements OrderA, OrderB, OrderC {
    private Order() {

    }

    public static OrderA OrderA() {
        return (OrderA) new Order();
    }

    public static OrderB OrderB() {
        return (OrderB) new Order();
    }

    public static OrderC OrderC() {
        return (OrderC) new Order();
    }

    @Override
    public String insertOrder() {
        return "insertOrder";
    }

    @Override
    public String getOrder() {
        return "getOrder";
    }

    @Override
    public String deleteOrder() {
        return "deleteOrder";
    }
}