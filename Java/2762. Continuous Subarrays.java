/*
# 2762. Continuous Subarrays


You are given a 0-indexed integer array nums. A subarray of nums is called continuous if:

Let i, i + 1, ..., j be the indices in the subarray. Then, for each pair of indices i <= i1, 
i2 <= j, 0 <= |nums[i1] - nums[i2]| <= 2.
Return the total number of continuous subarrays.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [5,4,2,4]
Output: 8
Explanation: 
Continuous subarray of size 1: [5], [4], [2], [4].
Continuous subarray of size 2: [5,4], [4,2], [2,4].
Continuous subarray of size 3: [4,2,4].
Thereare no subarrys of size 4.
Total continuous subarrays = 4 + 3 + 1 = 8.
It can be shown that there are no more continuous subarrays.
 
Example 2:
Input: nums = [1,2,3]
Output: 6
Explanation: 
Continuous subarray of size 1: [1], [2], [3].
Continuous subarray of size 2: [1,2], [2,3].
Continuous subarray of size 3: [1,2,3].
Total continuous subarrays = 3 + 2 + 1 = 6.
 
Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 109
*/





class Solution {
    public long continuousSubarrays(int[] nums) {
        long ans = 1; // [nums[0]]
        int left = nums[0] - 2;
        int right = nums[0] + 2;
        int l = 0;

        // nums[l..r] is a valid window with range in [left, right].
        for (int r = 1; r < nums.length; r++) {
            if (left <= nums[r] && nums[r] <= right) {
                left = Math.max(left, nums[r] - 2);
                right = Math.min(right, nums[r] + 2);
            } else {
                // nums[r] is out-of-bounds, so reconstruct the window.
                left = nums[r] - 2;
                right = nums[r] + 2;
                l = r;
                // If we consistently move leftward in each iteration, it implies that
                // the entire left subarray satisfies the given condition. For every
                // subarray with l in the range [0, r], the condition is met, preventing
                // the code from reaching the final "else" condition. Instead, it stops
                // at the "if" condition.
                while (nums[r] - 2 <= nums[l] && nums[l] <= nums[r] + 2) {
                    left = Math.max(left, nums[l] - 2);
                    right = Math.min(right, nums[l] + 2);
                    --l;
                }
                ++l;
            }
            // nums[l..r], nums[l + 1..r], ..., nums[r]
            ans += r - l + 1;
        }

        return ans;
    }
}