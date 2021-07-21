package com.fjj.pack;

import com.fjj.util.ArraysUtil;
import lombok.Getter;

/**
 * DP系列——背包问题
 *
 * @author: fjjdragon
 * @date: 2019-08-28 17:38
 */
@Getter
public class Pack {
    private int[] Wi = {0, 2, 2, 6, 5, 4};
    private int[] Vi = {0, 6, 3, 5, 4, 6};
    private int N = 5, W = 10;
    private int[][] F;
    private int[] item = new int[N + 1];


    /**
     * 根据状态公式，生成记号表
     */
    public void first() {
        F = new int[N + 1][W + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= W; j++) {
                if (j - Wi[i] < 0) {
//                    F[i][j] = F[i - 1][j];
                    continue;
                }
                F[i][j] = Math.max(F[i - 1][j], F[i - 1][j - Wi[i]] + Vi[i]);
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
                } else if (j - Wi[i] >= 0 && F[i][j] == F[i - 1][j - Wi[i]] + Vi[i]) {
                    item[i] = 1;
                    second(i - 1, j - Wi[i]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}