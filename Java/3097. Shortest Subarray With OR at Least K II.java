/*
# 3097. Shortest Subarray With OR at Least K II



You are given an array nums of non-negative integers and an integer k.

An array is called special if the bitwise OR of all of its elements is at least k.

Return the length of the shortest special non-empty subarray of nums, or return -1 if no special subarray exists.


Example 1:

Input: nums = [1,2,3], k = 2

Output: 1

Explanation:

The subarray [3] has OR value of 3. Hence, we return 1.


Example 2:

Input: nums = [2,1,8], k = 10

Output: 3

Explanation:

The subarray [2,1,8] has OR value of 11. Hence, we return 3.


Example 3:

Input: nums = [1,2], k = 0

Output: 1

Explanation:

The subarray [1] has OR value of 1. Hence, we return 1.

 
Constraints:

1 <= nums.length <= 2 * 105
0 <= nums[i] <= 109
0 <= k <= 109
*/







class Solution {
    // Same as 3095. Shortest Subarray With OR at Least K I
    public int minimumSubarrayLength(int[] nums, int k) {
        final int kMax = 50;
        final int n = nums.length;
        int ans = n + 1;
        int ors = 0;
        int[] count = new int[kMax + 1];

        for (int l = 0, r = 0; r < n; ++r) {
            ors = orNum(ors, nums[r], count);
            while (ors >= k && l <= r) {
                ans = Math.min(ans, r - l + 1);
                ors = undoOrNum(ors, nums[l], count);
                ++l;
            }
        }

        return (ans == n + 1) ? -1 : ans;
    }

    private static final int kMaxBit = 30;

    private int orNum(int ors, int num, int[] count) {
        for (int i = 0; i < kMaxBit; ++i)
            if ((num >> i & 1) == 1 && ++count[i] == 1)
                ors += 1 << i;
        return ors;
    }

    private int undoOrNum(int ors, int num, int[] count) {
        for (int i = 0; i < kMaxBit; ++i)
            if ((num >> i & 1) == 1 && --count[i] == 0)
                ors -= 1 << i;
        return ors;
    }
}