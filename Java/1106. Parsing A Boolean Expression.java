/*
# 1106. Parsing A Boolean Expression


A boolean expression is an expression that evaluates to either true or false. It can be in one of the following shapes:

't' that evaluates to true.
'f' that evaluates to false.
'!(subExpr)' that evaluates to the logical NOT of the inner expression subExpr.
'&(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical AND of the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
'|(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical OR of the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
Given a string expression that represents a boolean expression, return the evaluation of that expression.

It is guaranteed that the given expression is valid and follows the given rules.


Example 1:
Input: expression = "&(|(f))"
Output: false
Explanation: 
First, evaluate |(f) --> f. The expression is now "&(f)".
Then, evaluate &(f) --> f. The expression is now "f".
Finally, return false.

Example 2:
Input: expression = "|(f,f,f,t)"
Output: true
Explanation: The evaluation of (false OR false OR false OR true) is true.

Example 3:
Input: expression = "!(&(f,t))"
Output: true
Explanation: 
First, evaluate &(f,t) --> (false AND true) --> false --> f. The expression is now "!(f)".
Then, evaluate !(f) --> NOT false --> true. We return true.
 

Constraints:

1 <= expression.length <= 2 * 104
expression[i] is one following characters: '(', ')', '&', '|', '!', 't', 'f', and ','.
*/






class Solution {
    public boolean parseBoolExpr(String expression) {
        return dfs(expression, 0, expression.length() - 1);
    }

    private boolean dfs(final String expression, int s, int e) {
        if (s == e)
            return expression.charAt(s) == 't';

        List<Boolean> exps = new ArrayList<>();
        int layer = 0;
        int left = 0;
        char op = ' ';

        for (int i = s; i <= e; ++i) {
            char c = expression.charAt(i);
            if (layer == 0 && (c == '!' || c == '&' || c == '|'))
                op = c;
            else if (c == '(' && ++layer == 1)
                left = i + 1;
            else if (c == ')' && --layer == 0)
                exps.add(dfs(expression, left, i - 1));
            else if (c == ',' && layer == 1) {
                exps.add(dfs(expression, left, i - 1));
                left = i + 1;
            }
        }

        if (op == '&') {
            boolean ans = true;
            for (boolean exp : exps)
                ans &= exp;
            return ans;
        }

        if (op == '|') {
            boolean ans = false;
            for (boolean exp : exps)
                ans |= exp;
            return ans;
        }

        return !exps.get(0);
    }
}