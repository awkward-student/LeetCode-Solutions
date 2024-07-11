/*
# 1190. Reverse Substrings Between Each Pair of Parentheses


You are given a string s that consists of lower case English letters and brackets.

Reverse the strings in each pair of matching parentheses, starting from the innermost one.

Your result should not contain any brackets.

 
Example 1:
Input: s = "(abcd)"
Output: "dcba"

Example 2:
Input: s = "(u(love)i)"
Output: "iloveu"
Explanation: The substring "love" is reversed first, then the whole string is reversed.

Example 3:
Input: s = "(ed(et(oc))el)"
Output: "leetcode"
Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
 

Constraints:

1 <= s.length <= 2000
s only contains lower case English characters and parentheses.
It is guaranteed that all parentheses are balanced.
*/






class Solution {
    public String reverseParentheses(String s) {
        char[] x = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<x.length; i++){
            if(x[i] == '(') stack.push(i);
            else if(x[i] == ')'){
                reverse(x, stack.peek(), i);
                stack.pop();
            }
        }
        StringBuilder res = new StringBuilder();
        for(char c: x){
            if(c == '(' || c == ')') continue;
            else res.append(c);
        }
        return new String(res);
    }

    public void reverse(char[] x, int i, int l){
        while(i<l){
            char z = x[i];
            x[i] = x[l];
            x[l] = z;

            ++ i;
            -- l;
        }
    }
}