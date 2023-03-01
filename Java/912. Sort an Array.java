/*

#912. Sort an Array

Given an array of integers nums, sort the array in ascending order and return it.
You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.
 

Example 1:
Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the positions of other numbers are changed (for example, 1 and 5).

Example 2:
Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
Explanation: Note that the values of nums are not necessairly unique.
 

Constraints:
1 <= nums.length <= 5 * 104
-5 * 104 <= nums[i] <= 5 * 104
*/


class Solution {
    public int[] sortArray(int[] nums) {
        int N = nums.length;
  
        for (int i = N / 2 - 1; i >= 0; i--)
            heapify(nums, N, i);
  
        for (int i = N - 1; i > 0; i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
  
            heapify(nums, i, 0);
        }
        
        return nums;
    }

    public void heapify(int nums[], int N, int i)
    {
        int largest = i; 
        int l = 2 * i + 1; 
        int r = 2 * i + 2; 
  
        if (l < N && nums[l] > nums[largest])
            largest = l;
  
        if (r < N && nums[r] > nums[largest])
            largest = r;
  
        if (largest != i) {
            int swap = nums[i];
            nums[i] = nums[largest];
            nums[largest] = swap;
            heapify(nums, N, largest);
        }
    }
}