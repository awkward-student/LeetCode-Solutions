/*
# 78. Subsets


Given an integer array nums of unique elements, return all possible 
subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

 
Example 1:
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

Example 2:
Input: nums = [0]
Output: [[],[0]]
 

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
*/






class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void dfs(int[] nums, int s, List<Integer> path, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(path));

        for (int i = s; i < nums.length; ++i) {
            path.add(nums[i]);
            dfs(nums, i + 1, path, ans);
            path.remove(path.size() - 1);
        }
    }
}