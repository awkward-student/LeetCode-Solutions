/*
# 557. Reverse Words in a String III

Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

 
Example 1:
Input: s = "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"

Example 2:
Input: s = "God Ding"
Output: "doG gniD"
 

Constraints:

1 <= s.length <= 5 * 104
s contains printable ASCII characters.
s does not contain any leading or trailing spaces.
There is at least one word in s.
All the words in s are separated by a single space.
*/




class Solution {
  public String reverseWords(String s) {
    StringBuilder sb = new StringBuilder(s);
    int i = 0;
    int j = 0;

    while (i < sb.length()) {
      while (i < j || i < sb.length() && sb.charAt(i) == ' ')
        ++i;
      while (j < i || j < sb.length() && sb.charAt(j) != ' ')
        ++j;
      reverse(sb, i, j - 1);
    }

    return sb.toString();
  }

  private void reverse(StringBuilder sb, int l, int r) {
    while (l < r) {
      final char temp = sb.charAt(l);
      sb.setCharAt(l++, sb.charAt(r));
      sb.setCharAt(r--, temp);
    }
  }
}