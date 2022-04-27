package com.datastructure.likou;

import lombok.val;

import java.util.HashMap;
import java.util.Map;

public class MissingNumber {


    public static void main(String[] args) {
        int [] nums = {2,1,4};
        System.out.println("missingNumber : " +  missingNumber(nums));
        System.out.println("missingNumber2 : " +  missingNumber2(nums));


    }
    public static int missingNumber(int[] nums) {

        int tempLitter = 0;
        int tempBig = 0;

        Map<Integer, Integer> map  = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (i == 0){
                tempLitter =   nums[0];
            }
            val num = nums[i];
            if ( nums[i] < tempLitter){
                tempLitter =  nums[i];
            }
            if ( nums[i] > tempBig){
                tempBig = nums[i];
            }
            map.put(nums[i] , nums[i]);
        }

        if(tempBig <= nums.length){
            tempBig = nums.length +1;
        }
        for (int i = tempLitter; i < tempBig; i++) {
            if (!map.containsKey(i)){
                System.out.println(i);
            }

        }
        return 0;
    }



    public static  int missingNumber2(int[] nums) {
        int xor = 0;
        for (int i = 0; i < nums.length; i++)
            xor ^= nums[i] ^ (i + 1);
        return xor;
    }
}
