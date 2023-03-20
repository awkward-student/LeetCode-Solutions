/*
 * 
 * #211. Design Add and Search Words Data Structure
 * 
 * 
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. 
word may contain dots '.' where dots can be matched with any letter.
 
Example:
Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True
 
Constraints:

1 <= word.length <= 25
word in addWord consists of lowercase English letters.
word in search consist of '.' or lowercase English letters.
There will be at most 3 dots in word for search queries.
At most 104 calls will be made to addWord and search.
*/



class WordDictionary {

    public WordDictionary() {
        
    }
    
    public void addWord(String word) {
        TrieNode node = root;
        for (final char c : word.toCharArray()) {
            final int i = c - 'a';
            if (node.children[i] == null) node.children[i] = new TrieNode();
            node = node.children[i];
        }
        node.isWord = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private TrieNode root = new TrieNode();

    private boolean dfs(String word, int s, TrieNode node) {
        if (s == word.length()) return node.isWord;
        if (word.charAt(s) != '.') {
            TrieNode next = node.children[word.charAt(s) - 'a'];
            return next == null ? false : dfs(word, s + 1, next);
        }
        for (int i = 0; i < 26; ++i)
            if (node.children[i] != null && dfs(word, s + 1, node.children[i]))
                return true;
        return false;
    }
}

class TrieNode {
  public TrieNode[] children = new TrieNode[26];
  public boolean isWord = false;
}


/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */