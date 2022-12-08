/*
383. Ransom Note
Easy

Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.

Each letter in magazine can only be used once in ransomNote.

 

Example 1:

Input: ransomNote = "a", magazine = "b"
Output: false
Example 2:

Input: ransomNote = "aa", magazine = "ab"
Output: false
Example 3:

Input: ransomNote = "aa", magazine = "aab"
Output: true
 

Constraints:

1 <= ransomNote.length, magazine.length <= 105
ransomNote and magazine consist of lowercase English letters.
*/

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        char[] ransom = ransomNote.toCharArray();
        char[] mag = magazine.toCharArray();
        for(int i=0; i<ransom.length; i++){
            for(int j=0; j<mag.length; j++){
                if(ransom[i]==mag[j]){
                    ransom[i] = '0';
                    mag[j] = '0';
                    break;
                }
                else continue;
            }
            if(ransom[i]!='0') return false;
        }
        return true;
    }
}