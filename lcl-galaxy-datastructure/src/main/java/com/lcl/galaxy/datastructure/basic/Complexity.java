package com.lcl.galaxy.datastructure.basic;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class Complexity {

    public int sum(int n){
        int sum = 0;
        for(int i = 1; i< n; i++) {
            sum = sum + i;
        }
        return sum;
    }

    public int sum(int n, int x){
        int sum = 0;
        for(int i = 1; i< n; i++) {
            if(i == x){
                break;
            }
            sum = sum + i;
        }
        return sum;
    }


    int n;
    public int sum2(int x){
        int count = 0;
        if(n == x){
            for(int i=0; i< n; i++){
                count++;
            }
        } else {
            count = x;
        }
        return count;
    }



    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }

        for (int i : nums){
            System.out.println(i);
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public int divide(int a, int b) {
        boolean flag = true;
        if(a > 0){
            flag = false;
            a = -a;
        }
        if(b > 0){
            flag = flag? false : true;
            b = -b;
        }
        return divide2(flag, a, b);
    }

    private int divide2(boolean flag, int a, int b){
        if(b < a){
            return 0;
        }
        int c = a-b;
        int i = 0;
        if(c<b){
            i = divide2(flag, c,b);
        }
        int k =  flag ? ++i : --i;
        return k;
    }



    public double myPow(double x, int n) {
        int k = n;
        return k > 0 ? pow(x, n) : 1.0/pow(x, -n);
    }

    private double pow(double x, int n){
        if(n == 0){
            return 1;
        }
        double k = pow(x, n/2);
        return n%2 == 0 ? k*k : x*k*k;
    }

    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            int m = i >> 1;
            m = m ^ i;
            ret.add((i >> 1) ^ i);
        }
        return ret;
    }

    public List<Integer> grayCode2(int n) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0; i< 1<<n;i++){
            list.add(i >> 1 ^ i);
        }
        return list;
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i : nums){
            if(!set.add(i)){
                return false;
            }
        }
        return true;
    }


    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> list = new ArrayList();
        if(nums.length < 3){
            return list;
        }
        Arrays.sort(nums);
        List<Integer> subList = null;
        int target;
        for(int i=0; i< nums.length-2;i++){
            if(nums[i] > 0){
                break;
            }
            if(i >1 && nums[i] == nums[i-1]){
                continue;
            }
            target = 0 - nums[i];
            int left = i+1;
            for(;left < nums.length-1;left++){
                int right = nums.length-1;
                while(left != right-1 && target-nums[left] != nums[right]){
                    right--;
                }
                if(target-nums[left] == nums[right] && !(right<nums.length -1 && nums[right] == nums[right+1])){
                    subList = new ArrayList();
                    subList.add(nums[i]);
                    subList.add(nums[left]);
                    subList.add(nums[right]);
                    list.add(subList);
                }
            }

        }
        return list;
    }

    public int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int[] target = new int[m+n];
        int v1 = 0;
        int v2 = 0;
        int t = 0;
        while(t< m+n || v1 < m-1){
            if(v1 > m-1){
                target[t++] = nums2[v2];
                v2++;
                continue;
            }
            if(v2 > n-1){
                target[t++] = nums1[v1];
                v1++;
                continue;
            }

            if(nums1[v1] > nums2[v2]){
                target[t++] = nums2[v2];
                v2++;
            }else{
                target[t++] = nums1[v1];
                v1++;
            }
        }
        v1 = 0;
        for (int i :target){
            nums1[v1++] = i;
        }
        return target;
    }


    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    public static void main(String[] args) {
//        int i = 5/2;
//        //int[] nums = {0,1};
//        int[] nums = {0,1,0,3,12};
        Complexity complexity = new Complexity();
//        complexity.moveZeroes(nums);
        //System.out.println(complexity.divide(15, 2));
        System.out.println(complexity.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));

        Integer i = 100;
        Integer j = 100;
        System.out.print(i == j);
    }

}
