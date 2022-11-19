package list;

public class LC_92 {

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) return head;
        ListNode headT = new ListNode(-1);
        headT.next = head;

        ListNode temp1 = headT, temp2 = headT, temp3 = headT;
        ListNode start, end;
        int rightTemp = right, leftTemp = left;
        while ((left - 1) > 0 && temp1 != null) {
            temp1 = temp1.next;
            left--;
        }
        start = temp1;

        while (right-- > 0 && temp2 != null) {
            temp2 = temp2.next;
        }
        end = temp2.next;

        while (rightTemp-- > 0 && temp3 != null) {
            temp3 = temp3.next;
        }
        temp3.next = null;

        ListNode after = reverse(start.next);
        start.next = after;

        while (after.next != null) {
            after = after.next;
        }
        after.next = end;
        if (leftTemp == 1) return start.next;
        return headT.next;
    }

    static ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(6);

        ListNode ans = reverseBetween(head, 3, 4);
        System.out.println(ans.next.val);
    }
}
