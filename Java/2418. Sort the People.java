/*
# 2418. Sort the People


You are given an array of strings names, and an array heights that consists of distinct positive integers. Both arrays are of length n.

For each index i, names[i] and heights[i] denote the name and height of the ith person.

Return names sorted in descending order by the people's heights.
 

Example 1:
Input: names = ["Mary","John","Emma"], heights = [180,165,170]
Output: ["Mary","Emma","John"]
Explanation: Mary is the tallest, followed by Emma and John.

Example 2:
Input: names = ["Alice","Bob","Bob"], heights = [155,185,150]
Output: ["Bob","Alice","Bob"]
Explanation: The first Bob is the tallest, followed by Alice and the second Bob.
 
Constraints:

n == names.length == heights.length
1 <= n <= 103
1 <= names[i].length <= 20
1 <= heights[i] <= 105
names[i] consists of lower and upper case English letters.
All the values of heights are distinct.
*/





class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        mergeSort(heights, names, 0, heights.length-1);
        reverse(names);
        return names;
    }

    private void reverse(String[] names) {
        int i=0;
        int j=names.length-1;
        while(i<j){
            String t = names[i];
            names[i] = names[j];
            names[j] = t;
            i++;
            j--;
        }
    }

    private void merge(int[] heights, String[] names, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>();
        ArrayList<String> x = new ArrayList<>();
        int left = low;
        int right = mid + 1;
        while (left <= mid && right <= high) {
            if (heights[left] <= heights[right]) {
                temp.add(heights[left]);
                x.add(names[left]);
                left++;
            } else {
                temp.add(heights[right]);
                x.add(names[right]);
                right++;
            }
        }
        while (left <= mid) {
            temp.add(heights[left]);
            x.add(names[left]);
            left++;
        }
        while (right <= high) {
            temp.add(heights[right]);
            x.add(names[right]);
            right++;
        }
        for (int i = low; i <= high; i++) {
            heights[i] = temp.get(i - low);
            names[i] = x.get(i - low);
        }
    }

    public void mergeSort(int[] heights, String[] names, int low, int high) {
        if (low >= high) return;
        int mid = (low + high) / 2 ;
        mergeSort(heights, names, low, mid);  // left half
        mergeSort(heights, names, mid + 1, high); // right half
        merge(heights, names, low, mid, high);  // merging sorted halves
    }
}