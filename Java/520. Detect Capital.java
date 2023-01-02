/*
520. Detect Capital

Easy

We define the usage of capitals in a word to be right when one of the following cases holds:
All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital, like "Google".
Given a string word, return true if the usage of capitals in it is right.

Example 1:
Input: word = "USA"
Output: true

Example 2:
Input: word = "FlaG"
Output: false
 
Constraints:
1 <= word.length <= 100
word consists of lowercase and uppercase English letters.
*/



class Solution {
    public boolean detectCapitalUse(String word) {
       if(word.length()==1) return true;
       char[] array = word.toCharArray();
       for(int i=0; i<word.length(); i++){
           if(Character.isLowerCase(array[0])){
               if(Character.isUpperCase(array[i])) return false;
           }
           else if(Character.isUpperCase(array[0]) && Character.isLowerCase(array[1])){
               if(i==0 || i==1) continue;
               if(Character.isUpperCase(array[i])) return false;
           }
           else if(Character.isUpperCase(array[0]) && Character.isUpperCase(array[1])){
               if(i==0 || i==1) continue;
               if(Character.isLowerCase(array[i])) return false;
           }
       } 
       return true;
    }
}