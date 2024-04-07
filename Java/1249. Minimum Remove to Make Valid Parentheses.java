/*
# 1249. Minimum Remove to Make Valid Parentheses


Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', 
in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
 

Example 1:
Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Example 2:
Input: s = "a)b(c)d"
Output: "ab(c)d"

Example 3:
Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
 

Constraints:

1 <= s.length <= 105
s[i] is either'(' , ')', or lowercase English letter.
*/





class Solution {
    public String minRemoveToMakeValid(String s) {
        Deque<Integer> stack = new ArrayDeque<>(); // unpaired '(' indices
        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < s.length(); ++i)
            if (sb.charAt(i) == '(') {
                stack.push(i); // Record the unpaired '(' index.
            } else if (sb.charAt(i) == ')') {
                if (stack.isEmpty())
                    sb.setCharAt(i, '#'); // Mark the unpaired ')' as '#'.
                else
                    stack.pop(); // Find a pair!
            }

        // Mark the unpaired '(' as '#'.
        while (!stack.isEmpty())
            sb.setCharAt(stack.pop(), '#');

        return sb.toString().replaceAll("#", "");
    }
}