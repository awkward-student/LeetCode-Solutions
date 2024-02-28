/*
# 513. Find Bottom Left Tree Value

Given the root of a binary tree, return the leftmost value in the last row of the tree.
 

Example 1:
Input: root = [2,1,3]
Output: 1

Example 2:
Input: root = [1,2,3,4,null,5,6,null,null,7]
Output: 7
 
Constraints:

The number of nodes in the tree is in the range [1, 104].
-231 <= Node.val <= 231 - 1
*/





/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

class Solution {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>(Arrays.asList(root));
        TreeNode node = null;

        while (!q.isEmpty()) {
            node = q.poll();
            if (node.right != null)
                q.offer(node.right);
            if (node.left != null)
                q.offer(node.left);
        }

        return node.val;
    }
}