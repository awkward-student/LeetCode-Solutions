/*
# 1122. Relative Sort Array


Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.

Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2. 
Elements that do not appear in arr2 should be placed at the end of arr1 in ascending order.

 
Example 1:
Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
Output: [2,2,2,1,4,3,3,9,6,7,19]

Example 2:
Input: arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
Output: [22,28,8,6,17,44]
 

Constraints:

1 <= arr1.length, arr2.length <= 1000
0 <= arr1[i], arr2[i] <= 1000
All the elements of arr2 are distinct.
Each arr2[i] is in arr1.
*/





class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] map = new int[1001];
        for(int x: arr1) map[x]++;
        int curIndx = 0;
        for(int i=0; i<arr2.length; i++){
            for(int z=1; z<=map[arr2[i]]; z++) {
                arr1[curIndx] = arr2[i];
                curIndx++;
            }
            map[arr2[i]] = 0;
        }
        for(int i=0; i<map.length; i++){
            if(map[i] == 0) continue;
            for(int z=1; z<=map[i] && curIndx<arr1.length; z++) {
                arr1[curIndx] = i;
                curIndx++;
            }
        }
        return arr1;
    }
    // [0, 1, 3, 2, 1, 0, 1, 1, 0, 1, 0000..., 1];
}