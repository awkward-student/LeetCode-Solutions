/*
# 861. Score After Flipping Matrix


You are given an m x n binary matrix grid.

A move consists of choosing any row or column and toggling each value in that row or column
(i.e., changing all 0's to 1's, and all 1's to 0's).

Every row of the matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.

Return the highest possible score after making any number of moves (including zero moves).

 
Example 1:
Input: grid = [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
Output: 39
Explanation: 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39

Example 2:
Input: grid = [[0]]
Output: 1
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 20
grid[i][j] is either 0 or 1.
*/



// Approach 1: Naive Greedy

class Solution {
    public int matrixScore(int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;
        int ans = 0;

        // Flip the rows with a leading 0.
        for (int[] row : grid)
            if (row[0] == 0)
                flip(row);

        // Flip the columns with 1s < 0s.
        for (int j = 0; j < n; ++j)
            if (onesColCount(grid, j) * 2 < m)
                flipCol(grid, j);

        // Add a binary number for each row.
        for (int[] row : grid)
            ans += binary(row);

        return ans;
    }

    private void flip(int[] row) {
        for (int i = 0; i < row.length; ++i)
            row[i] ^= 1;
    }

    private int onesColCount(int[][] grid, int j) {
        int ones = 0;
        for (int i = 0; i < grid.length; ++i)
            ones += grid[i][j];
        return ones;
    }

    private void flipCol(int[][] grid, int j) {
        for (int i = 0; i < grid.length; ++i)
            grid[i][j] ^= 1;
    }

    private int binary(int[] row) {
        int res = row[0];
        for (int j = 1; j < row.length; ++j)
            res = res * 2 + row[j];
        return res;
    }
}





// Approach 2: Heuristic

class Solution {
    public int matrixScore(int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;
        int ans = m; // All the cells in the first column are 1.

        for (int j = 1; j < n; ++j) {
            int onesCount = 0;
            for (int i = 0; i < m; ++i)
                // The best strategy is flipping the rows with a leading 0..
                onesCount += grid[i][j] == grid[i][0] ? 1 : 0;
            ans = ans * 2 + Math.max(onesCount, m - onesCount);
        }

        return ans;
    }
}