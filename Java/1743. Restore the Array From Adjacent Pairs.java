/*
# 1743. Restore the Array From Adjacent Pairs

There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.

You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.

It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.

Return the original array nums. If there are multiple solutions, return any of them.
 

Example 1:
Input: adjacentPairs = [[2,1],[3,4],[3,2]]
Output: [1,2,3,4]
Explanation: This array has all its adjacent pairs in adjacentPairs.
Notice that adjacentPairs[i] may not be in left-to-right order.

Example 2:
Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
Output: [-2,4,1,-3]
Explanation: There can be negative numbers.
Another solution is [-3,1,4,-2], which would also be accepted.

Example 3:
Input: adjacentPairs = [[100000,-100000]]
Output: [100000,-100000]

Constraints:

nums.length == n
adjacentPairs.length == n - 1
adjacentPairs[i].length == 2
2 <= n <= 105
-105 <= nums[i], ui, vi <= 105
There exists some nums that has adjacentPairs as its pairs.
*/





class Solution {
  public int[] restoreArray(int[][] adjacentPairs) {
    int[] ans = new int[adjacentPairs.length + 1];
    int i = 0; // ans's index
    Map<Integer, List<Integer>> numToAdjs = new HashMap<>();

    for (int[] pair : adjacentPairs) {
      numToAdjs.putIfAbsent(pair[0], new ArrayList<>());
      numToAdjs.putIfAbsent(pair[1], new ArrayList<>());
      numToAdjs.get(pair[0]).add(pair[1]);
      numToAdjs.get(pair[1]).add(pair[0]);
    }

    for (Map.Entry<Integer, List<Integer>> entry : numToAdjs.entrySet())
      if (entry.getValue().size() == 1) {
        ans[i++] = entry.getKey();
        ans[i++] = entry.getValue().get(0);
        break;
      }

    while (i < adjacentPairs.length + 1) {
      final int tail = ans[i - 1];
      final int prev = ans[i - 2];
      List<Integer> adjs = numToAdjs.get(tail);
      if (adjs.get(0) == prev)
        ans[i++] = adjs.get(1);
      else
        ans[i++] = adjs.get(0);
    }

    return ans;
  }
}