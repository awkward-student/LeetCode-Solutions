/*
 * 
 * #2218. Maximum Value of K Coins From Piles
 * 
 * 

There are n piles of coins on a table. Each pile consists of a positive number of coins of assorted denominations.

In one move, you can choose any coin on top of any pile, remove it, and add it to your wallet.

Given a list piles, where piles[i] is a list of integers denoting the composition of the ith pile from top to bottom, and a positive integer k, return the maximum total value of coins you can have in your wallet if you choose exactly k coins optimally.

 

Example 1:


Input: piles = [[1,100,3],[7,8,9]], k = 2
Output: 101
Explanation:
The above diagram shows the different ways we can choose k coins.
The maximum total we can obtain is 101.
Example 2:

Input: piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
Output: 706
Explanation:
The maximum total can be obtained if we choose all coins from the last pile.
 

Constraints:

n == piles.length
1 <= n <= 1000
1 <= piles[i][j] <= 105
1 <= k <= sum(piles[i].length) <= 2000
 */


 
 class Solution {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
      // dp[i][k] := max value of picking k coins from piles[i:]
      dp = new Integer[piles.size()][k + 1];
      return maxValueOfCoins(piles, 0, k);
    }
  
    private Integer[][] dp;
  
    private int maxValueOfCoins(List<List<Integer>> piles, int i, int k) {
      if (i == piles.size() || k == 0)
        return 0;
      if (dp[i][k] != null)
        return dp[i][k];
  
      int ans = maxValueOfCoins(piles, i + 1, k); // Pick 0 coins from current pile
      int val = 0;                                // Coins picked from current pile
  
      // Try to pick 1, 2, ..., k coins from current pile
      for (int j = 0; j < Math.min(piles.get(i).size(), k); ++j) {
        val += piles.get(i).get(j);
        ans = Math.max(ans, val + maxValueOfCoins(piles, i + 1, k - j - 1));
      }
  
      return dp[i][k] = ans;
    }
  }