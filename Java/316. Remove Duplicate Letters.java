/*
# 316. Remove Duplicate Letters

Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is 
the smallest in lexicographical order
 among all possible results.

 
Example 1:
Input: s = "bcabc"
Output: "abc"

Example 2:
Input: s = "cbacdcbc"
Output: "acdb"
 

Constraints:

1 <= s.length <= 104
s consists of lowercase English letters.
 

Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
*/




class Solution {
  public String removeDuplicateLetters(String s) {
    StringBuilder sb = new StringBuilder();
    int[] count = new int[128];
    boolean[] used = new boolean[128];

    for (final char c : s.toCharArray())
      ++count[c];

    for (final char c : s.toCharArray()) {
      --count[c];
      if (used[c])
        continue;
      while (sb.length() > 0 && last(sb) > c && count[last(sb)] > 0) {
        used[last(sb)] = false;
        sb.setLength(sb.length() - 1);
      }
      used[c] = true;
      sb.append(c);
    }

    return sb.toString();
  }

  private char last(StringBuilder sb) {
    return sb.charAt(sb.length() - 1);
  }
}