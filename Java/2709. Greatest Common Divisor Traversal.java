/*
# 2709. Greatest Common Divisor Traversal

You are given a 0-indexed integer array nums, and you are allowed to traverse between its indices. You can traverse between index i and index j, i != j, if and only if gcd(nums[i], nums[j]) > 1, where gcd is the greatest common divisor.

Your task is to determine if for every pair of indices i and j in nums, where i < j, there exists a sequence of traversals that can take us from i to j.

Return true if it is possible to traverse between all such pairs of indices, or false otherwise.
 

Example 1:
Input: nums = [2,3,6]
Output: true
Explanation: In this example, there are 3 possible pairs of indices: (0, 1), (0, 2), and (1, 2).
To go from index 0 to index 1, we can use the sequence of traversals 0 -> 2 -> 1, where we move from index 0 to index 2 because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1, and then move from index 2 to index 1 because gcd(nums[2], nums[1]) = gcd(6, 3) = 3 > 1.
To go from index 0 to index 2, we can just go directly because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1. Likewise, to go from index 1 to index 2, we can just go directly because gcd(nums[1], nums[2]) = gcd(3, 6) = 3 > 1.

Example 2:
Input: nums = [3,9,5]
Output: false
Explanation: No sequence of traversals can take us from index 0 to index 2 in this example. So, we return false.

Example 3:
Input: nums = [4,3,12,8]
Output: true
Explanation: There are 6 possible pairs of indices to traverse between: (0, 1), (0, 2), (0, 3), (1, 2), (1, 3), and (2, 3). A valid sequence of traversals exists for each pair, so we return true.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
*/





class UnionFind {
  public UnionFind(int n) {
    id = new int[n];
    sz = new int[n];
    for (int i = 0; i < n; ++i)
      id[i] = i;
    for (int i = 0; i < n; ++i)
      sz[i] = 1;
  }

  public void unionBySize(int u, int v) {
    final int i = find(u);
    final int j = find(v);
    if (i == j)
      return;
    if (sz[i] < sz[j]) {
      sz[j] += sz[i];
      id[i] = j;
    } else {
      sz[i] += sz[j];
      id[j] = i;
    }
  }

  public int getSize(int i) {
    return sz[i];
  }

  private int[] id;
  private int[] sz;

  private int find(int u) {
    return id[u] == u ? u : (id[u] = find(id[u]));
  }
}

class Solution {
  public boolean canTraverseAllPairs(int[] nums) {
    final int n = nums.length;
    final int maxNum = Arrays.stream(nums).max().getAsInt();
    final int[] minPrimeFactors = sieveEratosthenes(maxNum + 1);
    Map<Integer, Integer> primeToFirstIndex = new HashMap<>();
    UnionFind uf = new UnionFind(n);

    for (int i = 0; i < n; ++i)
      for (final int primeFactor : getPrimeFactors(nums[i], minPrimeFactors))
        // `primeFactor` already appeared in the previous indices.
        if (primeToFirstIndex.containsKey(primeFactor))
          uf.unionBySize(primeToFirstIndex.get(primeFactor), i);
        else
          primeToFirstIndex.put(primeFactor, i);

    for (int i = 0; i < n; ++i)
      if (uf.getSize(i) == n)
        return true;
    return false;
  }

  // Gets the minimum prime factor of i, where 1 < i <= n.
  private int[] sieveEratosthenes(int n) {
    int[] minPrimeFactors = new int[n + 1];
    for (int i = 2; i <= n; ++i)
      minPrimeFactors[i] = i;
    for (int i = 2; i * i < n; ++i)
      if (minPrimeFactors[i] == i) // `i` is prime.
        for (int j = i * i; j < n; j += i)
          minPrimeFactors[j] = Math.min(minPrimeFactors[j], i);
    return minPrimeFactors;
  }

  private List<Integer> getPrimeFactors(int num, int[] minPrimeFactors) {
    List<Integer> primeFactors = new ArrayList<>();
    while (num > 1) {
      final int divisor = minPrimeFactors[num];
      primeFactors.add(divisor);
      while (num % divisor == 0)
        num /= divisor;
    }
    return primeFactors;
  }
}