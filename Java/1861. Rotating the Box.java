/*
# 1861. Rotating the Box



You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:

A stone '#'
A stationary obstacle '*'
Empty '.'

The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.

It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.

Return an n x m matrix representing the box after the rotation described above.
 

Example 1:

Input: box = [["#",".","#"]]
Output: [["."],
         ["#"],
         ["#"]]



Example 2:

Input: box = [["#",".","*","."],
              ["#","#","*","."]]
Output: [["#","."],
         ["#","#"],
         ["*","*"],
         [".","."]]



Example 3:

Input: box = [["#","#","*",".","*","."],
              ["#","#","#","*",".","."],
              ["#","#","#",".","#","."]]
Output: [[".","#","#"],
         [".","#","#"],
         ["#","#","*"],
         ["#","*","."],
         ["#",".","*"],
         ["#",".","."]]
 

Constraints:

m == box.length
n == box[i].length
1 <= m, n <= 500
box[i][j] is either '#', '*', or '.'.
*/






class Solution {
  public char[][] rotateTheBox(char[][] box) {
    final int m = box.length;
    final int n = box[0].length;
    char[][] ans = new char[n][m];
    Arrays.stream(ans).forEach(A -> Arrays.fill(A, '.'));

    for (int i = 0; i < m; ++i)
      for (int j = n - 1, k = n - 1; j >= 0; --j)
        if (box[i][j] != '.') {
          if (box[i][j] == '*')
            k = j;
          ans[k--][m - i - 1] = box[i][j];
        }

    return ans;
  }
}