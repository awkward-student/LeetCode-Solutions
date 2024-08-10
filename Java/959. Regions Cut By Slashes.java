/*
# 959. Regions Cut By Slashes


An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '. 
These characters divide the square into contiguous regions.

Given the grid grid represented as a string array, return the number of regions.

Note that backslash characters are escaped, so a '\' is represented as '\\'.


Example 1:
Input: grid = [" /","/ "]
Output: 2

Example 2:
Input: grid = [" /","  "]
Output: 1

Example 3:
Input: grid = ["/\\","\\/"]
Output: 5
Explanation: Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.
 

Constraints:

n == grid.length == grid[i].length
1 <= n <= 30
grid[i][j] is either '/', '\', or ' '.
*/





class Solution {
    public int regionsBySlashes(String[] grid) {
        final int n = grid.length;
        // G := upscaled grid
        int[][] g = new int[n * 3][n * 3];

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i].charAt(j) == '/') {
                    g[i * 3][j * 3 + 2] = 1;
                    g[i * 3 + 1][j * 3 + 1] = 1;
                    g[i * 3 + 2][j * 3] = 1;
                } else if (grid[i].charAt(j) == '\\') {
                    g[i * 3][j * 3] = 1;
                    g[i * 3 + 1][j * 3 + 1] = 1;
                    g[i * 3 + 2][j * 3 + 2] = 1;
                }

        int ans = 0;

        for (int i = 0; i < n * 3; ++i)
            for (int j = 0; j < n * 3; ++j)
                if (g[i][j] == 0) {
                    dfs(g, i, j);
                    ++ans;
                }

        return ans;
    }

    private void dfs(int[][] g, int i, int j) {
        if (i < 0 || i == g.length || j < 0 || j == g[0].length)
            return;
        if (g[i][j] != 0)
            return;

        g[i][j] = 2; // Mark 2 as visited.
        dfs(g, i + 1, j);
        dfs(g, i - 1, j);
        dfs(g, i, j + 1);
        dfs(g, i, j - 1);
    }
}