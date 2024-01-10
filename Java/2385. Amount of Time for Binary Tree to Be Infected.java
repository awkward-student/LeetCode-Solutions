/*
# 2385. Amount of Time for Binary Tree to Be Infected

You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.

Each minute, a node becomes infected if:

The node is currently uninfected.
The node is adjacent to an infected node.
Return the number of minutes needed for the entire tree to be infected.
 

Example 1:
Input: root = [1,5,3,null,4,10,6,9,2], start = 3
Output: 4
Explanation: The following nodes are infected during:
- Minute 0: Node 3
- Minute 1: Nodes 1, 10 and 6
- Minute 2: Node 5
- Minute 3: Node 4
- Minute 4: Nodes 9 and 2
It takes 4 minutes for the whole tree to be infected so we return 4.

Example 2:
Input: root = [1], start = 1
Output: 0
Explanation: At minute 0, the only node in the tree is infected so we return 0.
 

Constraints:

The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 105
Each node has a unique value.
A node with a value of start exists in the tree.
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
  public int amountOfTime(TreeNode root, int start) {
    int ans = -1;
    Map<Integer, List<Integer>> graph = getGraph(root);
    Queue<Integer> q = new ArrayDeque<>(Arrays.asList(start));
    Set<Integer> seen = new HashSet<>(Arrays.asList(start));

    for (; !q.isEmpty(); ++ans) {
      for (int sz = q.size(); sz > 0; --sz) {
        final int u = q.poll();
        if (!graph.containsKey(u))
          continue;
        for (final int v : graph.get(u)) {
          if (seen.contains(v))
            continue;
          q.offer(v);
          seen.add(v);
        }
      }
    }

    return ans;
  }

  private Map<Integer, List<Integer>> getGraph(TreeNode root) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    // (node, parent)
    Queue<Pair<TreeNode, Integer>> q = new ArrayDeque<>(Arrays.asList(new Pair<>(root, -1)));

    while (!q.isEmpty()) {
      Pair<TreeNode, Integer> pair = q.poll();
      TreeNode node = pair.getKey();
      final int parent = pair.getValue();
      if (parent != -1) {
        graph.putIfAbsent(parent, new ArrayList<>());
        graph.putIfAbsent(node.val, new ArrayList<>());
        graph.get(parent).add(node.val);
        graph.get(node.val).add(parent);
      }
      if (node.left != null)
        q.add(new Pair<>(node.left, node.val));
      if (node.right != null)
        q.add(new Pair<>(node.right, node.val));
    }

    return graph;
  }
}