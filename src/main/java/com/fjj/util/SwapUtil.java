package com.fjj.util;

/**
 * @author: fjjdragon
 * @date: 2019-08-27 14:38
 */
public class SwapUtil {

    public static void ArraySwapIndex(int[] arr, int i, int j) {
        if (i == j) {
//            System.out.println("arr index i==j");
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
        /**
         *second method
         */
        // int tmp = arr[i];
        // arr[i] = arr[j];
        // arr[j] = tmp;
        /**
         *third method
         */
        // x = x + y;
        // y = x - y;
        // x = x - y;
    }

    /**
     * swap:  x->y,y->z,z->x
     * before: x,y,z;
     * after: z,x,y;
     *
     * @param arr
     * @param x
     * @param y
     * @param z
     */
    public static void ArraySwapIndex_XYZ_2_ZXY(int[] arr, int x, int y, int z) {
        ArraySwapIndex(arr, x, z);
        ArraySwapIndex(arr, y, z);
    }
}