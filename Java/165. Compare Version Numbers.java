/*
# 165. Compare Version Numbers


Given two version numbers, version1 and version2, compare them.

Version numbers consist of one or more revisions joined by a dot '.'. Each revision consists of digits and may contain leading zeros. Every revision contains at least one character. Revisions are 0-indexed from left to right, with the leftmost revision being revision 0, the next revision being revision 1, and so on. For example 2.5.33 and 0.1 are valid version numbers.

To compare version numbers, compare their revisions in left-to-right order. Revisions are compared using their integer value ignoring any leading zeros. This means that revisions 1 and 001 are considered equal. If a version number does not specify a revision at an index, then treat the revision as 0. For example, version 1.0 is less than version 1.1 because their revision 0s are the same, but their revision 1s are 0 and 1 respectively, and 0 < 1.

Return the following:

If version1 < version2, return -1.
If version1 > version2, return 1.
Otherwise, return 0.
 

Example 1:
Input: version1 = "1.01", version2 = "1.001"
Output: 0
Explanation: Ignoring leading zeroes, both "01" and "001" represent the same integer "1".

Example 2:
Input: version1 = "1.0", version2 = "1.0.0"
Output: 0
Explanation: version1 does not specify revision 2, which means it is treated as "0".

Example 3:
Input: version1 = "0.1", version2 = "1.1"
Output: -1
Explanation: version1's revision 0 is "0", while version2's revision 0 is "1". 0 < 1, so version1 < version2.
 

Constraints:

1 <= version1.length, version2.length <= 500
version1 and version2 only contain digits and '.'.
version1 and version2 are valid version numbers.
All the given revisions in version1 and version2 can be stored in a 32-bit integer.
*/





import java.util.StringTokenizer;

class Solution {
    public int compareVersion(String version1, String version2) {
        List<Integer> ver1 = new ArrayList<>();
        List<Integer> ver2 = new ArrayList<>();
        StringTokenizer one = new StringTokenizer(version1, ".");
        StringTokenizer two = new StringTokenizer(version2, ".");
        while(one.hasMoreTokens())
            ver1.add(Integer.parseInt(one.nextToken()));
        while(two.hasMoreTokens())
            ver2.add(Integer.parseInt(two.nextToken()));
        if(ver2.size() > ver1.size()){
            for(int i=ver1.size(); i<ver2.size(); i++)
                ver1.add(0);
        }
        if(ver1.size() > ver2.size()){
            for(int i=ver2.size(); i<ver1.size(); i++)
                ver2.add(0);
        }

        for(int i=0; i<ver1.size(); i++){
            if(ver1.get(i) < ver2.get(i)) return -1;
            if(ver1.get(i) > ver2.get(i)) return 1;
        }

        return 0;
    }
}




class Solution {
    public int compareVersion(String version1, String version2) {
        final String[] levels1 = version1.split("\\.");
        final String[] levels2 = version2.split("\\.");
        final int length = Math.max(levels1.length, levels2.length);

        for (int i = 0; i < length; ++i) {
            final Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
            final Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
            final int compare = v1.compareTo(v2);
            if (compare != 0)
                return compare;
        }

        return 0;
    }
}