package com.galaxy.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *
 *
 * 提示：
 *
 * 1 <= n <= 45
 */
public class ClimbStairs_70 {

    //方法一：递归
    //解题思路：
    // 如果有一个楼梯，就只能有一种方式，有2个楼梯，就有两种方式，因此当楼梯树n小于等于2时，方式数量就是n
    // 如果大于n，由于一次只能走一步或者两步，因此当前可能有两种走法，一个是走一步，一个是走两步
    // 因此当大于2时，获取的方式就是走一步的方式 + 走两步的方式，走一步，就重新调用该方法，参数为n-1，走两步为n-2
    // 对于加map的解释：如果不加map，时间复杂度就是n的平方，例如n为10，那么第一步需要获取8和9，那么递归之后，在9里面需要获取8和7，因此为n的平方
    //  但是由于里面8是重复的，因此可以使用map进行缓存，时间复杂度变为n
        Map<Integer, Integer> map = new HashMap<>();
        public int climbStairs(int n) {
            if(n <=2){
                return n;
            }
            if(map.get(n) != null){
                return map.get(n);
            }
            int result = climbStairs(n-1) + climbStairs(n-2);
            map.put(n, result);
            return  result;
        }


    // 方法二：使用循环
    // 第一个台阶，方式为1，第二个台阶方式为2，第三个台阶，方式为3，第四个台阶，方式为4，因此，从第三个台阶开始，都是前两个方式之和。
    // 因此使用变量记录前两次的方式值，从第三个开始循环
    public int climbStairs2(int n) {
        if(n <= 2){
            return n;
        }
        int pre = 2;
        int prePre = 1;
        int result = 0;
        for(int i=3; i<=n;i++){
            result = pre + prePre;
            prePre = pre;
            pre = result;
        }
        return result;
    }

}
