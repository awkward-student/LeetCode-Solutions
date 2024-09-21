/*
# 386. Lexicographical Numbers


Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.

You must write an algorithm that runs in O(n) time and uses O(1) extra space. 

Example 1:
Input: n = 13
Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]

Example 2:
Input: n = 2
Output: [1,2]
 
Constraints:
1 <= n <= 5 * 104
*/



// o(2n) time & o(n) extra space

class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<String> s = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        for(int i=1; i<=n; i++)
            s.add(String.valueOf(i));
        Collections.sort(s);
        for(String x: s){
            ans.add(Integer.parseInt(x));
        }
        return ans;
    }
}

// o(n) time & o(1) extra space

class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        int curr = 1;

        while (ans.size() < n) {
            ans.add(curr);
            if (curr * 10 <= n) {
                curr *= 10;
            } else {
                while (curr % 10 == 9 || curr == n)
                    curr /= 10;
                ++curr;
            }
        }

        return ans;
    }
}