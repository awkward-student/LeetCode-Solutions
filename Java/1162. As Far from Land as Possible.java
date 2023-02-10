/*
1162. As Far from Land as Possible
Medium

Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.
The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|. 

Example 1:
1   0   1
0   0   0
1   0   1
Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.

Example 2:
1   0   0
0   0   0
0   0   0
Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.
 
Constraints:
n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
*/



class Solution {
    public int maxDistance(int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;
        final int[] dirs = {0, 1, 0, -1, 0};
        Queue<Pair<Integer, Integer>> q = new ArrayDeque<>();
        int water = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == 0) ++water;
                else q.offer(new Pair<>(i, j));

        if (water == 0 || water == m * n) return -1;
        int ans = 0;
        for (int d = 0; !q.isEmpty(); ++d)
            for (int sz = q.size(); sz > 0; --sz) {
                Pair<Integer, Integer> pair = q.poll();
                final int i = pair.getKey();
                final int j = pair.getValue();
                ans = d;
                for (int k = 0; k < 4; ++k) {
                    final int x = i + dirs[k];
                    final int y = j + dirs[k + 1];
                    if (x < 0 || x == m || y < 0 || y == n) continue;
                    if (grid[x][y] > 0) continue;
                    q.offer(new Pair<>(x, y));
                    grid[x][y] = 2; // Mark as visited.
                }
            }
        return ans;
    }
}