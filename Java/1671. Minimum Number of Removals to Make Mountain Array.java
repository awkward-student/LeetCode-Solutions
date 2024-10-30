*
# 1671. Minimum Number of Removals to Make Mountain Array


You may recall that an array arr is a mountain array if and only if:

arr.length >= 3
There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given an integer array nums​​​, return the minimum number of elements to remove to make nums​​​ a mountain array.

Example 1:
Input: nums = [1,3,1]
Output: 0
Explanation: The array itself is a mountain array so we do not need to remove any elements.

Example 2:
Input: nums = [2,1,1,5,6,2,3,1]
Output: 3
Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].
 
Constraints:

3 <= nums.length <= 1000
1 <= nums[i] <= 109
It is guaranteed that you can make a mountain array out of nums.
*/






class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int[] l = lengthOfLIS(nums);
        int[] r = reversed(lengthOfLIS(reversed(nums)));
        int maxMountainSeq = 0;

        for (int i = 0; i < nums.length; ++i)
            if (l[i] > 1 && r[i] > 1)
                maxMountainSeq = Math.max(maxMountainSeq, l[i] + r[i] - 1);

        return nums.length - maxMountainSeq;
    }

    // Similar to 300. Longest Increasing Subsequence
    private int[] lengthOfLIS(int[] nums) {
        // tails[i] := the minimum tail of all the increasing subsequences with
        // length i + 1
        List<Integer> tails = new ArrayList<>();
        // dp[i] := the length of LIS ending in nums[i]
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            final int num = nums[i];
            if (tails.isEmpty() || num > tails.get(tails.size() - 1))
                tails.add(num);
            else
                tails.set(firstGreaterEqual(tails, num), num);
            dp[i] = tails.size();
        }
        return dp;
    }

    private int firstGreaterEqual(List<Integer> A, int target) {
        final int i = Collections.binarySearch(A, target);
        return i < 0 ? -i - 1 : i;
    }

    private int[] reversed(int[] nums) {
        int[] A = nums.clone();
        int l = 0;
        int r = nums.length - 1;
        while (l < r)
            swap(A, l++, r--);
        return A;
    }

    private void swap(int[] A, int i, int j) {
        final int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}