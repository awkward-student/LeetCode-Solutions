/*
# 1092. Shortest Common Supersequence 


Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.

A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.

Example 1:
Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.

Example 2:
Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
Output: "aaaaaaaa"
 
Constraints:
1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters.
*/






class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        StringBuilder sb = new StringBuilder();
        int i = 0; // str1's index
        int j = 0; // str2's index

        for (final char c : lcs(str1, str2).toCharArray()) {
            // Append the letters that are not part of the LCS.
            while (str1.charAt(i) != c)
                sb.append(str1.charAt(i++));
            while (str2.charAt(j) != c)
                sb.append(str2.charAt(j++));
            // Append the letter of the LCS and match it with str1 and str2.
            sb.append(c);
            ++i;
            ++j;
        }

        // Append the remaining letters.
        return sb.toString() + str1.substring(i) + str2.substring(j);
    }

    private String lcs(final String a, final String b) {
        final int m = a.length();
        final int n = b.length();
        // dp[i][j] := the length of LCS(a[0..i), b[0..j))
        StringBuilder[][] dp = new StringBuilder[m + 1][n + 1];

        for (final StringBuilder[] row : dp)
            for (int i = 0; i < row.length; ++i)
                row[i] = new StringBuilder();

        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j)
                if (a.charAt(i - 1) == b.charAt(j - 1))
                    dp[i][j].append(dp[i - 1][j - 1]).append(a.charAt(i - 1));
                else
                    dp[i][j] = dp[i - 1][j].length() > dp[i][j - 1].length() ? dp[i - 1][j] : dp[i][j - 1];

        return dp[m][n].toString();
    }
}