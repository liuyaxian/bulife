package com.datastructure;


import com.datastructure.utils.TimeTool;
import com.datastructure.utils.TimeTool.Task;

/***
 * 斐波那契数列（黄金分割数列）
 * 递推的方法定义：F(0)=0，F(1)=1, F(n)=F(n - 1)+F(n - 2)（n ≥ 2，n ∈ N*）
 * 0   1  1     2   3   5   8   13  21  34  .......
 */
public class FibonacciNumber {

    public static void main(String[] args) {
        TimeTool.check("斐波那契1", new Task() {
            @Override
            public void execute() {
                System.out.println("seL:"+ fibonacci1(33));
            }
        });

        TimeTool.check("斐波那契2", new TimeTool.Task() {
            @Override
            public void execute() {
                System.out.println("seL:"+ fibonacci2(33));
            }
        });
    }

    private static  int fibonacci1(int n){
      if (n <= 1){
          return n;
      }
      return fibonacci1(n-1)+fibonacci1(n-2);
    }

    private static  int fibonacci2(int n){
        if (n <= 1){
            return n;
        }
        int first = 0;
        int sencond = 1;
        for (int i =0; i < n-1; i++){
            int sum = first + sencond;
            first = sencond;
            sencond = sum;
        }
        return sencond;
    }
}
