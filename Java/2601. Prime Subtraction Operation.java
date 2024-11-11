/*
# 2601. Prime Subtraction Operation


You are given a 0-indexed integer array nums of length n.

You can perform the following operation as many times as you want:

Pick an index i that you haven’t picked before, and pick a prime p strictly less than nums[i], then subtract p from nums[i].
Return true if you can make nums a strictly increasing array using the above operation and false otherwise.

A strictly increasing array is an array whose each element is strictly greater than its preceding element.

 
Example 1:
Input: nums = [4,9,6,10]
Output: true
Explanation: In the first operation: Pick i = 0 and p = 3, and then subtract 3 from nums[0], so that nums becomes [1,9,6,10].
In the second operation: i = 1, p = 7, subtract 7 from nums[1], so nums becomes equal to [1,2,6,10].
After the second operation, nums is sorted in strictly increasing order, so the answer is true.

Example 2:
Input: nums = [6,8,11,12]
Output: true
Explanation: Initially nums is sorted in strictly increasing order, so we don't need to make any operations.

Example 3:
Input: nums = [5,8,3]
Output: false
Explanation: It can be proven that there is no way to perform operations to make nums sorted in strictly increasing order, so the answer is false.
 
Constraints:
1 <= nums.length <= 1000
1 <= nums[i] <= 1000
nums.length == n
*/







class Solution {
    public boolean primeSubOperation(int[] nums) {
        final int kMax = 1000;
        final List<Integer> primes = sieveEratosthenes(kMax);

        int prevNum = 0;
        for (int num : nums) {
            // Make nums[i] the smallest as possible and still > nums[i - 1].
            final int i = firstGreaterEqual(primes, num - prevNum);
            if (i > 0)
                num -= primes.get(i - 1);
            if (num <= prevNum)
                return false;
            prevNum = num;
        }

        return true;
    }

    private List<Integer> sieveEratosthenes(int n) {
        List<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i * i < n; ++i)
            if (isPrime[i])
                for (int j = i * i; j < n; j += i)
                    isPrime[j] = false;
        for (int i = 2; i < n; ++i)
            if (isPrime[i])
                primes.add(i);
        return primes;
    }

    private int firstGreaterEqual(List<Integer> A, int target) {
        final int i = Collections.binarySearch(A, target);
        return i < 0 ? -i - 1 : i;
    }
}