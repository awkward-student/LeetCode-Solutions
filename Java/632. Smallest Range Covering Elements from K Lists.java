/*
# 632. Smallest Range Covering Elements from K Lists


You have k lists of sorted integers in non-decreasing order. 
Find the smallest range that includes at least one number from each of the k lists.

We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.


Example 1:
Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].

Example 2:
Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
Output: [1,1]
 

Constraints:

nums.length == k
1 <= k <= 3500
1 <= nums[i].length <= 50
-105 <= nums[i][j] <= 105
nums[i] is sorted in non-decreasing order.
*/






class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        record T(int i, int j, int num) {
        } // num := nums[i][j]
        Queue<T> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.num, b.num));
        int mn = Integer.MAX_VALUE;
        int mx = Integer.MIN_VALUE;

        for (int i = 0; i < nums.size(); ++i) {
            final int num = nums.get(i).get(0);
            minHeap.offer(new T(i, 0, num));
            mn = Math.min(mn, num);
            mx = Math.max(mx, num);
        }

        int minRange = mn;
        int maxRange = mx;

        while (minHeap.size() == nums.size()) {
            final int i = minHeap.peek().i;
            final int j = minHeap.poll().j;
            if (j + 1 < nums.get(i).size()) {
                minHeap.offer(new T(i, j + 1, nums.get(i).get(j + 1)));
                mx = Math.max(mx, nums.get(i).get(j + 1));
                mn = minHeap.peek().num;
            }
            if (mx - mn < maxRange - minRange) {
                minRange = mn;
                maxRange = mx;
            }
        }

        return new int[] { minRange, maxRange };
    }
}