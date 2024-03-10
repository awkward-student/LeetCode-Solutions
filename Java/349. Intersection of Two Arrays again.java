/*
# 349. Intersection of Two Arrays

Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.


Example 1:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.
 

Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000
*/





class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        final int N = 1001;
        var set = new int [N];
        for (var i : nums1) set[i] = 1;
        int resCount = 0;
        for (var i : nums2) {
            if (set[i] == 1) {
                set[i] = 2;
                ++resCount;
            }
        }
        var res = new int[resCount];
        var i = 0;
        var nums = nums2.length > nums1.length ? nums1 : nums2;
        for (var v : nums) {
            if (set[v] == 2) {
                res[i++] = v;
                set[v] = 1;
            }
        }
        return res;
    }
    public int[] intersectionOnSet(int[] nums1, int[] nums2) {
       var  s1 = new HashSet<Integer>(nums1.length);
       for (var i : nums1) s1.add(i);
       var s2 = new HashSet<Integer>(nums2.length);
       for (var i : nums2) {
           if (s1.contains(i)) s2.add(i);
       }
       var res = new int[s2.size()];
       int j = 0;
       for (var i : s2){
           System.out.println("v: " + res[j]);
            res[j++] = i;
       }
       return res;
    }
}