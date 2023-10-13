/*
# 1095. Find in Mountain Array


You may recall that an array arr is a mountain array if and only if:

arr.length >= 3
There exists some i with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such an index does not exist, return -1.

You cannot access the mountain array directly. You may only access the array using a MountainArray interface:

MountainArray.get(k) returns the element of the array at index k (0-indexed).
MountainArray.length() returns the length of the array.
Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.

(This problem is an interactive problem.)


Example 1:
Input: array = [1,2,3,4,5,3,1], target = 3
Output: 2
Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.

Example 2:
Input: array = [0,1,2,4,2,1], target = 3
Output: -1
Explanation: 3 does not exist in the array, so we return -1.
 

Constraints:

3 <= mountain_arr.length() <= 104
0 <= target <= 109
0 <= mountain_arr.get(index) <= 109
*/




/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *   public int get(int index) {}
 *   public int length() {}
 * }
 */

class Solution {
  public int findInMountainArray(int target, MountainArray mountainArr) {
    final int n = mountainArr.length();
    final int peakIndex = peakIndexInMountainArray(mountainArr, 0, n - 1);

    final int leftIndex = searchLeft(mountainArr, target, 0, peakIndex);
    if (mountainArr.get(leftIndex) == target)
      return leftIndex;

    final int rightIndex = searchRight(mountainArr, target, peakIndex + 1, n - 1);
    if (mountainArr.get(rightIndex) == target)
      return rightIndex;

    return -1;
  }

  // 852. Peak Index in a Mountain Array
  private int peakIndexInMountainArray(MountainArray A, int l, int r) {
    while (l < r) {
      final int m = (l + r) / 2;
      if (A.get(m) < A.get(m + 1))
        l = m + 1;
      else
        r = m;
    }
    return l;
  }

  private int searchLeft(MountainArray A, int target, int l, int r) {
    while (l < r) {
      final int m = (l + r) / 2;
      if (A.get(m) < target)
        l = m + 1;
      else
        r = m;
    }
    return l;
  }

  private int searchRight(MountainArray A, int target, int l, int r) {
    while (l < r) {
      final int m = (l + r) / 2;
      if (A.get(m) > target)
        l = m + 1;
      else
        r = m;
    }
    return l;
  }
}