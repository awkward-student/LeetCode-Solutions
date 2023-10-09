/*
# 34. Find First and Last Position of Element in Sorted Array

Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.

 
Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Example 3:
Input: nums = [], target = 0
Output: [-1,-1]
 

Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums is a non-decreasing array.
-109 <= target <= 109
*/



class Solution {
    public int[] searchRange(int[] nums, int target) {
        final int lower = findUpperEqual(nums, target);
        if(lower == nums.length || nums[lower] != target)
            return new int[] {-1, -1};
        final int upper = findUpperEqual(nums, target+1) - 1;
        return new int[] {lower, upper};
    }

    private int findUpperEqual(int[] array, int key){
        int lower = 0;
        int upper = array.length;
        while(lower < upper){
            final int mid = (lower + upper)/2;
            if(array[mid] >= key) upper = mid;
            else lower = mid + 1;
        }
        return lower;
    }
}