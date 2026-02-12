package com.example.sampledemo.other;

import java.util.Arrays;

public class Sort {

    public static void main(String[] args) {
        int[] arr = generateIntArray(10, 1, 20);
        bubbleSort(Arrays.copyOf(arr, arr.length));
        selectionSort(Arrays.copyOf(arr, arr.length));
        heapSort(Arrays.copyOf(arr, arr.length));
        insertionSort(Arrays.copyOf(arr, arr.length));
        mergeSort(Arrays.copyOf(arr, arr.length));
        quickSort(Arrays.copyOf(arr, arr.length));
        shellSort(Arrays.copyOf(arr, arr.length));
        countingSort(Arrays.copyOf(arr, arr.length));
        radixSort(Arrays.copyOf(arr, arr.length));
    }

    private static void radixSort(int[] array) {

    }

    private static void countingSort(int[] array) {

    }

    private static void shellSort(int[] array) {

    }

    private static void quickSort(int[] array) {

    }

    private static void mergeSort(int[] array) {

    }

    private static void insertionSort(int[] array) {

    }

    private static void heapSort(int[] array) {

    }

    private static void selectionSort(int[] array) {

    }

    private static void bubbleSort(int[] array) {

    }

    public static int[] generateIntArray(int count, int rangeL, int rangeR) {
        if (count < 1 || rangeR - rangeL < 1) return null;
        int[] ints = new int[count];
        int range = rangeR - rangeL + 1;
        for (int i = 0; i < count; i++) {
            ints[i] = (int) (Math.random() * range + rangeL);
        }
        return ints;
    }

}
