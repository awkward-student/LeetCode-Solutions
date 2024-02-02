/*
# 1291. Sequential Digits

An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

 
Example 1:
Input: low = 100, high = 300
Output: [123,234]
Example 2:

Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]
 

Constraints:

10 <= low <= high <= 10^9
*/





class Solution {
  public List<Integer> sequentialDigits(int low, int high) {
    List<Integer> ans = new ArrayList<>();
    Queue<Integer> q = new ArrayDeque<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

    while (!q.isEmpty()) {
      final int num = q.poll();
      if (num > high)
        return ans;
      if (low <= num && num <= high)
        ans.add(num);
      final int lastDigit = num % 10;
      if (lastDigit < 9)
        q.offer(num * 10 + lastDigit + 1);
    }

    return ans;
  }
}