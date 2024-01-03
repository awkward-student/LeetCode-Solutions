/*
# 2610. Convert an Array Into a 2D Array With Conditions

You are given an integer array nums. You need to create a 2D array from nums satisfying the following conditions:

The 2D array should contain only the elements of the array nums.
Each row in the 2D array contains distinct integers.
The number of rows in the 2D array should be minimal.
Return the resulting array. If there are multiple answers, return any of them.

Note that the 2D array can have a different number of elements on each row.


Example 1:
Input: nums = [1,3,4,1,2,3,1]
Output: [[1,3,4,2],[1,3],[1]]
Explanation: We can create a 2D array that contains the following rows:
- 1,3,4,2
- 1,3
- 1
All elements of nums were used, and each row of the 2D array contains distinct integers, so it is a valid answer.
It can be shown that we cannot have less than 3 rows in a valid array.

Example 2:
Input: nums = [1,2,3,4]
Output: [[4,3,2,1]]
Explanation: All elements of the array are distinct, so we can keep all of them in the first row of the 2D array.
 

Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= nums.length
*/




// solution I

class Solution {

    List<List<Integer>> matrix = new ArrayList<>();

    public List<List<Integer>> findMatrix(int[] nums) {
        
        for(int x: nums){
            int i = 0;
            assignInteger(x, i);
        }

        return matrix;
    }

    public void assignInteger(int x, int indx){

        while(indx < matrix.size()){
            if(! matrix.get(indx).contains(x)){
                matrix.get(indx).add(x);
                return;
            }
            ++ indx;
        }
        
        List<Integer> zx = new ArrayList<>();
        zx.add(x);
        matrix.add(zx);
    }
}




// solution II

class Solution {
  public List<List<Integer>> findMatrix(int[] nums) {
    // The number of rows we need equals the maximum frequency.
    List<List<Integer>> ans = new ArrayList<>();
    int[] count = new int[nums.length + 1];

    for (final int num : nums) {
      // Construct `ans` on demand.
      if (++count[num] > ans.size())
        ans.add(new ArrayList<>());
      ans.get(count[num] - 1).add(num);
    }

    return ans;
  }
}