/*
# 862. Shortest Subarray with Sum at Least K


Given an integer array nums and an integer k, 
return the length of the shortest non-empty subarray of nums with a sum of at least k. 
If there is no such subarray, return -1.

A subarray is a contiguous part of an array.

Example 1:
Input: nums = [1], k = 1
Output: 1

Example 2:
Input: nums = [1,2], k = 4
Output: -1

Example 3:
Input: nums = [2,-1,2], k = 3
Output: 3
 
Constraints:
1 <= nums.length <= 105
-105 <= nums[i] <= 105
1 <= k <= 109
*/





class Solution {
    public int shortestSubarray(int[] nums, int k) {
        final int n = nums.length;
        int ans = n + 1;
        Deque<Integer> dq = new ArrayDeque<>();
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; ++i)
            prefix[i + 1] = (long) nums[i] + prefix[i];

        for (int i = 0; i < n + 1; ++i) {
            while (!dq.isEmpty() && prefix[i] - prefix[dq.getFirst()] >= k)
                ans = Math.min(ans, i - dq.pollFirst());
            while (!dq.isEmpty() && prefix[i] <= prefix[dq.getLast()])
                dq.pollLast();
            dq.addLast(i);
        }

        return ans <= n ? ans : -1;
    }
}