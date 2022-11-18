package list;

import java.util.ArrayList;
import java.util.List;

public class LC_61 {

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        ListNode temp = head;
        int size = 0;
        while (temp.next != null) {
            size++;
            temp = temp.next;
        }
        int kt = k % (size + 1);
        while (kt-- > 0) {
            head = move(head);
        }
        return head;
    }

    static ListNode move(ListNode head) {
        ListNode temp = head;
        while (head.next != null) {
            head = head.next;
        }
        head = new ListNode(head.val);
        head.next = temp;
        ListNode temp1 = head;
        while (head.next.next != null) {
            head = head.next;
        }
        head.next = null;
        return temp1;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println(rotateRight(head, 1).val);

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        String[] strs = new String[list.size()];
        list.toArray(strs);
    }
}
