/*
# 2699. Modify Graph Edge Weights


You are given an undirected weighted connected graph containing n nodes labeled from 0 to n - 1, and an integer array edges where edges[i] = [ai, bi, wi] indicates that there is an edge between nodes ai and bi with weight wi.

Some edges have a weight of -1 (wi = -1), while others have a positive weight (wi > 0).

Your task is to modify all edges with a weight of -1 by assigning them positive integer values in the range [1, 2 * 109] so that the shortest distance between the nodes source and destination becomes equal to an integer target. If there are multiple modifications that make the shortest distance between source and destination equal to target, any of them will be considered correct.

Return an array containing all edges (even unmodified ones) in any order if it is possible to make the shortest distance from source to destination equal to target, or an empty array if it's impossible.

Note: You are not allowed to modify the weights of edges with initial positive weights.
 

Example 1:
Input: n = 5, edges = [[4,1,-1],[2,0,-1],[0,3,-1],[4,3,-1]], source = 0, destination = 1, target = 5
Output: [[4,1,1],[2,0,1],[0,3,3],[4,3,1]]
Explanation: The graph above shows a possible modification to the edges, making the distance from 0 to 1 equal to 5.

Example 2:
Input: n = 3, edges = [[0,1,-1],[0,2,5]], source = 0, destination = 2, target = 6
Output: []
Explanation: The graph above contains the initial edges. It is not possible to make the distance from 0 to 2 equal to 6 by modifying the edge with weight -1. So, an empty array is returned.

Example 3:
Input: n = 4, edges = [[1,0,4],[1,2,3],[2,3,5],[0,3,-1]], source = 0, destination = 2, target = 6
Output: [[1,0,4],[1,2,3],[2,3,5],[0,3,1]]
Explanation: The graph above shows a modified graph having the shortest distance from 0 to 2 as 6.
 

Constraints:

1 <= n <= 100
1 <= edges.length <= n * (n - 1) / 2
edges[i].length == 3
0 <= ai, bi < n
wi = -1 or 1 <= wi <= 107
ai != bi
0 <= source, destination < n
source != destination
1 <= target <= 109
The graph is connected, and there are no self-loops or repeated edges
*/






class Solution {
    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        final int kMax = 2_000_000_000;
        List<Pair<Integer, Integer>>[] graph = new List[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] edge : edges) {
            final int u = edge[0];
            final int v = edge[1];
            final int w = edge[2];
            if (w == -1)
                continue;
            graph[u].add(new Pair<>(v, w));
            graph[v].add(new Pair<>(u, w));
        }

        int distToDestination = dijkstra(graph, source, destination);
        if (distToDestination < target)
            return new int[0][];
        if (distToDestination == target) {
            // Change the weights of negative edges to an impossible value.
            for (int[] edge : edges)
                if (edge[2] == -1)
                    edge[2] = kMax;
            return edges;
        }

        for (int i = 0; i < edges.length; ++i) {
            final int u = edges[i][0];
            final int v = edges[i][1];
            final int w = edges[i][2];
            if (w != -1)
                continue;
            edges[i][2] = 1;
            graph[u].add(new Pair<>(v, 1));
            graph[v].add(new Pair<>(u, 1));
            distToDestination = dijkstra(graph, source, destination);
            if (distToDestination <= target) {
                edges[i][2] += target - distToDestination;
                // Change the weights of negative edges to an impossible value.
                for (int j = i + 1; j < edges.length; ++j)
                    if (edges[j][2] == -1)
                        edges[j][2] = kMax;
                return edges;
            }
        }

        return new int[0][];
    }

    private int dijkstra(List<Pair<Integer, Integer>>[] graph, int src, int dst) {
        int[] dist = new int[graph.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        // (d, u)
        Queue<Pair<Integer, Integer>> minHeap = new PriorityQueue<>((a, b) -> a.getKey().compareTo(b.getKey()));

        dist[src] = 0;
        minHeap.offer(new Pair<>(dist[src], src));

        while (!minHeap.isEmpty()) {
            final int d = minHeap.peek().getKey();
            final int u = minHeap.poll().getValue();
            if (d > dist[u])
                continue;
            for (Pair<Integer, Integer> pair : graph[u]) {
                final int v = pair.getKey();
                final int w = pair.getValue();
                if (d + w < dist[v]) {
                    dist[v] = d + w;
                    minHeap.offer(new Pair<>(dist[v], v));
                }
            }
        }

        return dist[dst];
    }
}