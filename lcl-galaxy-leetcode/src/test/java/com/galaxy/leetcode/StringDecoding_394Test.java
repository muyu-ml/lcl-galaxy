package com.galaxy.leetcode;

import com.galaxy.leetcode.demo.SortDemo;
import com.galaxy.leetcode.demo.StringDecoding_394;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
@Slf4j
public class StringDecoding_394Test {

    @Test
    public void test1(){
        StringDecoding_394 stringDecoding_394 = new StringDecoding_394();
        String s = stringDecoding_394.decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]ef");
        String s1= "zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef";
        log.info("s=============={}", s);
        log.info("s=============={}", s1);
        log.info("=============={}", s.equals(s1));
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    @Test
    public void test3(){
        ListNode l1 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
        ListNode l2 = new ListNode(9, new ListNode(9));
        ListNode listNode = addTwoNumbers(l1, l2);
        log.info("",listNode);
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int j = 0;
        ListNode head = new ListNode();
        ListNode r = head;
        while(l1 != null && l2!=null){
            int val1 = 0, val2 = 0;
            if(l1 != null){
                val1 = l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                val2 = l2.val;
                l2 = l2.next;
            }
            int sum = val1+val2+j;
            if(sum > 9){
                sum = sum-10;
                j = 1;
            }else{
                j =0;
            }
            ListNode next = new ListNode(sum);
            head.next = next;
            head = head.next;
        }
        if(j > 0){
            ListNode next = new ListNode(j);
            head.next = next;
            head = next;
        }
        return r.next;
    }

    @Test
    public void minSubArrayLen() {
        int[] nums = {1,2,3,4,5};
        int target = 15;
        if(nums == null || nums.length == 0){
            return;
        }
        int minLength = Integer.MAX_VALUE;
        int sum = nums[0];
        int l=0, r=0;
        while(r<nums.length){
            sum+=nums[r];
            while(sum >= target){
                minLength = Math.min(minLength, r-l+1);
                sum-=nums[l];
                l++;
            }
            r++;
        }
        minLength = minLength == Integer.MAX_VALUE ? 0 : minLength;
        log.info("=========={}", minLength);
    }


    @Test
    public void test2(){
        SortDemo sortDemo = new SortDemo();
        sortDemo.sort(sortDemo.getIntArr());
    }

}
