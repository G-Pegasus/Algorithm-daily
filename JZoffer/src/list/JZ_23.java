package list;

import java.util.HashSet;

public class JZ_23 {

    public ListNode detectCycle1(ListNode head) {

        if (head == null) return null;

        // 通过哈希集合不能重复的特性来做题，但是好慢...
        HashSet<ListNode> set = new HashSet<>();
        set.add(head);

        while (head.next != null) {
            set.add(head.next);
            if (set.contains(head.next.next)) {
                return head.next.next;
            }
            head = head.next;
        }

        return null;
    }

    // 快慢指针
    public ListNode detectCycle2(ListNode head) {
        if (head == null) return null;

        ListNode l1 = head, l2 = head;
        while (l2 != null) {
            l1 = l1.next;
            if (l2.next != null) {
                l2 = l2.next.next;
            } else {
                return null;
            }

            if (l1 == l2) {
                ListNode temp = head;
                while (temp != l1) {
                    l1 = l1.next;
                    temp = temp.next;
                }
                return temp;
            }
        }

        return null;
    }
}
