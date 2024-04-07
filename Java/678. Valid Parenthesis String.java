/*
# 678. Valid Parenthesis String


Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 

Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "(*)"
Output: true

Example 3:
Input: s = "(*))"
Output: true
 

Constraints:

1 <= s.length <= 100
s[i] is '(', ')' or '*'.
*/





class Solution {
    public boolean checkValidString(String s) {
        int low = 0; // the lower bound of the number of valid '('s
        int high = 0; // the upper bound of the number of valid '('s

        for (final char c : s.toCharArray()) {
            switch (c) {
                case '(':
                    ++low;
                    ++high;
                    break;
                case ')':
                    low = Math.max(0, --low);
                    --high;
                    break;
                case '*':
                    low = Math.max(0, --low);
                    ++high;
                    break;
            }
            if (high < 0)
                return false;
        }

        return low == 0;
    }
}