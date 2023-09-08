/*
# 118. Pascal's Triangle

Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

Example 1:
Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

Example 2:
Input: numRows = 1
Output: [[1]]

Constraints:

1 <= numRows <= 30
*/



class Solution {
  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> ans = new ArrayList<>();

    for (int i = 0; i < numRows; ++i) {
      Integer[] temp = new Integer[i + 1];
      Arrays.fill(temp, 1);
      ans.add(Arrays.asList(temp));
    }

    for (int i = 2; i < numRows; ++i)
      for (int j = 1; j < ans.get(i).size() - 1; ++j)
        ans.get(i).set(j, ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));

    return ans;
  }
}