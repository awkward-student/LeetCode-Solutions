/*
# 827. Making A Large Island


You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.

Example 1:
Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.

Example 2:
Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.

Example 3:
Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.
 
Constraints:
n == grid.length
n == grid[i].length
1 <= n <= 500
grid[i][j] is either 0 or 1.
*/







class Solution {
    public int largestIsland(int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;
        int maxSize = 0;
        // sizes[i] := the size of the i-th connected component (starting from 2)
        List<Integer> sizes = new ArrayList<>(List.of(0, 0));

        // For each 1 in the grid, paint all the connected 1s with the next
        // available color (2, 3, and so on). Also, remember the size of the island
        // we just painted with that color.
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == 1) {
                    sizes.add(paint(grid, i, j, sizes.size())); // Paint 2, 3, ...
                }

        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == 0) {
                    Set<Integer> neighborIds = new HashSet<>(Arrays.asList(getId(grid, i - 1, j), getId(grid, i + 1, j),
                            getId(grid, i, j + 1), getId(grid, i, j - 1)));
                    maxSize = Math.max(maxSize, 1 + getSize(grid, neighborIds, sizes));
                }

        return maxSize == 0 ? m * n : maxSize;
    }

    private int paint(int[][] grid, int i, int j, int id) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length)
            return 0;
        if (grid[i][j] != 1)
            return 0;
        grid[i][j] = id; // grid[i][j] is part of the id-th connected component.
        return 1 + paint(grid, i + 1, j, id) + paint(grid, i - 1, j, id) + paint(grid, i, j + 1, id) +
                paint(grid, i, j - 1, id);
    }

    // Gets the id of grid[i][j] and returns 0 if it's out-of-bounds.
    private int getId(int[][] grid, int i, int j) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length)
            return 0; // Invalid
        return grid[i][j];
    }

    private int getSize(int[][] grid, Set<Integer> neighborIds, List<Integer> sizes) {
        int size = 0;
        for (final int neighborId : neighborIds)
            size += sizes.get(neighborId);
        return size;
    }
}