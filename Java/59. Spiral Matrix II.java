/*
 *
 * #59. Spiral Matrix II
 *
 *

Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

 

Example 1:


Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]
Example 2:

Input: n = 1
Output: [[1]]
 

Constraints:

1 <= n <= 20
 */





class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int count = 1;

        for (int min = 0; min < n / 2; ++min) {
            final int max = n - min - 1;
            for (int i = min; i < max; ++i)
                ans[min][i] = count++;
            for (int i = min; i < max; ++i)
                ans[i][max] = count++;
            for (int i = max; i > min; --i)
                ans[max][i] = count++;
            for (int i = max; i > min; --i)
                ans[i][min] = count++;
        }

        if (n % 2 == 1)
            ans[n / 2][n / 2] = count;

        return ans;
    }
}