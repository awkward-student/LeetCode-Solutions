/*
# 1496. Path Crossing

Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively. You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.

Return true if the path crosses itself at any point, that is, if at any time you are on a location you have previously visited. Return false otherwise.
 

Example 1:
Input: path = "NES"
Output: false 
Explanation: Notice that the path doesn't cross any point more than once.

Example 2:
Input: path = "NESWW"
Output: true
Explanation: Notice that the path visits the origin twice.
 

Constraints:

1 <= path.length <= 104
path[i] is either 'N', 'S', 'E', or 'W'.
*/





class Solution {
    public boolean isPathCrossing(String path) {
        char[] move = path.toCharArray();
        Set<String> visit = new HashSet<>();
        int x = 0;
        int y = 0;
        visit.add(Integer.toString(x)+" "+Integer.toString(y));

        for(char s: move){
            switch(s){
                case 'E': ++ x;
                break;
                case 'W': -- x;
                break;
                case 'N': ++ y;
                break;
                case 'S': -- y;
            }
            String position = Integer.toString(x)+" "+Integer.toString(y);
            if (visit.contains(position)) return true;
            else visit.add(position);
        }
        System.out.print(visit);
        return false;
    }
}