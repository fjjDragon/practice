package com.fjj.test;

import com.fjj.pack.Pack;

import java.util.Arrays;

/**
 * @author: fjjdragon
 * @date: 2019-08-29 17:38
 */

public class Test1 {

    public static void main(String[] args) {
        Pack pack = new Pack();
        pack.first();
        pack.second(pack.getN(), pack.getW());
        System.out.println(Arrays.toString(pack.getItem()));
    }

}