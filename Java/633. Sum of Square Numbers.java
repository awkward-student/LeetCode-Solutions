/*
# 633. Sum of Square Numbers

Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.

Example 1:
Input: c = 5
Output: true
Explanation: 1 * 1 + 2 * 2 = 5

Example 2:
Input: c = 3
Output: false

Constraints:

0 <= c <= 231 - 1
*/





class Solution {
    public boolean judgeSquareSum(int c) {
        int l = 0;
        int r = (int) Math.sqrt(c);

        while (l <= r) {
            final int sum = l * l + r * r;
            if (sum == c)
                return true;
            if (sum < c)
                ++l;
            else
                --r;
        }

        return false;
    }
}