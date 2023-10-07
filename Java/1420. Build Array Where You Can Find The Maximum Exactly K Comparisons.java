/*
# 1420. Build Array Where You Can Find The Maximum Exactly K Comparisons

You are given three integers n, m and k. Consider the following algorithm to find the maximum element of an array of positive integers:


You should build the array arr which has the following properties:

arr has exactly n integers.
1 <= arr[i] <= m where (0 <= i < n).
After applying the mentioned algorithm to arr, the value search_cost is equal to k.
Return the number of ways to build the array arr under the mentioned conditions. As the answer may grow large, the answer must be computed modulo 109 + 7.


Example 1:
Input: n = 2, m = 3, k = 1
Output: 6
Explanation: The possible arrays are [1, 1], [2, 1], [2, 2], [3, 1], [3, 2] [3, 3]

Example 2:
Input: n = 5, m = 2, k = 3
Output: 0
Explanation: There are no possible arrays that satisify the mentioned conditions.

Example 3:
Input: n = 9, m = 1, k = 1
Output: 1
Explanation: The only possible array is [1, 1, 1, 1, 1, 1, 1, 1, 1]
 

Constraints:

1 <= n <= 50
1 <= m <= 100
0 <= k <= n
*/




class Solution {
  public int numOfArrays(int n, int m, int k) {
    final int kMod = 1_000_000_007;;
    int[][][] dp = new int[n + 1][m + 1][k + 1];

    for (int j = 1; j <= m; ++j)
      dp[1][j][1] = 1;

    for (int i = 2; i <= n; ++i)                // For each length
      for (int j = 1; j <= m; ++j)              // For each max value
        for (int cost = 1; cost <= k; ++cost) { // For each cost
          dp[i][j][cost] = (int) ((long) j * dp[i - 1][j][cost] % kMod);
          for (int prevMax = 1; prevMax < j; ++prevMax) {
            dp[i][j][cost] += dp[i - 1][prevMax][cost - 1];
            dp[i][j][cost] %= kMod;
          }
        }

    int ans = 0;
    for (int j = 1; j <= m; ++j) {
      ans += dp[n][j][k];
      ans %= kMod;
    }
    return ans;
  }
}