package com.datastructure.likou;

import java.util.*;

public class RemoveDuplicates {

    public static void main(String[] args) {
        // 输入数组
        int [] nums = {1,1,2,3,4,5,6,6,7,7,7};
//        removeDuplicates(nums);
        System.out.println("a:" +removeDuplicates1(nums));

    }

    public static void removeDuplicates(int [] nums){
        // 长度正确的期望
        List<Integer> expectedNumList = new ArrayList<>();
        Map<Integer, Integer> allMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!allMap.containsKey(nums[i])) {
                expectedNumList.add(nums[i]);
                allMap.put(nums[i], nums[i]);
            }
        }
        // 长度正确的期望
        System.out.printf(expectedNumList.size() + ",  nums = " + expectedNumList.toString());
    }



    //双指针解决
    public static int removeDuplicates1(int[] A) {
        //边界条件判断
        if (A == null || A.length == 0)
            return 0;
        int left = 0;
        for (int right = 1; right < A.length; right++)
            //如果左指针和右指针指向的值一样，说明有重复的，
            //这个时候，左指针不动，右指针继续往右移。如果他俩
            //指向的值不一样就把右指针指向的值往前挪
            if (A[left] != A[right])
                A[++left] = A[right];
        return ++left;
    }
}
