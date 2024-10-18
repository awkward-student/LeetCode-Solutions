/*
# 670. Maximum Swap


You are given an integer num. You can swap two digits at most once to get the maximum valued number.

Return the maximum valued number you can get.

Example 1:
Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.

Example 2:
Input: num = 9973
Output: 9973
Explanation: No swap.
 

Constraints:

0 <= num <= 108
*/






class Solution {
    public int maximumSwap(int num) {
        char[] s = Integer.toString(num).toCharArray();
        int[] lastIndex = new int[10]; // {digit: last index}

        for (int i = 0; i < s.length; ++i)
            lastIndex[s[i] - '0'] = i;

        for (int i = 0; i < s.length; ++i)
            for (int d = 9; d > s[i] - '0'; --d)
                if (lastIndex[d] > i) {
                    s[lastIndex[d]] = s[i];
                    s[i] = (char) ('0' + d);
                    return Integer.parseInt(new String(s));
                }

        return num;
    }
}