/*
# 1408. String Matching in an Array


Given an array of string words, return all strings in words that is a substring of another word. 
You can return the answer in any order.

A substring is a contiguous sequence of characters within a string

Example 1:
Input: words = ["mass","as","hero","superhero"]
Output: ["as","hero"]
Explanation: "as" is substring of "mass" and "hero" is substring of "superhero".
["hero","as"] is also a valid answer.

Example 2:
Input: words = ["leetcode","et","code"]
Output: ["et","code"]
Explanation: "et", "code" are substring of "leetcode".

Example 3:
Input: words = ["blue","green","bu"]
Output: []
Explanation: No string of words is substring of another string.

Constraints:
1 <= words.length <= 100
1 <= words[i].length <= 30
words[i] contains only lowercase English letters.
All the strings of words are unique.
*/





Approach 1: Brute Force

class Solution {
  public List<String> stringMatching(String[] words) {
    List<String> ans = new ArrayList<>();
    for (final String a : words)
      for (final String b : words)
        if (a.length() < b.length() && b.indexOf(a) != -1) {
          ans.add(a);
          break;
        }
    return ans;
  }
}



Approach 2: Trie w/ sort¶

class TrieNode {
  public TrieNode[] children = new TrieNode[26];
}

class Trie {
  public void insert(String word) {
    TrieNode node = root;
    for (final char c : word.toCharArray()) {
      final int i = c - 'a';
      if (node.children[i] == null)
        node.children[i] = new TrieNode();
      node = node.children[i];
    }
  }

  public boolean search(String word) {
    TrieNode node = root;
    for (final char c : word.toCharArray()) {
      final int i = c - 'a';
      if (node.children[i] == null)
        return false;
      node = node.children[i];
    }
    return true;
  }

  private TrieNode root = new TrieNode();
}

class Solution {
  public List<String> stringMatching(String[] words) {
    List<String> ans = new ArrayList<>();
    Trie trie = new Trie();

    Arrays.sort(words, (a, b) -> Integer.compare(b.length(), a.length()));

    for (final String word : words) {
      if (trie.search(word))
        ans.add(word);
      for (int i = 0; i < word.length(); ++i)
        trie.insert(word.substring(i));
    }

    return ans;
  }
}
