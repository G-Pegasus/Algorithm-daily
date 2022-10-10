package list;

public class JZ_76 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        ListNode head1 = head;

        while (head.next != null) {
            if (head.next.val == head.val) {
                if (head.next.next != null) {
                    head.next = head.next.next;
                } else {
                    head.next = null;
                    break;
                }
            } else {
                head = head.next;
            }
        }

        return head1;
    }
}
