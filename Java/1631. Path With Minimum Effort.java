/*
# 1631. Path With Minimum Effort

You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

 

Example 1:
Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.

Example 2:
Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
Output: 1
Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].

Example 3:
Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
Output: 0
Explanation: This route does not require any effort.
 

Constraints:

rows == heights.length
columns == heights[i].length
1 <= rows, columns <= 100
1 <= heights[i][j] <= 106
*/



class T {
  public int i;
  public int j;
  public int d; // Max difference of (i, j) and its neighbors
  public T(int i, int j, int d) {
    this.i = i;
    this.j = j;
    this.d = d;
  }
}

class Solution {
  public int minimumEffortPath(int[][] heights) {
    final int m = heights.length;
    final int n = heights[0].length;
    final int[] dirs = {0, 1, 0, -1, 0};
    Queue<T> minHeap = new PriorityQueue<>((a, b) -> a.d - b.d);
    // dist[i][j] := max absolute difference to reach (i, j).
    int[][] diff = new int[m][n];
    Arrays.stream(diff).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));
    boolean[][] seen = new boolean[m][n];

    minHeap.offer(new T(0, 0, 0));
    diff[0][0] = 0;

    while (!minHeap.isEmpty()) {
      final int i = minHeap.peek().i;
      final int j = minHeap.peek().j;
      final int d = minHeap.poll().d;
      if (i == m - 1 && j == n - 1)
        return d;
      seen[i][j] = true;
      for (int k = 0; k < 4; ++k) {
        final int x = i + dirs[k];
        final int y = j + dirs[k + 1];
        if (x < 0 || x == m || y < 0 || y == n)
          continue;
        if (seen[x][y])
          continue;
        final int newDiff = Math.abs(heights[i][j] - heights[x][y]);
        final int maxDiff = Math.max(diff[i][j], newDiff);
        if (diff[x][y] > maxDiff) {
          diff[x][y] = maxDiff;
          minHeap.offer(new T(x, y, maxDiff));
        }
      }
    }

    throw new IllegalArgumentException();
  }
}