/*
 * 
 * #662. Maximum Width of Binary Tree
 * 
 * 

Given the root of a binary tree, return the maximum width of the given tree.

The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.

It is guaranteed that the answer will in the range of a 32-bit signed integer.

 

Example 1:


Input: root = [1,3,2,5,3,null,9]
Output: 4
Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
Example 2:


Input: root = [1,3,2,5,null,null,9,6,null,7]
Output: 7
Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
Example 3:


Input: root = [1,3,2,5]
Output: 2
Explanation: The maximum width exists in the second level with length 2 (3,2).
 

Constraints:

The number of nodes in the tree is in the range [1, 3000].
-100 <= Node.val <= 100
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
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int max_width = 0;
        Deque<Pair<TreeNode, Integer>> q = new ArrayDeque<>(); // {node, index}
        q.offer(new Pair<>(root, 1));

        while (!q.isEmpty()) {
            final int offset = q.peekFirst().getValue() * 2;
            max_width = Math.max(max_width, q.peekLast().getValue() - q.peekFirst().getValue() + 1);
            for (int sz = q.size(); sz > 0; --sz) {
                final TreeNode node = q.peekFirst().getKey();
                final int index = q.pollFirst().getValue();
                if (node.left != null) q.offer(new Pair<>(node.left, index * 2 - offset));
                if (node.right != null) q.offer(new Pair<>(node.right, index * 2 + 1 - offset));
            }
        }
        return max_width;
    }
}