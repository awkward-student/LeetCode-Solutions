/* 
345. Reverse Vowels of a String

Easy

Given a string s, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.

Example 1:

Input: s = "hello"
Output: "holle"
Example 2:

Input: s = "leetcode"
Output: "leotcede"
 

Constraints:

1 <= s.length <= 3 * 105
s consist of printable ASCII characters.

*/


class Solution {
    public String reverseVowels(String s) {
        char[] array = s.toCharArray();
        ArrayList<Character> vowels = new ArrayList<Character>();
        vowels.add('A');
        vowels.add('a');
        vowels.add('E');
        vowels.add('e');
        vowels.add('I');
        vowels.add('i');
        vowels.add('O');
        vowels.add('o');
        vowels.add('U');
        vowels.add('u');
        int ascIndx=0, dscIndx=s.length()-1;
        while(ascIndx<dscIndx){
            if(!vowels.contains(array[ascIndx])){
                ascIndx++;
                continue;
            }
            if(!vowels.contains(array[dscIndx])){
                dscIndx--;
                continue;
            }
            char swapper=array[ascIndx];
            array[ascIndx]=array[dscIndx];
            array[dscIndx]=swapper;
            ascIndx++;
            dscIndx--;
        }
        return new String(array);
    }
}