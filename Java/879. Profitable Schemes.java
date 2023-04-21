/*
 * 
 * #879. Profitable Schemes
 * 
 * 

There is a group of n members, and a list of various crimes they could commit. The ith crime generates a profit[i] and requires group[i] members to participate in it. If a member participates in one crime, that member can't participate in another crime.

Let's call a profitable scheme any subset of these crimes that generates at least minProfit profit, and the total number of members participating in that subset of crimes is at most n.

Return the number of schemes that can be chosen. Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:

Input: n = 5, minProfit = 3, group = [2,2], profit = [2,3]
Output: 2
Explanation: To make a profit of at least 3, the group could either commit crimes 0 and 1, or just crime 1.
In total, there are 2 schemes.
Example 2:

Input: n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
Output: 7
Explanation: To make a profit of at least 5, the group could commit any crimes, as long as they commit one.
There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).
 

Constraints:

1 <= n <= 100
0 <= minProfit <= 100
1 <= group.length <= 100
1 <= group[i] <= 100
profit.length == group.length
0 <= profit[i] <= 100
*/



class Solution {
  public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
    final int kMod = 1_000_000_007;
    int[][][] dp = new int[group.length + 1][n + 1][minProfit + 1];

    for (int i = 0; i <= n; ++i)
      dp[0][i][0] = 1;

    for (int k = 1; k <= group.length; ++k) {
      final int g = group[k - 1];
      final int p = profit[k - 1];
      for (int i = 0; i <= n; ++i)
        for (int j = 0; j <= minProfit; ++j)
          if (i < g) {
            dp[k][i][j] = dp[k - 1][i][j];
          } else {
            dp[k][i][j] = dp[k - 1][i][j] + dp[k - 1][i - g][Math.max(0, j - p)];
            dp[k][i][j] %= kMod;
          }
    }

    return dp[group.length][n][minProfit];
  }
}