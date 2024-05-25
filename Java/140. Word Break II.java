/*
# 140. Word Break II


Given a string s and a dictionary of strings wordDict, add spaces in s to construct a 
sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.


Example 1:
Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]

Example 2:
Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []
 

Constraints:

1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
Input is generated in a way that the length of the answer doesn't exceed 105.
*/





class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Map<String, List<String>> mem = new HashMap<>();
        return wordBreak(s, wordSet, mem);
    }

    private List<String> wordBreak(final String s, Set<String> wordSet,
            Map<String, List<String>> mem) {
        if (mem.containsKey(s))
            return mem.get(s);

        List<String> ans = new ArrayList<>();

        // 1 <= prefix.length() < s.length()
        for (int i = 1; i < s.length(); ++i) {
            final String prefix = s.substring(0, i);
            final String suffix = s.substring(i);
            if (wordSet.contains(prefix))
                for (final String word : wordBreak(suffix, wordSet, mem))
                    ans.add(prefix + " " + word);
        }

        // `wordSet` contains the whole string s, so don't add any space.
        if (wordSet.contains(s))
            ans.add(s);

        mem.put(s, ans);
        return ans;
    }
}