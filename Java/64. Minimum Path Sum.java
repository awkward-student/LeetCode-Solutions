/*
 * 
 * #64. Minimum Path Sum
 * 
 * 

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,

which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

 

Example 1:


Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
Example 2:

Input: grid = [[1,2,3],[4,5,6]]
Output: 12
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 100

*/




class Solution {
    public int minPathSum(int[][] grid) {
        final int x = grid.length;
        final int y = grid[0].length;
        for(int i=0; i<x; i++){
            for(int j=0; j<y; j++){
                if(i > 0 && j > 0) grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
                else if (i > 0) grid[i][0] += grid[i - 1][0];
                else if (j > 0) grid[0][j] += grid[0][j - 1];
            }
        }
        return grid[x-1][y-1];
    }
}