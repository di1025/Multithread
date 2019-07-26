package com.chendi.multithread.ParallelComputing;

import java.util.Random;

public class MergeSort {

    private int[] nums;
    private int[] tempArray;

    public MergeSort(int[] nums) {
        this.nums = nums;
        tempArray = new int[nums.length];
    }

    public void mergeSort(int lowIndex, int highIndex) {
        if (lowIndex >= highIndex) {
            return;
        }

        int middle = (lowIndex + highIndex) / 2;

        mergeSort(lowIndex, middle);
        mergeSort(middle + 1, highIndex);
        merge(lowIndex, middle, highIndex);


    }

    private void merge(int lowIndex, int middleIndex, int highIndex) {
        for (int i = lowIndex; i <= highIndex; i++) {
            tempArray[i] = nums[i];
        }
        int i = lowIndex;
        int j = middleIndex + 1;
        int k = lowIndex;

        while ((i <= middleIndex) && (j <= highIndex)) {
            if (tempArray[i] <= tempArray[j]) {
                nums[k] = tempArray[i];
                i++;
            } else {
                nums[k] = tempArray[j];
                j++;
            }
            k++;
        }
//checking if there is nums left in the left array
        while (i <= middleIndex) {
            nums[k] = tempArray[i];
            k++;
            i++;
        }
//checking if there is nums left in the right array
        while (j <= highIndex) {
            nums[k] = tempArray[j];
            k++;
            j++;
        }
    }

    public void sortResult() {
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i] + " ");
        }
    }

    public boolean isSorted() {
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }

            return true;

    }

    public static void main(String[] agrs){
        Random random = new Random();
        int[] nums = new int[30];

        for(int i=0;i<nums.length;i++){
            nums[i] = random.nextInt(1000)-500;
        }

        MergeSort mergesort = new MergeSort(nums);
        mergesort.mergeSort(0,nums.length-1);
        mergesort.sortResult();
        mergesort.isSorted();


    }


}
