/*
# 1726. Tuple with Same Product


Given an array nums of distinct positive integers,
return the number of tuples (a, b, c, d) such that a * b = c * d where a, b, c, and d are elements of nums, and a != b != c != d.

Example 1:
Input: nums = [2,3,4,6]
Output: 8
Explanation: There are 8 valid tuples:
(2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
(3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)

Example 2:
Input: nums = [1,2,4,5,10]
Output: 16
Explanation: There are 16 valid tuples:
(1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
(2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
(2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,5,4)
(4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
 
Constraints:
1 <= nums.length <= 1000
1 <= nums[i] <= 104
All elements in nums are distinct.
*/






class Solution {
    public int tupleSameProduct(int[] nums) {
        int ans = 0;
        Map<Integer, Integer> count = new HashMap<>();

        for (int i = 0; i < nums.length; ++i)
            for (int j = 0; j < i; ++j) {
                final int prod = nums[i] * nums[j];
                ans += count.getOrDefault(prod, 0) * 8;
                count.merge(prod, 1, Integer::sum);
            }

        return ans;
    }
}