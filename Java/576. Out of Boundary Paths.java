/*
# 576. Out of Boundary Paths

There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn]. You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary). You can apply at most maxMove moves to the ball.

Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid boundary. Since the answer can be very large, return it modulo 109 + 7.
 

Example 1:
Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
Output: 6

Example 2:
Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
Output: 12
 

Constraints:

1 <= m, n <= 50
0 <= maxMove <= 50
0 <= startRow < m
0 <= startColumn < n
*/





class Solution {
  public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
    final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    final int kMod = 1_000_000_007;
    int ans = 0;
    // dp[i][j] := the number of paths to move the ball (i, j) out-of-bounds
    int[][] dp = new int[m][n];
    dp[startRow][startColumn] = 1;

    while (maxMove-- > 0) {
      int[][] newDp = new int[m][n];
      for (int i = 0; i < m; ++i)
        for (int j = 0; j < n; ++j)
          if (dp[i][j] > 0)
            for (int[] dir : dirs) {
              final int x = i + dir[0];
              final int y = j + dir[1];
              if (x < 0 || x == m || y < 0 || y == n)
                ans = (ans + dp[i][j]) % kMod;
              else
                newDp[x][y] = (newDp[x][y] + dp[i][j]) % kMod;
            }
      dp = newDp;
    }

    return ans;
  }
}