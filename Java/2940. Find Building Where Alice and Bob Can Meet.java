/*
# 2940. Find Building Where Alice and Bob Can Meet



You are given a 0-indexed array heights of positive integers, where heights[i] represents the height of the ith building.

If a person is in building i, they can move to any other building j if and only if i < j and heights[i] < heights[j].

You are also given another array queries where queries[i] = [ai, bi]. On the ith query, 
Alice is in building ai while Bob is in building bi.

Return an array ans where ans[i] is the index of the leftmost building where Alice and Bob can meet on the ith query.
If Alice and Bob cannot move to a common building on query i, set ans[i] to -1.
 

Example 1:

Input: heights = [6,4,8,5,2,7], queries = [[0,1],[0,3],[2,4],[3,4],[2,2]]
Output: [2,5,-1,5,2]
Explanation: In the first query, Alice and Bob can move to building 2 since heights[0] < heights[2] and heights[1] < heights[2]. 
In the second query, Alice and Bob can move to building 5 since heights[0] < heights[5] and heights[3] < heights[5]. 
In the third query, Alice cannot meet Bob since Alice cannot move to any other building.
In the fourth query, Alice and Bob can move to building 5 since heights[3] < heights[5] and heights[4] < heights[5].
In the fifth query, Alice and Bob are already in the same building.  
For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.


Example 2:

Input: heights = [5,3,8,2,6,1,4,6], queries = [[0,7],[3,5],[5,2],[3,0],[1,6]]
Output: [7,6,-1,4,6]
Explanation: In the first query, Alice can directly move to Bob's building since heights[0] < heights[7].
In the second query, Alice and Bob can move to building 6 since heights[3] < heights[6] and heights[5] < heights[6].
In the third query, Alice cannot meet Bob since Bob cannot move to any other building.
In the fourth query, Alice and Bob can move to building 4 since heights[3] < heights[4] and heights[0] < heights[4].
In the fifth query, Alice can directly move to Bob's building since heights[1] < heights[6].
For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.

 

Constraints:

1 <= heights.length <= 5 * 104
1 <= heights[i] <= 109
1 <= queries.length <= 5 * 104
queries[i] = [ai, bi]
0 <= ai, bi <= heights.length - 1
*/




class Solution {
    // Similar to 2736. Maximum Sum Queries
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        IndexedQuery[] indexedQueries = getIndexedQueries(queries);
        int[] ans = new int[queries.length];
        Arrays.fill(ans, -1);
        // Store indices (heightsIndex) of heights with heights[heightsIndex] in
        // descending order.
        List<Integer> stack = new ArrayList<>();

        // Iterate through queries and heights simultaneously.
        int heightsIndex = heights.length - 1;
        for (IndexedQuery indexedQuery : indexedQueries) {
            final int queryIndex = indexedQuery.queryIndex;
            final int a = indexedQuery.a;
            final int b = indexedQuery.b;
            if (a == b || heights[a] < heights[b]) {
                // 1. Alice and Bob are already in the same index (a == b) or
                // 2. Alice can jump from a -> b (heights[a] < heights[b]).
                ans[queryIndex] = b;
            } else {
                // Now, a < b and heights[a] >= heights[b].
                // Gradually add heights with an index > b to the monotonic stack.
                while (heightsIndex > b) {
                    // heights[heightsIndex] is a better candidate, given that
                    // heightsIndex is smaller than the indices in the stack and
                    // heights[heightsIndex] is larger or equal to the heights mapped in
                    // the stack.
                    while (!stack.isEmpty() && heights[stack.get(stack.size() - 1)] <= heights[heightsIndex])
                        stack.remove(stack.size() - 1);
                    stack.add(heightsIndex--);
                }
                // Binary search to find the smallest index j such that j > b and
                // heights[j] > heights[a], thereby ensuring heights[t] > heights[b].
                final int j = lastGreater(stack, a, heights);
                if (j != -1)
                    ans[queryIndex] = stack.get(j);
            }
        }

        return ans;
    }

    private record IndexedQuery(int queryIndex, int a, int b) {
    };

    // Returns the last index i in A s.t. heights[A.get(i)] is > heights[target].
    private int lastGreater(List<Integer> A, int target, int[] heights) {
        int l = -1;
        int r = A.size() - 1;
        while (l < r) {
            final int m = (l + r + 1) / 2;
            if (heights[A.get(m)] > heights[target])
                l = m;
            else
                r = m - 1;
        }
        return l;
    }

    private IndexedQuery[] getIndexedQueries(int[][] queries) {
        IndexedQuery[] indexedQueries = new IndexedQuery[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            // Make sure that a <= b.
            final int a = Math.min(queries[i][0], queries[i][1]);
            final int b = Math.max(queries[i][0], queries[i][1]);
            indexedQueries[i] = new IndexedQuery(i, a, b);
        }
        Arrays.sort(indexedQueries, (a, b) -> Integer.compare(b.b, a.b));
        return indexedQueries;
    }
}