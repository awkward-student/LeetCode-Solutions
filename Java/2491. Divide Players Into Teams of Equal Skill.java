/*
# 2491. Divide Players Into Teams of Equal Skill


You are given a positive integer array skill of even length n where skill[i] denotes the skill of the ith player. Divide the players into n / 2 teams of size 2 such that the total skill of each team is equal.

The chemistry of a team is equal to the product of the skills of the players on that team.

Return the sum of the chemistry of all the teams, or return -1 if there is no way to divide the players into teams such that the total skill of each team is equal.

 
Example 1:
Input: skill = [3,2,5,1,3,4]
Output: 22
Explanation: 
Divide the players into the following teams: (1, 5), (2, 4), (3, 3), where each team has a total skill of 6.
The sum of the chemistry of all the teams is: 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8 + 9 = 22.

Example 2:
Input: skill = [3,4]
Output: 12
Explanation: 
The two players form a team with a total skill of 7.
The chemistry of the team is 3 * 4 = 12.

Example 3:
Input: skill = [1,1,2,3]
Output: -1
Explanation: 
There is no way to divide the players into teams such that the total skill of each team is equal.
 

Constraints:

2 <= skill.length <= 105
skill.length is even.
1 <= skill[i] <= 1000
*/






class Solution {
    public long dividePlayers(int[] skill) {
        final int n = skill.length;
        final int teamSkill = Arrays.stream(skill).sum() / (n / 2);
        long ans = 0;
        Map<Integer, Integer> count = new HashMap<>();

        for (final int s : skill)
            count.merge(s, 1, Integer::sum);

        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            final int s = entry.getKey();
            final int freq = entry.getValue();
            final int requiredSkill = teamSkill - s;
            if (count.getOrDefault(requiredSkill, 0) != freq)
                return -1;
            ans += (long) s * requiredSkill * freq;
        }

        return ans / 2;
    }
}