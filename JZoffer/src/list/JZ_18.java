package list;

public class JZ_18 {

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return null;

        ListNode head1 = head;

        if (head.val == val) {
            return head.next;
        }

        while (head.next != null) {
            if (head.next.val == val) {
                if (head.next.next != null) {
                    head.next = head.next.next;
                } else {
                    head.next = null;
                    break;
                }
            }
            head = head.next;
        }

        return head1;
    }
}
