package com.yaruida.utils;

import com.datastructure.utils.TimeTool;
import lombok.val;

import java.util.*;

/**
 * @desc:
 * @author: admin
 * @since: 2022/1/7 11:27
 * @history:
 */
public class MessageUtils {

    public static void main(String[] args) {
        int[] nums = {0,1,4,100000};
         missingNumber(nums);
        System.out.println("---------------");
       //  missingNumber1(nums);
        System.out.println("---------------");
        missingNumber2(nums);


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

    public void test(){
        int  i = 10;

        for (int i1 = 0; i1 < i; i1++) {

            System.out.println("i1 : " + i1);

            System.out.println("ccL " + Math.log(1));


        }

        for (int i1 = 0; i1 < i; i+=2) {

            System.out.println("i1 : " + i1);
        }
    }


    public static int missingNumber1(int[] nums) {

        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return 0;
    }


    public static int missingNumber2(int[] nums){
        List<Integer> nums2 = new ArrayList<>();
            Arrays.sort(nums);
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (nums[i] != i) {
                    nums2.add(i);
                }
            }
        System.out.printf("nums2L" + nums2.toString() );
            return n;
        }

    public static int missingNumber4(int[] nums){
        List<Integer> nums2 = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

        }
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                nums2.add(i);
            }
        }
        System.out.printf("nums2L" + nums2.toString() );
        return 0;
    }

}
