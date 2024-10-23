/*
# 2641. Cousins in Binary Tree II


Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.

Two nodes of a binary tree are cousins if they have the same depth with different parents.

Return the root of the modified tree.

Note that the depth of a node is the number of edges in the path from the root node to it.

Example 1:
Input: root = [5,4,9,1,10,null,7]
Output: [0,0,0,7,7,null,11]
Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 5 does not have any cousins so its sum is 0.
- Node with value 4 does not have any cousins so its sum is 0.
- Node with value 9 does not have any cousins so its sum is 0.
- Node with value 1 has a cousin with value 7 so its sum is 7.
- Node with value 10 has a cousin with value 7 so its sum is 7.
- Node with value 7 has cousins with values 1 and 10 so its sum is 11.

Example 2:
Input: root = [3,1,2]
Output: [0,0,0]
Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 3 does not have any cousins so its sum is 0.
- Node with value 1 does not have any cousins so its sum is 0.
- Node with value 2 does not have any cousins so its sum is 0.
 

Constraints:

The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 104
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
    public TreeNode replaceValueInTree(TreeNode root) {
        List<Integer> levelSums = new ArrayList<>();
        dfs(root, 0, levelSums);
        return replace(root, 0, new TreeNode(0), levelSums);
    }

    private void dfs(TreeNode root, int level, List<Integer> levelSums) {
        if (root == null)
            return;
        if (levelSums.size() == level)
            levelSums.add(0);
        levelSums.set(level, levelSums.get(level) + root.val);
        dfs(root.left, level + 1, levelSums);
        dfs(root.right, level + 1, levelSums);
    }

    private TreeNode replace(TreeNode root, int level, TreeNode curr, List<Integer> levelSums) {
        final int nextLevel = level + 1;
        final int nextLevelCousinsSum = nextLevel >= levelSums.size()
                ? 0
                : levelSums.get(nextLevel) -
                        (root.left == null ? 0 : root.left.val) -
                        (root.right == null ? 0 : root.right.val);
        if (root.left != null) {
            curr.left = new TreeNode(nextLevelCousinsSum);
            replace(root.left, level + 1, curr.left, levelSums);
        }
        if (root.right != null) {
            curr.right = new TreeNode(nextLevelCousinsSum);
            replace(root.right, level + 1, curr.right, levelSums);
        }
        return curr;
    }
}