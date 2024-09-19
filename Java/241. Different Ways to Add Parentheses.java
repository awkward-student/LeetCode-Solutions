/*
# 241. Different Ways to Add Parentheses


Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. You may return the answer in any order.

The test cases are generated such that the output values fit in a 32-bit integer and the number of different results does not exceed 104.
 

Example 1:
Input: expression = "2-1-1"
Output: [0,2]
Explanation:
((2-1)-1) = 0 
(2-(1-1)) = 2

Example 2:
Input: expression = "2*3-4*5"
Output: [-34,-14,-10,-10,10]
Explanation:
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10
 

Constraints:

1 <= expression.length <= 20
expression consists of digits and the operator '+', '-', and '*'.
All the integer values in the input expression are in the range [0, 99].
*/






class Solution {
    public List<Integer> diffWaysToCompute(String expression) {
        return ways(expression, new HashMap<>());
    }

    private List<Integer> ways(final String s, Map<String, List<Integer>> mem) {
        if (mem.containsKey(s))
            return mem.get(s);

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < s.length(); ++i)
            if (!Character.isDigit(s.charAt(i)))
                for (final int a : ways(s.substring(0, i), mem))
                    for (final int b : ways(s.substring(i + 1), mem))
                        if (s.charAt(i) == '+')
                            ans.add(a + b);
                        else if (s.charAt(i) == '-')
                            ans.add(a - b);
                        else
                            ans.add(a * b);

        if (ans.isEmpty()) { // Single number
            mem.put(s, Arrays.asList(Integer.parseInt(s)));
            return mem.get(s);
        }
        mem.put(s, ans);
        return ans;
    }
}