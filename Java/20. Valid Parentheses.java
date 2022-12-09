/*
20. Valid Parentheses

Easy

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
 

Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "()[]{}"
Output: true
Example 3:

Input: s = "(]"
Output: false
 

Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.
*/



class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        char[] array = s.toCharArray();
        for(int i=0; i<array.length; i++){
            if(stack.empty()){
                stack.push(array[i]);
                continue;
            }
            else{
                switch(array[i]){
                    case '(': stack.push(array[i]);
                    break;
                    case ')': if(stack.peek() == '('){
                        stack.pop();
                    }
                    else stack.push(array[i]);
                    break;
                    case '[': stack.push(array[i]);
                    break;
                    case ']': if(stack.peek() == '['){
                        stack.pop();
                    }
                    else stack.push(array[i]);
                    break;
                    case '{': stack.push(array[i]);
                    break;
                    case '}': if(stack.peek() == '{'){
                        stack.pop();
                    }
                    else stack.push(array[i]);
                    break;
                }
            }
        }
        if(stack.empty()) return true;
        else return false;
    }
}