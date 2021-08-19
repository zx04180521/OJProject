package test;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set=new HashSet<>();
        int start=0;
        int max=0;
        for(int i=0;i<s.length();i++){
            while(set.contains(s.charAt(i))){
                set.remove(s.charAt(start++));
            }
            set.add(s.charAt(i));
            max=Math.max(max,i-start+1);
        }
        return max;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String str="pwwkew";
        int result = solution.lengthOfLongestSubstring(str);
        if (result==3) {
            System.out.println("TestCase OK!");
        } else {
            System.out.println("TestCase Failed! String=\"pwwkew\"");
        }

        String str2="abcabcbb";
        int result2 = solution.lengthOfLongestSubstring(str);
        if (result2==3) {
            System.out.println("TestCaseOK!");
        } else {
            System.out.println("TestCase Failed! String=\"abcabcbb\"");
        }
    }
}