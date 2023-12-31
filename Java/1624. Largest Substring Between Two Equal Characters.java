/*
# 1624. Largest Substring Between Two Equal Characters

Given a string s, return the length of the longest substring between two equal characters, excluding the two characters. If there is no such substring return -1.

A substring is a contiguous sequence of characters within a string.
 

Example 1:
Input: s = "aa"
Output: 0
Explanation: The optimal substring here is an empty substring between the two 'a's.

Example 2:
Input: s = "abca"
Output: 2
Explanation: The optimal substring here is "bc".

Example 3:
Input: s = "cbzxy"
Output: -1
Explanation: There are no characters that appear twice in s.
 

Constraints:

1 <= s.length <= 300
s contains only lowercase English letters.
*/





// solution I
class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
        int ll = 0;
        for(int i=0; i<=s.length()-2; i++){
            for(int j=s.length()-1; j>i; j--){
                if(s.charAt(i) == s.charAt(j)){
                    ll = Math.max(ll, (j-i));
                    break;
                }
            }
        }
        return ll-1;
    }
}



// solution II
class Solution {
  public int maxLengthBetweenEqualCharacters(String s) {
    int ans = -1;
    int[] lastSeen = new int[26];
    Arrays.fill(lastSeen, -1);

    for (int i = 0; i < s.length(); ++i) {
      final int c = s.charAt(i) - 'a';
      if (lastSeen[c] == -1)
        lastSeen[c] = i;
      else
        ans = Math.max(ans, i - lastSeen[c] - 1);
    }

    return ans;
  }
}