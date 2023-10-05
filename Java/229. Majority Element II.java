/*
# 229. Majority Element II

Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

 
Example 1:
Input: nums = [3,2,3]
Output: [3]

Example 2:
Input: nums = [1]
Output: [1]

Example 3:
Input: nums = [1,2]
Output: [1,2]
 

Constraints:

1 <= nums.length <= 5 * 104
-109 <= nums[i] <= 109
 

Follow up: Could you solve the problem in linear time and in O(1) space?
*/




class Solution {
    public List<Integer> majorityElement(int[] nums) {
        Arrays.sort(nums);
        int count = 1;
        double fraction = nums.length/3;
        Set<Integer> set = new HashSet<Integer>();
        if(nums.length<3){
            for(int x: nums) set.add(x);
        }
        for(int i=1; i<nums.length; i++){
            if(nums[i] == nums[i-1]){
                ++count;
                if(count>fraction) set.add(nums[i]);
            }
            else {
                count = 1;
                if(count>fraction) set.add(nums[i]);
            }    
        }
        return new ArrayList<Integer>(set);
    }
}


// another approach

class Solution {
  public List<Integer> majorityElement(int[] nums) {
    List<Integer> ans = new ArrayList<>();
    int candidate1 = 0;
    int candidate2 = 1;  // Any number different from candidate1
    int countSoFar1 = 0; // # of candidate1 so far
    int countSoFar2 = 0; // # of candidate2 so far

    for (final int num : nums)
      if (num == candidate1) {
        ++countSoFar1;
      } else if (num == candidate2) {
        ++countSoFar2;
      } else if (countSoFar1 == 0) { // Assign new candidate
        candidate1 = num;
        ++countSoFar1;
      } else if (countSoFar2 == 0) { // Assign new candidate
        candidate2 = num;
        ++countSoFar2;
      } else { // Meet a new number, so pair out previous counts
        --countSoFar1;
        --countSoFar2;
      }

    int count1 = 0;
    int count2 = 0;

    for (final int num : nums)
      if (num == candidate1)
        ++count1;
      else if (num == candidate2)
        ++count2;

    if (count1 > nums.length / 3)
      ans.add(candidate1);
    if (count2 > nums.length / 3)
      ans.add(candidate2);
    return ans;
  }
}