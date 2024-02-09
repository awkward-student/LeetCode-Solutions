/*
# 368. Largest Divisible Subset

Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.

 
Example 1:
Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.

Example 2:
Input: nums = [1,2,4,8]
Output: [1,2,4,8]
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 2 * 109
All the integers in nums are unique.
*/






class Solution {
  public List<Integer> largestDivisibleSubset(int[] nums) {
    final int n = nums.length;
    List<Integer> ans = new ArrayList<>();
    // sizeEndsAt[i] := the maximum size ends in nums[i]
    int[] sizeEndsAt = new int[n];
    // prevIndex[i] := the best index s.t.
    // 1. nums[i] % nums[prevIndex[i]] == 0 and
    // 2. can increase the size of the subset
    int[] prevIndex = new int[n];
    int maxSize = 0; // Max size of the subset
    int index = -1;  // Track the best ending index

    Arrays.fill(sizeEndsAt, 1);
    Arrays.fill(prevIndex, -1);
    Arrays.sort(nums);

    // Fix the maximum ending number in the subset.
    for (int i = 0; i < n; ++i) {
      for (int j = i - 1; j >= 0; --j)
        if (nums[i] % nums[j] == 0 && sizeEndsAt[i] < sizeEndsAt[j] + 1) {
          sizeEndsAt[i] = sizeEndsAt[j] + 1;
          prevIndex[i] = j;
        }
      // Find a new subset that has a bigger size.
      if (maxSize < sizeEndsAt[i]) {
        maxSize = sizeEndsAt[i];
        index = i; // Update the best ending index.
      }
    }

    // Loop from the back to the front.
    while (index != -1) {
      ans.add(nums[index]);
      index = prevIndex[index];
    }

    return ans;
  }
}