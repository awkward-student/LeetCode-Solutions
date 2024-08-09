/*
# 840. Magic Squares In Grid


A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each 
row, column, and both diagonals all have the same sum.

Given a row x col grid of integers, how many 3 x 3 contiguous magic square subgrids are there?

Note: while a magic square can only contain numbers from 1 to 9, grid may contain numbers up to 15.

 
Example 1:
Input: grid = [[4,3,8,4],[9,5,1,9],[2,7,6,2]]
Output: 1
Explanation: 
The following subgrid is a 3 x 3 magic square:
while this one is not:
In total, there is only one magic square inside the given grid.

Example 2:
Input: grid = [[8]]
Output: 0
 

Constraints:

row == grid.length
col == grid[i].length
1 <= row, col <= 10
0 <= grid[i][j] <= 15
*/






class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int ans = 0;

        for (int i = 0; i + 2 < grid.length; ++i)
            for (int j = 0; j + 2 < grid[0].length; ++j)
                if (grid[i][j] % 2 == 0 && grid[i + 1][j + 1] == 5)
                    if (isMagic(grid, i, j))
                        ++ans;

        return ans;
    }

    private boolean isMagic(int[][] grid, int i, int j) {
        String s = new String("");

        for (final int num : new int[] { 0, 1, 2, 5, 8, 7, 6, 3 })
            s += Integer.toString(grid[i + num / 3][j + num % 3]);

        return //
        new String("4381672943816729").contains(s) || //
                new String("9276183492761834").contains(s);
    }
}