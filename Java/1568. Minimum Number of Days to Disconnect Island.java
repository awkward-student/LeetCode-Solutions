/*
# 1568. Minimum Number of Days to Disconnect Island


You are given an m x n binary grid grid where 1 represents land and 0 represents water. 
An island is a maximal 4-directionally (horizontal or vertical) connected group of 1's.

The grid is said to be connected if we have exactly one island, otherwise is said disconnected.

In one day, we are allowed to change any single land cell (1) into a water cell (0).

Return the minimum number of days to disconnect the grid.

 
Example 1:
Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]
Output: 2
Explanation: We need at least 2 days to get a disconnected grid.
Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.

Example 2:
Input: grid = [[1,1]]
Output: 2
Explanation: Grid of full water is also disconnected ([[1,1]] -> [[0,0]]), 0 islands.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 30
grid[i][j] is either 0 or 1.	
*/





class Solution {
    public int minDays(int[][] grid) {
        if (disconnected(grid))
            return 0;

        // Try to remove 1 land.
        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[0].length; ++j)
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    if (disconnected(grid))
                        return 1;
                    grid[i][j] = 1;
                }

        // Remove 2 lands.
        return 2;
    }

    private final int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    private boolean disconnected(int[][] grid) {
        int islandsCount = 0;
        boolean[][] seen = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 0 || seen[i][j])
                    continue;
                if (++islandsCount > 1)
                    return true;
                dfs(grid, i, j, seen);
            }

        return islandsCount != 1;
    }

    private void dfs(int[][] grid, int i, int j, boolean[][] seen) {
        seen[i][j] = true;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x == grid.length || y < 0 || y == grid[0].length)
                continue;
            if (grid[x][y] == 0 || seen[x][y])
                continue;
            dfs(grid, x, y, seen);
        }
    }
}