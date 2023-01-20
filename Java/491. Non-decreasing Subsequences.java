/*
491. Non-decreasing Subsequences
Medium
Given an integer array nums, return all the different possible non-decreasing subsequences of the given array with at least two elements. You may return the answer in any order.

Example 1:

Input: nums = [4,6,7,7]
Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]


Example 2:

Input: nums = [4,4,3,2,1]
Output: [[4,4]]
 

Constraints:

1 <= nums.length <= 15
-100 <= nums[i] <= 100
*/



class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        dfs(nums, 0, new LinkedList<>(), ans);
        return ans;
    }
    private void dfs(int[] nums, int s, LinkedList<Integer> path, List<List<Integer>> ans) {
        if (path.size() > 1) ans.add(new LinkedList<>(path));
        Set<Integer> used = new HashSet<>();
        for (int i = s; i < nums.length; ++i) {
            if (used.contains(nums[i])) continue;
            if (path.isEmpty() || nums[i] >= path.getLast()) {
                used.add(nums[i]);
                path.addLast(nums[i]);
                dfs(nums, i + 1, path, ans);
                path.removeLast();
            }
        }
    }
}