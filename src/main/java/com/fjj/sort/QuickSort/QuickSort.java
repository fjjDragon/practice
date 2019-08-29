package com.fjj.sort.QuickSort;

import com.fjj.util.SwapUtil;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序的几种实现对比
 * <p>
 * 对于重复数比较多的样本数据，采用三向切分快排性能较好
 *
 * @author: fjjdragon
 * @date: 2019-08-27 14:36
 */
public class QuickSort {
    public static int[] duration;

    static {
        Random random = new Random();
        duration = new int[1000000];
        for (int i = 0; i < duration.length; i++) {
            duration[i] = random.nextInt(1000);
        }
    }

    public static void main(String[] args) {

        int arr[] = Arrays.copyOf(duration, duration.length);
        long st = System.currentTimeMillis();
        quickSort1(arr, 0, arr.length - 1);
        long et = System.currentTimeMillis();
        System.out.println(/*Arrays.toString(arr) + */" 两端扫描交换 time :" + (et - st));

        arr = Arrays.copyOf(duration, duration.length);
        st = System.currentTimeMillis();
        quickSort2(arr, 0, arr.length - 1);
        et = System.currentTimeMillis();
        System.out.println(/*Arrays.toString(arr) + */" 一端挖坑，另一端填补time :" + (et - st));

        arr = Arrays.copyOf(duration, duration.length);
        st = System.currentTimeMillis();
        quickSort3(arr, 0, arr.length - 1);
        et = System.currentTimeMillis();
        System.out.println(/*Arrays.toString(arr) + */" 单端扫描快速排序time :" + (et - st));

        arr = Arrays.copyOf(duration, duration.length);
        st = System.currentTimeMillis();
        quickSort4(arr, 0, arr.length - 1);
        et = System.currentTimeMillis();
        System.out.println(/*Arrays.toString(arr) +*/ " 三向切分快速排序time :" + (et - st));

        arr = Arrays.copyOf(duration, duration.length);
        st = System.currentTimeMillis();
        quickSort5(arr, 0, arr.length - 1);
        et = System.currentTimeMillis();
        System.out.println(/*Arrays.toString(arr) +*/ " 双轴快速排序①time :" + (et - st));

        arr = Arrays.copyOf(duration, duration.length);
        st = System.currentTimeMillis();
        QuickSortDualPivot(arr, 0, arr.length - 1);
        et = System.currentTimeMillis();
        System.out.println(/*Arrays.toString(arr) + */" 双轴快速排序②time :" + (et - st));
    }


    //从两端扫描交换的方式
    public static void quickSort1(int[] arr, int l, int r) {
        if (l >= r) return;
        int pivot = arr[l];
        int i = l + 1, j = r;
        while (i <= j) {
            while (i <= j && arr[i] <= pivot) {
                i++;
            }
            while (i <= j && arr[j] > pivot) {
                j--;
            }
            if (i < j) {
                SwapUtil.ArraySwapIndex(arr, i, j);
                i++;
                j--;
            }
        }
        if (arr[l] > arr[j])
            SwapUtil.ArraySwapIndex(arr, l, j);

        quickSort1(arr, l, j - 1);
        quickSort1(arr, j + 1, r);
    }

    //两端扫描，一端挖坑，另一端填补
    public static void quickSort2(int[] arr, int l, int r) {
        if (l >= r) return;
        int pivot = arr[l];
        int i = l, j = r;
        while (i < j) {
            while (j > i && arr[j] > pivot) {
                j--;
            }
            arr[i] = arr[j];
            while (i < j && arr[i] <= pivot) {
                i++;
            }
            arr[j] = arr[i];
        }
        arr[i] = pivot;
        quickSort2(arr, l, i - 1);
        quickSort2(arr, i + 1, r);
    }

    //单端扫描方式
    public static void quickSort3(int[] arr, int l, int r) {
        if (l >= r) return;
        int pivot = arr[l];
        int i = l, j = i + 1;
        while (j <= r) {
            if (arr[j] <= pivot) {
                i++;
                if (i < j)
                    SwapUtil.ArraySwapIndex(arr, i, j);
            }
            j++;
        }
        if (l < i)
            SwapUtil.ArraySwapIndex(arr, l, i);
        quickSort3(arr, l, i - 1);
        quickSort3(arr, i + 1, r);
    }

    //三向切分的快速排序

