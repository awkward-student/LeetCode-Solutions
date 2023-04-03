/*
 * 
 * #704. Binary Search
 * 
 * 

Given an array of integers nums which is sorted in ascending order,
and an integer target, write a function to search target in nums. 
If target exists, then return its index. Otherwise, return -1.

You must write an algorithm with O(log n) runtime complexity.

 

Example 1:

Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
Example 2:

Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1
 

Constraints:

1 <= nums.length <= 104
-104 < nums[i], target < 104
All the integers in nums are unique.
nums is sorted in ascending order.
*/




class Solution {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;
        int mid = (start+end)/2;

        while(start<=end){
            mid = (start+end)/2;
            if(nums[mid] == target) return mid;
            if(nums[mid]<target){
                start = mid+1;
            }
            else if(nums[mid]>target){
                end = mid-1;
            }
        }
        return -1;
    }
}




// using java's inbuilt method 
// Arrays.binarySearch(int[] array, int key);


class Solution {
    public int search(int[] nums, int target) {
        int result = Arrays.binarySearch(nums, target);
        if (result<0) return -1;
        return result;
    }
}