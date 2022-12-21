/*
169. Majority Element
Easy

Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.


Example 1:

Input: nums = [3,2,3]
Output: 3


Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2
 

Constraints:

n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109
*/


class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> pairs = new HashMap<Integer, Integer>();
        for(int x : nums){
            if(pairs.containsKey(x)){
                int value = pairs.get(x);
                pairs.put(x, ++value);
                continue;
            }
            pairs.put(x, 1);
        }
        int max = Collections.max(pairs.values());
        int key = 0;
        for (Map.Entry<Integer, Integer> entry : pairs.entrySet()){
            if (entry.getValue()==max) key = entry.getKey();
        }
        return key;
    } 
}