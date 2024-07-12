/*
# 1717. Maximum Score From Removing Substrings


You are given a string s and two integers x and y. You can perform two types of operations any number of times.

Remove substring "ab" and gain x points.
For example, when removing "ab" from "cabxbae" it becomes "cxbae".
Remove substring "ba" and gain y points.
For example, when removing "ba" from "cabxbae" it becomes "cabxe".
Return the maximum points you can gain after applying the above operations on s.

 
Example 1:
Input: s = "cdbcbbaaabab", x = 4, y = 5
Output: 19
Explanation:
- Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
- Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
- Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
- Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
Total score = 5 + 4 + 5 + 5 = 19.

Example 2:
Input: s = "aabbaaxybbaabb", x = 5, y = 4
Output: 20
 

Constraints:

1 <= s.length <= 105
1 <= x, y <= 104
s consists of lowercase English letters.
*/






class Solution {
    public int maximumGain(String s, int x, int y) {
        return x > y ? gain(s, "ab", x, "ba", y) : gain(s, "ba", y, "ab", x);
    }

    private int gain(final String s, final String sub1, int point1, final String sub2, int point2) {
        int points = 0;
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();

        for (final char c : s.toCharArray())
            if (!stack1.isEmpty() && stack1.peek() == sub1.charAt(0) && c == sub1.charAt(1)) {
                stack1.pop();
                points += point1;
            } else {
                stack1.push(c);
            }

        for (final char c : stack1)
            if (!stack2.isEmpty() && stack2.peek() == sub2.charAt(0) && c == sub2.charAt(1)) {
                stack2.pop();
                points += point2;
            } else {
                stack2.push(c);
            }

        return points;
    }
}