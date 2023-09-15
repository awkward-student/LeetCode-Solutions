/*
# 1584. Min Cost to Connect All Points

You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

 

Example 1:
Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation: 

We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.


Example 2:

Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18
 

Constraints:

1 <= points.length <= 1000
-106 <= xi, yi <= 106
All pairs (xi, yi) are distinct.
*/




class Solution {
  public int minCostConnectPoints(int[][] points) {
    // dist[i] := min distance to connect points[i]
    int[] dist = new int[points.length];
    Arrays.fill(dist, Integer.MAX_VALUE);
    int ans = 0;

    for (int i = 0; i < points.length - 1; ++i) {
      for (int j = i + 1; j < points.length; ++j) {
        // Try to connect points[i] with points[j].
        dist[j] = Math.min(dist[j], Math.abs(points[i][0] - points[j][0]) +
                                        Math.abs(points[i][1] - points[j][1]));
        // Swap points[j] (point with min dist) with points[i + 1].
        if (dist[j] < dist[i + 1]) {
          final int[] tempPoint = points[j];
          points[j] = points[i + 1];
          points[i + 1] = tempPoint;
          final int tempDist = dist[j];
          dist[j] = dist[i + 1];
          dist[i + 1] = tempDist;
        }
      }
      ans += dist[i + 1];
    }

    return ans;
  }
}