    /**
     * 用i，j，k三个变量将数组切分成四部分，
     * a[L, i-1]表示小于pivot的部分，
     * a[i, k-1]表示等于pivot的部分，
     * a[j+1]表示大于pivot的部分，
     * 而a[k, j]表示未判定的元素（即不知道比pivot大，还是比中轴元素小）
     */
    public static void quickSort4(int arr[], int l, int r) {
        if (l >= r) return;
        int pivot = arr[l];
        int i = l, k = l + 1, j = r;
        outter:
        while (k <= j) {
            if (arr[k] < pivot) {
                if (i < k) {
                    SwapUtil.ArraySwapIndex(arr, i, k);
                    i++;
                }
            } else if (arr[k] > pivot) {
                while (arr[j] > pivot) {
                    j--;
                    if (j < k) break outter;
                }
                if (arr[j] == pivot) {
                    if (k < j) {
                        SwapUtil.ArraySwapIndex(arr, k, j);
                        j--;
                    }
                } else {
                    if (i < k && k < j) {
                        SwapUtil.ArraySwapIndex_XYZ_2_ZXY(arr, i, k, j);
                        i++;
                        j--;
                    }
                }
            }
            k++;
        }
        quickSort4(arr, l, i - 1);
        quickSort4(arr, j + 1, r);
    }

    //双轴快速排序

    /**
     * 使用i，j，k三个变量将数组分成四部分,
     * A[L+1, i]是小于pivot1的部分，
     * A[i+1, k-1]是大于等于pivot1且小于等于pivot2的部分，
     * A[j, R]是大于pivot2的部分，
     * 而A[k, j-1]是未知部分。
     * <p>
     * i从l开始 ，j从r开始
     */
    public static void quickSort5(int[] arr, int l, int r) {
        if (l >= r) return;
        if (arr[l] > arr[r]) {
            SwapUtil.ArraySwapIndex(arr, l, r);//保证pivot1 <= pivot2
        }
        if (l + 1 >= r) return;

        int pivot1 = arr[l], pivot2 = arr[r];
        int i = l, k = i + 1, j = r;
        outter:
        while (k < j) {
            if (arr[k] < pivot1) {
                if (++i < k) {
                    SwapUtil.ArraySwapIndex(arr, i, k);
                }
            } else if (arr[k] > pivot2) {

                while (arr[--j] > pivot2) {
                    if (j <= k) break outter;
                }
                if (arr[j] >= pivot1) {
                    SwapUtil.ArraySwapIndex(arr, k, j);
                } else {
                    SwapUtil.ArraySwapIndex(arr, k, j);
                    if (++i < k) {
                        SwapUtil.ArraySwapIndex(arr, i, k);
                    }
                }
            }
            k++;
        }
        if (l < i && arr[l] > arr[i])
            SwapUtil.ArraySwapIndex(arr, l, i);
        if (j < r && arr[j] > arr[r])
            SwapUtil.ArraySwapIndex(arr, j, r);

        quickSort5(arr, l, i - 1);
        quickSort5(arr, i + 1, j - 1);
        quickSort5(arr, j + 1, r);
    }

    //双轴快速排序

    /**
     * i从l+1开始 ，j从r-1开始
     */
    public static void QuickSortDualPivot(int[] arr, int l, int r) {
        if (l >= r) return;
        if (arr[l] > arr[r]) {
            SwapUtil.ArraySwapIndex(arr, l, r); //保证pivot1 <= pivot2
        }
        if (l + 1 >= r) return;

        int pivot1 = arr[l], pivot2 = arr[r];
        int i = l + 1;
        int k = l + 1;
        int j = r - 1;

        OUT_LOOP:
        while (k <= j) {
            if (arr[k] < pivot1) {
                SwapUtil.ArraySwapIndex(arr, i, k);
                i++;
            } else if (arr[k] > pivot2) {
                while (arr[j] > pivot2) {
                    j--;
                    if (j < k) {
                        break OUT_LOOP;
                    }
                }
                if (arr[j] >= pivot1) {
                    SwapUtil.ArraySwapIndex(arr, k, j);
                } else {
//                    SwapUtil.ArraySwapIndex(A, j, k);//注意k不动
//                    j--;
                    SwapUtil.ArraySwapIndex(arr, k, j);
                    SwapUtil.ArraySwapIndex(arr, i, k);
                    i++;
                }
                j--;
            }
            k++;
        }
        i--;
        j++;
        if (l < i && arr[l] > arr[i])
            SwapUtil.ArraySwapIndex(arr, l, i);
        if (r < j && arr[r] > arr[j])
            SwapUtil.ArraySwapIndex(arr, r, j);

        QuickSortDualPivot(arr, l, i - 1);
        QuickSortDualPivot(arr, i + 1, j - 1);
        QuickSortDualPivot(arr, j + 1, r);
    }
}