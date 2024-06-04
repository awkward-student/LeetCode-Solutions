/*
# 409. Longest Palindrome


Given a string s which consists of lowercase or uppercase letters, return the length of the longest 
palindrome that can be built with those letters.

Letters are case sensitive, for example, "Aa" is not considered a palindrome.


Example 1:
Input: s = "abccccdd"
Output: 7
Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.

Example 2:
Input: s = "a"
Output: 1
Explanation: The longest palindrome that can be built is "a", whose length is 1.
 

Constraints:

1 <= s.length <= 2000
s consists of lowercase and/or uppercase English letters only.
*/





class Solution {
    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char x: s.toCharArray()){
            if(map.containsKey(x)) map.put(x, map.get(x)+1);
            else map.put(x, 1);
        }
        int len = 0;
        boolean isOdd = false;
        for(Map.Entry<Character, Integer> set: map.entrySet()){
            if((set.getValue() & 1) == 1){
                isOdd = true;
                len += (set.getValue() - 1);
            }
            else len += set.getValue();
        }
        if(isOdd) return len + 1;
        return len;
    }
}





class Solution {
    public int longestPalindrome(String s) {
        int ans = 0;
        int[] count = new int[128];

        for (final char c : s.toCharArray())
            ++count[c];

        for (final int freq : count)
            ans += freq % 2 == 0 ? freq : freq - 1;

        final boolean hasOddCount = Arrays.stream(count).anyMatch(freq -> freq % 2 == 1);
        return ans + (hasOddCount ? 1 : 0);
    }
}