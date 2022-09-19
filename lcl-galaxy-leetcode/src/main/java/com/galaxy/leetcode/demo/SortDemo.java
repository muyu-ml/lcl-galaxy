package com.galaxy.leetcode.demo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;

@Slf4j
@Data
public class SortDemo {

    int[] intArr = {5,1,1,2,0,0};

    public void sort(int[] nums){
        log.info("============={}", JSON.toJSONString(nums));
        this.mergeSort(nums);
        log.info("============={}", JSON.toJSONString(nums));
    }

    public void mergeSort(int[] nums){
        if(nums == null || nums.length < 2){
            return;
        }
        int[] result = sortArray(nums, 0, nums.length-1);
        log.info("============={}", JSON.toJSONString(result));
    }

    private int[] sortArray(int[] nums, int left, int right){
        int length = right - left;
        int[] result = new int[length+1];
        if(length < 1){
            result[0] = nums[left];
            return result;
        }

        int mid = length/2 + left;
        int[] leftArr = sortArray(nums, left, mid);
        int[] rightArr = sortArray(nums, mid+1, right);

        // merge
        int leftIndex =0, rightIndex=0;
        for(int index=0;index<length+1;index++){
            if(leftIndex>leftArr.length-1) {
                result[index] = rightArr[rightIndex++];
            }else if(rightIndex>rightArr.length-1){
                result[index] = leftArr[leftIndex++];
            }else if(leftArr[leftIndex] > rightArr[rightIndex]){
                result[index] = rightArr[rightIndex++];
            }else {
                result[index] = leftArr[leftIndex++];
            }
        }
        return result;
    }


    /**
     * 希尔排序
     * @param nums
     */
    public void hellSort(int[] nums){
        if(nums == null || nums.length < 2){
            return;
        }
        int gap = nums.length;
        while (gap > 0){
            gap = gap/2;
            hellInsertSort(gap, nums);
        }
    }

    private void hellInsertSort(int gap, int[] nums){
        for (int i=0;i<gap;i++){
            for (int k=i;k<nums.length;k=k+gap){
                int temp = k+gap;
                while (temp<nums.length && temp >= gap && nums[temp] < nums[temp-gap]){
                    exchange(nums, temp, temp-gap);
                    temp = temp-gap;
                }
            }
        }

    }

    /**
     * 快速排序
     * @param nums
     */
    public void quickSort(int[] nums){
        quickSort(nums, 0, nums.length-1);
    }

    /**
     *
     * @param nums
     * @param left
     * @param right
     */
    private void quickSort(int[] nums, int left, int right) {
        if(nums == null || right - left < 1 || left < 0 || right > nums.length-1 || left > right){
            return;
        }
        int baseNum = (int) (left + Math.random() * (right-left+1));
        exchange(nums, baseNum, right);

        if(right - left <15){
            int increIndex = left+1;
            for(;increIndex<=right;increIndex++){
                int tempIndex = increIndex;
                while(tempIndex > left && nums[tempIndex] < nums[tempIndex-1]){
                    exchange(nums, tempIndex, tempIndex-1);
                    tempIndex--;
                }
            }
            return;
        }

        int partitionIndex = left -1;
        int subscribeIndex = left;
        while (subscribeIndex <= right){
            if(nums[subscribeIndex] <= nums[right]){
                partitionIndex++;
                if(partitionIndex != subscribeIndex){
                    exchange(nums, partitionIndex, subscribeIndex);
                }
            }
            subscribeIndex++;
        }
        quickSort(nums, left, partitionIndex-1);
        quickSort(nums, partitionIndex+1, right);
    }

    private void exchange(int[] nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    /**
     * 插入排序
     * @param nums
     */
    public void insertSort(int[] nums){
        int temp = 0;
        for (int i=1;i<nums.length;i++){
            int j=i;
            while (j>0 && nums[j] < nums[j-1]){
                temp = nums[j];
                nums[j] = nums[j-1];
                nums[j-1] = temp;
                j--;
            }
        }
        log.info("============={}", JSON.toJSONString(nums));
    }

    /**
     * 冒泡排序
     * @param nums
     */
    public void bubbleSort(int[] nums){
        int temp = 0;
        for (int i=0;i<nums.length;i++){
            for (int j=1;j<nums.length-i;j++){
                if(nums[j-1] > nums[j]){
                    temp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = temp;
                }
                log.info("============={}", JSON.toJSONString(nums));
            }
        }
        log.info("============={}", JSON.toJSONString(nums));
    }

    /**
     * 选择排序
     * @param nums
     */
    public void selectSort(int[] nums){
        for(int i=0;i<nums.length;i++){
            int minIndex = i;
            for (int j=i;j<nums.length;j++){
                if(nums[j] < nums[minIndex]){
                    minIndex = j;
                }
            }
            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }
        log.info("============={}", JSON.toJSONString(nums));
    }


}
