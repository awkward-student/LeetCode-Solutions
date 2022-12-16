/*
1207. Unique Number of Occurrences
Easy

Given an array of integers arr, return true if the number of occurrences of each value in the array is unique or false otherwise.

 

Example 1:

Input: arr = [1,2,2,1,1,3]
Output: true
Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.


Example 2:

Input: arr = [1,2]
Output: false


Example 3:

Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
Output: true
 

Constraints:

1 <= arr.length <= 1000
-1000 <= arr[i] <= 1000
*/

import java.util.*;
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        HashMap <Integer, Integer> map = new HashMap<>();
        for(int x:arr){
            if(map.containsKey(x)){
                int value = map.get(x) + 1;
                map.replace(x, value);
                continue;
            }
            map.put(x, 1);
        }
        Collection <Integer> elements = map.values();
        Object[] list = elements.toArray();
        Arrays.sort(list);
        for(int i=0; i<list.length-1; i++){
            if(list[i].equals(list[i+1])){
                return false;
            }
        }
        return true;
    }
}