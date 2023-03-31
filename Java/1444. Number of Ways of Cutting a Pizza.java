/*
 * 
 * #1444. Number of Ways of Cutting a Pizza
 * 
 * 

Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k. You have to cut the pizza into k pieces using k-1 cuts. 

For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces. If you cut the pizza vertically, give the left part of the pizza to a person. If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.

Return the number of ways of cutting the pizza such that each piece contains at least one apple. Since the answer can be a huge number, return this modulo 10^9 + 7.

 

Example 1:



Input: pizza = ["A..","AAA","..."], k = 3
Output: 3 
Explanation: The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.
Example 2:

Input: pizza = ["A..","AA.","..."], k = 3
Output: 1
Example 3:

Input: pizza = ["A..","A..","..."], k = 1
Output: 1
 

Constraints:

1 <= rows, cols <= 50
rows == pizza.length
cols == pizza[i].length
1 <= k <= 10
pizza consists of characters 'A' and '.' only.

*/



class Solution {
  public int ways(String[] pizza, int k) {
    final int M = pizza.length;
    final int N = pizza[0].length();
    // dp[m][n][k] := # of ways to cut pizza[m:M][n:N] w/ k cuts
    dp = new int[M][N][k];
    for (int[][] A : dp)
      Arrays.stream(A).forEach(a -> Arrays.fill(a, -1));
    prefix = new int[M + 1][N + 1];

    for (int i = 0; i < M; ++i)
      for (int j = 0; j < N; ++j)
        prefix[i + 1][j + 1] = (pizza[i].charAt(j) == 'A' ? 1 : 0) + prefix[i][j + 1] +
                               prefix[i + 1][j] - prefix[i][j];

    return ways(0, 0, k - 1, M, N);
  }

  private static final int kMod = 1_000_000_007;
  private int[][][] dp;
  private int[][] prefix;

  // HasApple of pizza[row1..row2)[col1..col2)
  private boolean hasApple(int row1, int row2, int col1, int col2) {
    return (prefix[row2][col2] - prefix[row1][col2] - prefix[row2][col1] + prefix[row1][col1]) > 0;
  }

  private int ways(int m, int n, int k, final int M, final int N) {
    if (k == 0)
      return 1;
    if (dp[m][n][k] >= 0)
      return dp[m][n][k];

    dp[m][n][k] = 0;

    for (int i = m + 1; i < M; ++i) // Cut horizontally
      if (hasApple(m, i, n, N) && hasApple(i, M, n, N))
        dp[m][n][k] = (dp[m][n][k] + ways(i, n, k - 1, M, N)) % kMod;

    for (int j = n + 1; j < N; ++j) // Cut vertically
      if (hasApple(m, M, n, j) && hasApple(m, M, j, N))
        dp[m][n][k] = (dp[m][n][k] + ways(m, j, k - 1, M, N)) % kMod;

    return dp[m][n][k];
  }
}