/*
# 3133. Minimum Array End



You are given two integers n and x. You have to construct an array of positive integers nums of size n where for every 0 <= i < n - 1, 

nums[i + 1] is greater than nums[i], and the result of the bitwise AND operation between all elements of nums is x.

Return the minimum possible value of nums[n - 1].


Example 1:

Input: n = 3, x = 4

Output: 6

Explanation:

nums can be [4,5,6] and its last element is 6.


Example 2:

Input: n = 2, x = 7

Output: 15

Explanation:

nums can be [7,15] and its last element is 15.

 

Constraints:

1 <= n, x <= 108
*/







class Solution {
    public long minEnd(int n, int x) {
        // Set x's 0s with (n - 1)'s LSb-to-MSb bits, preserving x's 1s. This
        // operation increase x for (n - 1) iterations while preserving x's 1s.
        final int kMaxBit = bitLength(n) + bitLength(x);
        final long k = n - 1;
        long ans = x;
        int kBinaryIndex = 0;

        for (int i = 0; i < kMaxBit; ++i) {
            if ((ans >> i & 1) == 0) {
                // Set x's 0 with k's bit if the running bit of k is 1.
                if ((k >> kBinaryIndex & 1) == 1)
                    ans |= 1L << i;
                ++kBinaryIndex;
            }
        }

        return ans;
    }

    private int bitLength(int n) {
        return 32 - Integer.numberOfLeadingZeros(n);
    }
}