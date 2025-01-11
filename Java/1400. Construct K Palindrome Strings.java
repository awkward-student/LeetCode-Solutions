/*
# 1400. Construct K Palindrome Strings


Given a string s and an integer k, return true if you can use all the characters in s to construct k palindrome strings or false otherwise.

Example 1:
Input: s = "annabelle", k = 2
Output: true
Explanation: You can construct two palindromes using all characters in s.
Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"

Example 2:
Input: s = "leetcode", k = 3
Output: false
Explanation: It is impossible to construct 3 palindromes using all the characters of s.

Example 3:
Input: s = "true", k = 4
Output: true
Explanation: The only possible solution is to put each character in a separate string.
 
Constraints:
1 <= s.length <= 105
s consists of lowercase English letters.
1 <= k <= 105
*/





class Solution {
    public boolean canConstruct(String s, int k) {
        // If |s| < k, we cannot construct k strings from the s.
        if (s.length() < k)
            return false;

        int[] count = new int[26];

        for (final char c : s.toCharArray())
            count[c - 'a'] ^= 1;

        // If the number of letters that have odd counts > k, the minimum number of
        // palindromic strings we can construct is > k.
        return Arrays.stream(count).filter(c -> c % 2 == 1).count() <= k;
    }
}