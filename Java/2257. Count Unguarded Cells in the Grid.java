/*
# 2257. Count Unguarded Cells in the Grid


You are given two integers m and n representing a 0-indexed m x n grid. You are also given two 2D integer arrays guards and walls where guards[i] = [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the ith guard and jth wall respectively.

A guard can see every cell in the four cardinal directions (north, east, south, or west) starting from their position unless obstructed by a wall or another guard. A cell is guarded if there is at least one guard that can see it.

Return the number of unoccupied cells that are not guarded.

Example 1:
Input: m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
Output: 7
Explanation: The guarded and unguarded cells are shown in red and green respectively in the above diagram.
There are a total of 7 unguarded cells, so we return 7.

Example 2:
Input: m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
Output: 4
Explanation: The unguarded cells are shown in green in the above diagram.
There are a total of 4 unguarded cells, so we return 4.
 

Constraints:

1 <= m, n <= 105
2 <= m * n <= 105
1 <= guards.length, walls.length <= 5 * 104
2 <= guards.length + walls.length <= m * n
guards[i].length == walls[j].length == 2
0 <= rowi, rowj < m
0 <= coli, colj < n
All the positions in guards and walls are unique.
*/






class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int ans = 0;
        char[][] grid = new char[m][n];
        char[][] left = new char[m][n];
        char[][] right = new char[m][n];
        char[][] up = new char[m][n];
        char[][] down = new char[m][n];

        for (int[] guard : guards)
            grid[guard[0]][guard[1]] = 'G';

        for (int[] wall : walls)
            grid[wall[0]][wall[1]] = 'W';

        for (int i = 0; i < m; ++i) {
            char lastCell = 0;
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == 'G' || grid[i][j] == 'W')
                    lastCell = grid[i][j];
                else
                    left[i][j] = lastCell;
            lastCell = 0;
            for (int j = n - 1; j >= 0; --j)
                if (grid[i][j] == 'G' || grid[i][j] == 'W')
                    lastCell = grid[i][j];
                else
                    right[i][j] = lastCell;
        }

        for (int j = 0; j < n; ++j) {
            char lastCell = 0;
            for (int i = 0; i < m; ++i)
                if (grid[i][j] == 'G' || grid[i][j] == 'W')
                    lastCell = grid[i][j];
                else
                    up[i][j] = lastCell;
            lastCell = 0;
            for (int i = m - 1; i >= 0; --i)
                if (grid[i][j] == 'G' || grid[i][j] == 'W')
                    lastCell = grid[i][j];
                else
                    down[i][j] = lastCell;
        }

        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == 0 && left[i][j] != 'G' && right[i][j] != 'G' && up[i][j] != 'G' &&
                        down[i][j] != 'G')
                    ++ans;

        return ans;
    }
}