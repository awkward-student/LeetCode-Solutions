/*
# 1380. Lucky Numbers in a Matrix
Imagine you're given a grid of numbers, which we call a matrix in mathematics and computer science. This matrix has 'm' rows and 'n' columns, forming an m x n grid. Each cell in this grid contains a distinct number, meaning no two cells have the same number.

Your task is to find all the "lucky numbers" in this matrix. But what makes a number lucky? A number is considered lucky if it satisfies two conditions simultaneously:

It must be the smallest number in its entire row.
It must also be the largest number in its entire column.
Let's break this down with some examples to make it clearer:

Example 1:
Consider this 3x3 matrix:
3  7  8
9  11 13
15 16 17
In this matrix:
15 is the only lucky number.
Why? Because 15 is the smallest number in its row (15, 16, 17) and also the largest number in its column (3, 9, 15).

Example 2:
Let's look at a 3x4 matrix:
1  10 4  2
9  3  8  7
15 16 17 12
Here:
12 is the only lucky number.
It's the smallest in its row (15, 16, 17, 12) and the largest in its column (2, 7, 12).

Example 3:
Consider a smaller 2x2 matrix:
7 8
1 2
In this case:
7 is the lucky number.
It's the smallest in its row (7, 8) and the largest in its column (7, 1).
Important Points to Note:

All numbers in the matrix are distinct (unique).
The numbers are positive integers, ranging from 1 to 10^5.
The matrix can have between 1 to 50 rows and 1 to 50 columns.
It's possible that a matrix might not have any lucky numbers at all.
If lucky numbers exist, you need to return all of them in any order.
*/







class Solution {
    public List<Integer> luckyNumbers (int[][] matrix) {
        int N = matrix.length, M = matrix[0].length;

        List<Integer> rowMin = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int rMin = Integer.MAX_VALUE;
            for (int j = 0; j < M; j++) {
                rMin = Math.min(rMin, matrix[i][j]);
            }
            rowMin.add(rMin);
        }

        List<Integer> colMax = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int cMax = Integer.MIN_VALUE;
            for (int j = 0; j < N; j++) {
                cMax = Math.max(cMax, matrix[j][i]);
            }
            colMax.add(cMax);
        }

        List<Integer> luckyNumbers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == rowMin.get(i) && matrix[i][j] == colMax.get(j)) {
                    luckyNumbers.add(matrix[i][j]);
                }
            }
        }

        return luckyNumbers;
    }
}