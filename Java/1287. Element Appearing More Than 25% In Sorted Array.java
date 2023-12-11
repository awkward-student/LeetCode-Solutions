/*
# 1287. Element Appearing More Than 25% In Sorted Array

Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time, return that integer.


Example 1:
Input: arr = [1,2,2,6,6,6,6,7,10]
Output: 6

Example 2:
Input: arr = [1,1]
Output: 1
 

Constraints:

1 <= arr.length <= 104
0 <= arr[i] <= 105
*/





class Solution {
    public int findSpecialInteger(int[] arr) {
        if(arr.length == 1) return arr[0];
        int count = 0;
        double rex = arr.length / 4;
        for(int i=1; i<arr.length; i++){
            if(arr[i] == arr[i-1]){
                count ++;
                if(count >= rex) {
                    return arr[i];
                }
            }
            else{
                count = 0;
            }
        }
        return arr[arr.length-1];
    }
}