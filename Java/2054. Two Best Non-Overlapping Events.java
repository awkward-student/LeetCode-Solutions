/*
# 2054. Two Best Non-Overlapping Events


You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei]. The ith event starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of valuei. You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.

Return this maximum sum.

Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts and the other ends at the same time. More specifically, if you attend an event with end time t, the next event must start at or after t + 1.

Example 1:
Input: events = [[1,3,2],[4,5,2],[2,4,3]]
Output: 4
Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.

Example 2:
Example 1 Diagram
Input: events = [[1,3,2],[4,5,2],[1,5,5]]
Output: 5
Explanation: Choose event 2 for a sum of 5.

Example 3:
Input: events = [[1,5,3],[1,5,1],[6,6,5]]
Output: 8
Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.
 
Constraints:

2 <= events.length <= 105
events[i].length == 3
1 <= startTimei <= endTimei <= 109
1 <= valuei <= 106
*/






\class Event {
    public int time;
    public int value;
    public int isStart;

    public Event(int time, int value, int isStart) {
        this.time = time;
        this.value = value;
        this.isStart = isStart;
    }
}

class Solution {
    public int maxTwoEvents(int[][] events) {
        int ans = 0;
        int maxValue = 0;
        Event[] evts = new Event[events.length * 2];

        for (int i = 0; i < events.length; ++i) {
            final int start = events[i][0];
            final int end = events[i][1];
            final int value = events[i][2];
            evts[i * 2] = new Event(start, value, 1);
            evts[i * 2 + 1] = new Event(end + 1, value, 0);
        }

        Arrays.sort(evts,
                (a, b) -> a.time == b.time ? Integer.compare(a.isStart, b.isStart)
                        : Integer.compare(a.time, b.time));

        for (Event evt : evts)
            if (evt.isStart == 1)
                ans = Math.max(ans, evt.value + maxValue);
            else
                maxValue = Math.max(maxValue, evt.value);

        return ans;
    }
}