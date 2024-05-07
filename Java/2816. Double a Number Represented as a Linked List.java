/*
# 2816. Double a Number Represented as a Linked List


You are given the head of a non-empty linked list representing a non-negative integer without leading zeroes.

Return the head of the linked list after doubling it.


Example 1:
Input: head = [1,8,9]
Output: [3,7,8]
Explanation: The figure above corresponds to the given linked list which represents the number 189. Hence, the returned linked list represents the number 189 * 2 = 378.

Example 2:
Input: head = [9,9,9]
Output: [1,9,9,8]
Explanation: The figure above corresponds to the given linked list which represents the number 999. Hence, the returned linked list reprersents the number 999 * 2 = 1998. 
 

Constraints:

The number of nodes in the list is in the range [1, 104]
0 <= Node.val <= 9
The input is generated such that the list represents a number that does not have leading zeros, except the number 0 itself.
*/





/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    int rem = 0;
    int count = 0;
    public ListNode doubleIt(ListNode head) {
        count++;
        if(head.next == null){
            int x = (head.val*2) + rem;
            head.val = x%10;
            rem = x/10;
            if(count < 2 && rem > 0) {
                ListNode i = new ListNode(rem);
                i.next = head;
                return i;
            }
            return head;
        }
        doubleIt(head.next);
        count--;
        int x = (head.val*2) + rem;
        head.val = x%10;
        rem = x/10;
        if(count < 2 && rem > 0) {
            ListNode i = new ListNode(rem);
            i.next = head;
            return i;
        }
        return head;
    }
}