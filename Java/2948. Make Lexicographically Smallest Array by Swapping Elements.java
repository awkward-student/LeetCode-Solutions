/*
# 2948. Make Lexicographically Smallest Array by Swapping Elements


You are given a 0-indexed array of positive integers nums and a positive integer limit.

In one operation, you can choose any two indices i and j and swap nums[i] and nums[j] if |nums[i] - nums[j]| <= limit.

Return the lexicographically smallest array that can be obtained by performing the operation any number of times.

An array a is lexicographically smaller than an array b if in the first position where a and b differ, array a has an element that is less than the corresponding element in b. For example, the array [2,10,3] is lexicographically smaller than the array [10,2,3] because they differ at index 0 and 2 < 10.

Example 1:
Input: nums = [1,5,3,9,8], limit = 2
Output: [1,3,5,8,9]
Explanation: Apply the operation 2 times:
- Swap nums[1] with nums[2]. The array becomes [1,3,5,9,8]
- Swap nums[3] with nums[4]. The array becomes [1,3,5,8,9]
We cannot obtain a lexicographically smaller array by applying any more operations.
Note that it may be possible to get the same result by doing different operations.

Example 2:
Input: nums = [1,7,6,18,2,1], limit = 3
Output: [1,6,7,18,1,2]
Explanation: Apply the operation 3 times:
- Swap nums[1] with nums[2]. The array becomes [1,6,7,18,2,1]
- Swap nums[0] with nums[4]. The array becomes [2,6,7,18,1,1]
- Swap nums[0] with nums[5]. The array becomes [1,6,7,18,1,2]
We cannot obtain a lexicographically smaller array by applying any more operations.

Example 3:
Input: nums = [1,7,28,19,10], limit = 3
Output: [1,7,28,19,10]
Explanation: [1,7,28,19,10] is the lexicographically smallest array we can obtain because we cannot apply the operation on any two indices.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
1 <= limit <= 109
*/






class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int[] ans = new int[nums.length];
        List<List<Pair<Integer, Integer>>> numAndIndexesGroups = new ArrayList<>();

        for (Pair<Integer, Integer> numAndIndex : getNumAndIndexes(nums))
            if (numAndIndexesGroups.isEmpty() ||
                    numAndIndex.getKey() -
                            numAndIndexesGroups.get(numAndIndexesGroups.size() - 1)
                                    .get(numAndIndexesGroups.get(numAndIndexesGroups.size() - 1).size() - 1)
                                    .getKey() > limit) {
                // Start a new group.
                numAndIndexesGroups.add(new ArrayList<>(List.of(numAndIndex)));
            } else {
                // Append to the existing group.
                numAndIndexesGroups.get(numAndIndexesGroups.size() - 1).add(numAndIndex);
            }

        for (List<Pair<Integer, Integer>> numAndIndexesGroup : numAndIndexesGroups) {
            List<Integer> sortedNums = new ArrayList<>();
            List<Integer> sortedIndices = new ArrayList<>();
            for (Pair<Integer, Integer> pair : numAndIndexesGroup) {
                sortedNums.add(pair.getKey());
                sortedIndices.add(pair.getValue());
            }
            sortedIndices.sort(null);
            for (int i = 0; i < sortedNums.size(); ++i) {
                ans[sortedIndices.get(i)] = sortedNums.get(i);
            }
        }

        return ans;
    }

    private Pair<Integer, Integer>[] getNumAndIndexes(int[] nums) {
        Pair<Integer, Integer>[] numAndIndexes = new Pair[nums.length];
        for (int i = 0; i < nums.length; ++i)
            numAndIndexes[i] = new Pair<>(nums[i], i);
        Arrays.sort(numAndIndexes, (a, b) -> a.getKey().compareTo(b.getKey()));
        return numAndIndexes;
    }
}