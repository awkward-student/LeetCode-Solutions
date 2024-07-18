/*
# 1530. Number of Good Leaf Nodes Pairs


You are given the root of a binary tree and an integer distance. 

A pair of two different leaf nodes of a binary tree is said to be good 

if the length of the shortest path between them is less than or equal to distance.

Return the number of good leaf node pairs in the tree.
 

Example 1:
Input: root = [1,2,3,null,4], distance = 3
Output: 1
Explanation: The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This is the only good pair.

Example 2:
Input: root = [1,2,3,4,5,6,7], distance = 3
Output: 2
Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2. The pair [4,6] is not good because the length of ther shortest path between them is 4.

Example 3:
Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
Output: 1
Explanation: The only good pair is [2,5].
 

Constraints:

The number of nodes in the tree is in the range [1, 210].
1 <= Node.val <= 100
1 <= distance <= 10
*/






/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
 
class Solution {
    public int countPairs(TreeNode root, int distance) {
        dfs(root, distance);

        return ans;
    }

    private int ans = 0;

    private int[] dfs(TreeNode root, int distance) {
        int[] d = new int[distance + 1]; // {distance: the number of leaf nodes}
        if (root == null)
            return d;
        if (root.left == null && root.right == null) {
            d[0] = 1;
            return d;
        }

        int[] dl = dfs(root.left, distance);
        int[] dr = dfs(root.right, distance);

        for (int i = 0; i < distance; ++i)
            for (int j = 0; j < distance; ++j)
                if (i + j + 2 <= distance)
                    ans += dl[i] * dr[j];

        for (int i = 0; i < distance; ++i)
            d[i + 1] = dl[i] + dr[i];

        return d;
    }
}