/*
# 1002. Find Common Characters


Given a string array words, return an array of all characters that show up in all strings 
within the words (including duplicates). You may return the answer in any order.
 
Example 1:
Input: words = ["bella","label","roller"]
Output: ["e","l","l"]

Example 2:
Input: words = ["cool","lock","cook"]
Output: ["c","o"]
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of lowercase English letters.
*/





class Solution {
    public List<String> commonChars(String[] words) {
        List<String> ans = new ArrayList<>();
        int[] commonCount = new int[26];
        Arrays.fill(commonCount, Integer.MAX_VALUE);

        for (String a : A) {
            int[] count = new int[26];
            for (char c : a.toCharArray())
                ++count[c - 'a'];
            for (int i = 0; i < 26; ++i)
                commonCount[i] = Math.min(commonCount[i], count[i]);
        }

        for (char c = 'a'; c <= 'z'; ++c)
            for (int i = 0; i < commonCount[c - 'a']; ++i)
                ans.add(String.valueOf(c));

        return ans;
    }
}