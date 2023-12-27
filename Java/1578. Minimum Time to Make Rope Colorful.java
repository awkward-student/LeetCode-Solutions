/*
# 1578. Minimum Time to Make Rope Colorful

Alice has n balloons arranged on a rope. You are given a 0-indexed string colors where colors[i] is the color of the ith balloon.

Alice wants the rope to be colorful. She does not want two consecutive balloons to be of the same color, so she asks Bob for help. Bob can remove some balloons from the rope to make it colorful. You are given a 0-indexed integer array neededTime where neededTime[i] is the time (in seconds) that Bob needs to remove the ith balloon from the rope.

Return the minimum time Bob needs to make the rope colorful.

 
Example 1:
Input: colors = "abaac", neededTime = [1,2,3,4,5]
Output: 3
Explanation: In the above image, 'a' is blue, 'b' is red, and 'c' is green.
Bob can remove the blue balloon at index 2. This takes 3 seconds.
There are no longer two consecutive balloons of the same color. Total time = 3.

Example 2:
Input: colors = "abc", neededTime = [1,2,3]
Output: 0
Explanation: The rope is already colorful. Bob does not need to remove any balloons from the rope.

Example 3:
Input: colors = "aabaa", neededTime = [1,2,3,4,1]
Output: 2
Explanation: Bob will remove the ballons at indices 0 and 4. Each ballon takes 1 second to remove.
There are no longer two consecutive balloons of the same color. Total time = 1 + 1 = 2.
 

Constraints:

n == colors.length == neededTime.length
1 <= n <= 105
1 <= neededTime[i] <= 104
colors contains only lowercase English letters.
*/




// solution I:

class Solution {
    public int minCost(String colors, int[] neededTime) {
        int cost = 0;
        if(colors.length() == 1) return cost;

        int lastIndx = 0;
        for(int i=1; i<colors.length(); i++){
            if(colors.charAt(i) == colors.charAt(lastIndx)){
                if(neededTime[i] < neededTime[lastIndx]){
                    cost += neededTime[i];
                }
                else{
                    cost += neededTime[lastIndx];
                    lastIndx = i;
                }
            }
            else lastIndx = i;
        }
        return cost;
    }
}



// solution II:

class Solution {
  public int minCost(String colors, int[] neededTime) {
    int ans = 0;
    int maxNeededTime = neededTime[0];

    for (int i = 1; i < colors.length(); ++i)
      if (colors.charAt(i) == colors.charAt(i - 1)) {
        ans += Math.min(maxNeededTime, neededTime[i]);
        // For each continuous group, Bob needs to remove every balloon except the one with the max
        // `neededTime`. So, he should hold the balloon with the highest `neededTime` in his hand.
        maxNeededTime = Math.max(maxNeededTime, neededTime[i]);
      } else {
        // If the current balloon is different from the previous one, discard the balloon from the
        // previous group and hold the new one in hand.
        maxNeededTime = neededTime[i];
      }

    return ans;
  }
}