package com.galaxy.leetcode.demo;

import org.springframework.boot.SpringApplication;

import java.util.LinkedList;
import java.util.Stack;

public class StringDecoding_394 {

    public static void main(String[] args) {
        StringDecoding_394 stringDecoding_394 = new StringDecoding_394();
        stringDecoding_394.decodeString("3[a]2[bc]");
    }

    public  String decodeString(String s) {
        LinkedList<String> list = new LinkedList<>();
        StringBuilder numBuilder = new StringBuilder();//存储数字
        StringBuilder tempBuilder = new StringBuilder();//存储最小的数据单元
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){ //如果是数字，需要特殊处理，先放入存储数字的builder中
                numBuilder.append(s.charAt(i));
            }else if(s.charAt(i) == '['){ //如果是左括号，说明数字已经读完，将数字和左括号放入stack中，同时将数字builder清空
                list.addFirst(numBuilder.toString());
                list.addFirst(String.valueOf(s.charAt(i)));
                numBuilder = new StringBuilder();
            }else if(']' != s.charAt(i)){ //如果不是有括号，也就是正常的字符串，则直接加入stack中
                list.addFirst(String.valueOf(s.charAt(i)));
            }else{  // 如果是有括号，需要取出最小的字符串单元
                tempBuilder = new StringBuilder();
                while(list.size() != 0){
                    String temp = list.pop(); //从stack中获取字符
                    if(!"[".equals(temp)){ //如果不是左括号，则一直获取，放入临时builder中
                        tempBuilder.insert(0,temp);
                    }else{ //如果是左括号，说明已经到了边界，需要再获取一次数据，获取的就是括号前的数字
                        temp = list.pop(); //括号前的数字
                        String smailString = tempBuilder.toString();
                        for(int j=1; j< Integer.parseInt(temp);j++){  //按照括号前的数字，循环拼接
                            tempBuilder.insert(0,smailString);
                        }
                        list.addFirst(tempBuilder.toString()); //拼接后重新放入stack
                        break;//处理完一个又括号之后，不再进行处理
                    }
                }

            }
        }
        tempBuilder = new StringBuilder();
        // 获取stack中的数据，反转即可
        while(list.size() != 0){
            tempBuilder.insert(0,list.pop());
        }
        return tempBuilder.toString();
    }
}
