/*
# 1425. Constrained Subsequence Sum

Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.

A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.

Example 1:
Input: nums = [10,2,-10,5,20], k = 2
Output: 37
Explanation: The subsequence is [10, 2, 5, 20].

Example 2:
Input: nums = [-1,-2,-3], k = 1
Output: -1
Explanation: The subsequence must be non-empty, so we choose the largest number.

Example 3:
Input: nums = [10,-2,-10,-5,20], k = 2
Output: 23
Explanation: The subsequence is [10, -2, -5, 20].
 

Constraints:

1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104
*/




class Solution {
  public int constrainedSubsetSum(int[] nums, int k) {
    // dp[i] := max sum of non-empty subsequence in nums[0..i]
    int[] dp = new int[nums.length];
    // dq stores dp[i - k], dp[i - k + 1], ..., dp[i - 1] whose values are > 0
    // in decreasing order.
    Deque<Integer> dq = new ArrayDeque<>();

    for (int i = 0; i < nums.length; ++i) {
      if (dq.isEmpty())
        dp[i] = nums[i];
      else
        dp[i] = Math.max(dq.peekFirst(), 0) + nums[i];
      while (!dq.isEmpty() && dq.peekLast() < dp[i])
        dq.pollLast();
      dq.offerLast(dp[i]);
      if (i >= k && dp[i - k] == dq.peekFirst())
        dq.pollFirst();
    }

    return Arrays.stream(dp).max().getAsInt();
  }
}