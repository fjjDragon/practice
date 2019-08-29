package com.fjj.pack;

import com.fjj.util.ArraysUtil;
import lombok.Data;

/**
 * DP系列——背包问题
 *
 * @author: fjjdragon
 * @date: 2019-08-28 17:38
 */
@Data
public class Pack {
    private int[] w = {0, 1, 2, 2, 3, 4};
    private int[] value = {0, 2, 3, 3, 4, 5};
    private int N = 5, V = 8;
    private int[][] F;
    private int[] item = new int[N + 1];


    /**
     * 根据状态公式，生成记号表
     */
    public void first() {
        F = new int[N + 1][V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                if (j - w[i] < 0) continue;
                F[i][j] = Math.max(F[i - 1][j], F[i - 1][j - w[i]] + value[i]);
            }
        }
//        System.out.println(Arrays.deepToString(F));
        ArraysUtil.format_printTo2DArray(F);
    }

    /**
     * 最优解回溯
     *
     * @param i
     * @param j
     */
    public void second(int i, int j) {
        if (i > 0) {
            try {
                if (F[i][j] == F[i - 1][j]) {
                    item[i] = 0;
                    second(i - 1, j);
                } else if (j - w[i] >= 0 && F[i][j] == F[i - 1][j - w[i]] + value[i]) {
                    item[i] = 1;
                    second(i - 1, j - w[i]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}