/*
# 930. Binary Subarrays With Sum

Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.

A subarray is a contiguous part of the array.
 

Example 1:
Input: nums = [1,0,1,0,1], goal = 2
Output: 4
Explanation: The 4 subarrays are bolded and underlined below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]

Example 2:
Input: nums = [0,0,0,0,0], goal = 0
Output: 15
 

Constraints:

1 <= nums.length <= 3 * 104
nums[i] is either 0 or 1.
0 <= goal <= nums.length
*/





class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int ans = 0;
        int prefix = 0;
        // {prefix: number of occurrence}
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1);

        for (final int num : nums) {
            prefix += num;
            final int key = prefix - goal;
            if (count.containsKey(key))
                ans += count.get(key);
            count.merge(prefix, 1, Integer::sum);
        }

        return ans;
    }
}