/*
# 786. K-th Smallest Prime Fraction


You are given a sorted integer array arr containing 1 and prime numbers, where all the integers of arr are unique. 
You are also given an integer k.

For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].

Return the kth smallest fraction considered. Return your answer as an array of integers of size 2, 
where answer[0] == arr[i] and answer[1] == arr[j].


Example 1:
Input: arr = [1,2,3,5], k = 3
Output: [2,5]
Explanation: The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
The third fraction is 2/5.

Example 2:
Input: arr = [1,7], k = 1
Output: [1,7]
 

Constraints:

2 <= arr.length <= 1000
1 <= arr[i] <= 3 * 104
arr[0] == 1
arr[i] is a prime number for i > 0.
All the numbers of arr are unique and sorted in strictly increasing order.
1 <= k <= arr.length * (arr.length - 1) / 2
 

Follow up: Can you solve the problem with better than O(n2) complexity?
*/





class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        final int n = arr.length;
        double l = 0.0;
        double r = 1.0;

        while (l < r) {
            final double m = (l + r) / 2.0;
            int fractionsNoGreaterThanM = 0;
            int p = 0;
            int q = 1;

            // For each index i, find the first index j s.t. arr[i] / arr[j] <= m,
            // so fractionsNoGreaterThanM for index i will be n - j.
            for (int i = 0, j = 1; i < n; ++i) {
                while (j < n && arr[i] > m * arr[j])
                    ++j;
                if (j == n)
                    break;
                fractionsNoGreaterThanM += n - j;
                if (p * arr[j] < q * arr[i]) {
                    p = arr[i];
                    q = arr[j];
                }
            }

            if (fractionsNoGreaterThanM == k)
                return new int[] { p, q };
            if (fractionsNoGreaterThanM > k)
                r = m;
            else
                l = m;
        }

        throw new IllegalArgumentException();
    }
}