/*
# 1110. Delete Nodes And Return Forest


Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest. You may return the result in any order.
 

Example 1:
Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]

Example 2:
Input: root = [1,2,4,null,3], to_delete = [3]
Output: [[1,2,4]]
 

Constraints:

The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.
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
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> ans = new ArrayList<>();
        Set<Integer> toDeleteSet = Arrays.stream(to_delete).boxed().collect(Collectors.toSet());
        dfs(root, toDeleteSet, true, ans);
        return ans;
    }

    private TreeNode dfs(TreeNode root, Set<Integer> toDeleteSet, boolean isRoot,
            List<TreeNode> ans) {
        if (root == null)
            return null;

        final boolean deleted = toDeleteSet.contains(root.val);
        if (isRoot && !deleted)
            ans.add(root);

        // If root is deleted, both children have the possibility to be a new root
        root.left = dfs(root.left, toDeleteSet, deleted, ans);
        root.right = dfs(root.right, toDeleteSet, deleted, ans);
        return deleted ? null : root;
    }
}