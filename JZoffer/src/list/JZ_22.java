package list;

public class JZ_22 {

    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null)
            return null;
        else if (head.next == null)
            return head;
        ListNode slow = head;

        //noinspection ConstantConditions
        while (head.next != null) {
            if (--k > 0) {
                head = head.next;
            } else {
                head = head.next;
                slow = slow.next;
            }
            if (head.next == null)
                return slow;
        }
        return null;
    }
}
