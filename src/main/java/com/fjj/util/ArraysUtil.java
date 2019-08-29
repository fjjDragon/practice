package com.fjj.util;

import java.util.Arrays;

/**
 * @author: fjjdragon
 * @date: 2019-08-28 18:06
 */
public class ArraysUtil {
    public static void format_printTo2DArray(Object[] arr) {
        if (null == arr || arr.length == 0) {
            System.out.println("null");
            return;
        }
        int len = arr.length;
        StringBuilder buf = new StringBuilder(len * 20);
        buf.append('[');
        for (int i = 0; i < len; i++) {
            Object element = arr[i];
            if (element == null) {
                buf.append("null");
            } else {
                Class<?> eClass = element.getClass();
                if (eClass.isArray()) {
                    toString(eClass, element, buf);
                } else {  // element is non-null and not an array
                    buf.append(element.toString());
                }
            }
            if (i == len - 1)
                break;
            buf.append(",\r\n ");
        }
        buf.append(']');
        System.out.println(buf.toString());
    }

    private static void toString(Class<?> eClass, Object element, StringBuilder buf) {
        if (eClass == byte[].class) buf.append(Arrays.toString(((byte[]) element)));
        else if (eClass == short[].class) buf.append(Arrays.toString(((short[]) element)));
        else if (eClass == int[].class) buf.append(Arrays.toString(((int[]) element)));
        else if (eClass == long[].class) buf.append(Arrays.toString(((long[]) element)));
        else if (eClass == char[].class) buf.append(Arrays.toString(((char[]) element)));
        else if (eClass == float[].class) buf.append(Arrays.toString(((float[]) element)));
        else if (eClass == double[].class) buf.append(Arrays.toString(((double[]) element)));
        else if (eClass == boolean[].class) buf.append(Arrays.toString(((boolean[]) element)));
        else buf.append(element.toString());
    }


}