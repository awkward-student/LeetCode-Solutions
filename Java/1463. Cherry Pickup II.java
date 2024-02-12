/*
# 1463. Cherry Pickup II

You are given a rows x cols matrix grid representing a field of cherries where grid[i][j] represents the number of cherries that you can collect from the (i, j) cell.

You have two robots that can collect cherries for you:

Robot #1 is located at the top-left corner (0, 0), and
Robot #2 is located at the top-right corner (0, cols - 1).
Return the maximum number of cherries collection using both robots by following the rules below:

From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
When both robots stay in the same cell, only one takes the cherries.
Both robots cannot move outside of the grid at any moment.
Both robots should reach the bottom row in grid.
 

Example 1:

Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
Output: 24
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
Total of cherries: 12 + 12 = 24.


Example 2:

Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
Output: 28
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
Total of cherries: 17 + 11 = 28.
 

Constraints:

rows == grid.length
cols == grid[i].length
2 <= rows, cols <= 70
0 <= grid[i][j] <= 100
*/





// Top-Down Approach

class Solution {
  public int cherryPickup(int[][] grid) {
    final int m = grid.length;
    final int n = grid[0].length;
    int[][][] mem = new int[m][n][n];

    for (int[][] A : mem)
      Arrays.stream(A).forEach(B -> Arrays.fill(B, -1));

    return cherryPick(grid, 0, 0, n - 1, mem);
  }

  // Returns the maximum cherries we can collect, where robot #1 is on (x, y1)
  // and robot #2 is on (x, y2).
  private int cherryPick(int[][] grid, int x, int y1, int y2, int[][][] mem) {
    if (x == grid.length)
      return 0;
    if (y1 < 0 || y1 == grid[0].length || y2 < 0 || y2 == grid[0].length)
      return 0;
    if (mem[x][y1][y2] != -1)
      return mem[x][y1][y2];

    final int currRow = grid[x][y1] + (y1 == y2 ? 0 : grid[x][y2]);

    for (int d1 = -1; d1 <= 1; ++d1)
      for (int d2 = -1; d2 <= 1; ++d2)
        mem[x][y1][y2] =
            Math.max(mem[x][y1][y2], currRow + cherryPick(grid, x + 1, y1 + d1, y2 + d2, mem));

    return mem[x][y1][y2];
  }
}





// Bottom-Up Approach

class Solution {
  public int cherryPickup(int[][] grid) {
    final int m = grid.length;
    final int n = grid[0].length;
    // dp[x][y1][y2] := the maximum cherries we can collect, where the robot #1
    // is on (x, y1) and the robot #2 is on (x, y2)
    int[][][] dp = new int[m + 1][n][n];

    for (int x = m - 1; x >= 0; --x)
      for (int y1 = 0; y1 < n; ++y1)
        for (int y2 = 0; y2 < n; ++y2) {
          final int currRow = grid[x][y1] + (y1 == y2 ? 0 : 1) * grid[x][y2];
          for (int d1 = Math.max(0, y1 - 1); d1 < Math.min(n, y1 + 2); ++d1)
            for (int d2 = Math.max(0, y2 - 1); d2 < Math.min(n, y2 + 2); ++d2)
              dp[x][y1][y2] = Math.max(dp[x][y1][y2], currRow + dp[x + 1][d1][d2]);
        }

    return dp[0][0][n - 1];
  }
}