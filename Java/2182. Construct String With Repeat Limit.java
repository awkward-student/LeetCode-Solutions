/*
# 2182. Construct String With Repeat Limit


You are given a string s and an integer repeatLimit. Construct a new string repeatLimitedString using the characters of s such that no letter appears more than repeatLimit times in a row. You do not have to use all characters from s.

Return the lexicographically largest repeatLimitedString possible.

A string a is lexicographically larger than a string b if in the first position where a and b differ, string a has a letter that appears later in the alphabet than the corresponding letter in b. If the first min(a.length, b.length) characters do not differ, then the longer string is the lexicographically larger one.

Example 1:
Input: s = "cczazcc", repeatLimit = 3
Output: "zzcccac"
Explanation: We use all of the characters from s to construct the repeatLimitedString "zzcccac".
The letter 'a' appears at most 1 time in a row.
The letter 'c' appears at most 3 times in a row.
The letter 'z' appears at most 2 times in a row.
Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
The string is the lexicographically largest repeatLimitedString possible so we return "zzcccac".
Note that the string "zzcccca" is lexicographically larger but the letter 'c' appears more than 3 times in a row, so it is not a valid repeatLimitedString.

Example 2:
Input: s = "aababab", repeatLimit = 2
Output: "bbabaa"
Explanation: We use only some of the characters from s to construct the repeatLimitedString "bbabaa". 
The letter 'a' appears at most 2 times in a row.
The letter 'b' appears at most 2 times in a row.
Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
The string is the lexicographically largest repeatLimitedString possible so we return "bbabaa".
Note that the string "bbabaaa" is lexicographically larger but the letter 'a' appears more than 2 times in a row, so it is not a valid repeatLimitedString.
 
Constraints:
1 <= repeatLimit <= s.length <= 105
s consists of lowercase English letters.
*/





class Solution {
    public String repeatLimitedString(String s, int repeatLimit) {
        StringBuilder sb = new StringBuilder();
        int[] count = new int[26];

        for (final char c : s.toCharArray())
            ++count[c - 'a'];

        while (true) {
            final boolean addOne = !sb.isEmpty() && shouldAddOne(sb, count);
            final int i = getLargestChar(sb, count);
            if (i == -1)
                break;
            final int repeats = addOne ? 1 : Math.min(count[i], repeatLimit);
            sb.append(String.valueOf((char) ('a' + i)).repeat(repeats));
            count[i] -= repeats;
        }

        return sb.toString();
    }

    private boolean shouldAddOne(StringBuilder sb, int[] count) {
        for (int i = 25; i >= 0; --i)
            if (count[i] > 0)
                return sb.charAt(sb.length() - 1) == 'a' + i;
        return false;
    }

    private int getLargestChar(StringBuilder sb, int[] count) {
        for (int i = 25; i >= 0; --i)
            if (count[i] > 0 && (sb.isEmpty() || sb.charAt(sb.length() - 1) != 'a' + i))
                return i;
        return -1;
    }
}