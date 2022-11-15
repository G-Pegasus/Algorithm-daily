package list;

import java.util.ArrayList;

public class LC_143 {

    public static void reorderList(ListNode head) {
        ListNode temp = head, ans = head;
        ArrayList<ListNode> list = new ArrayList<>();

        while (temp != null) {
            list.add(new ListNode(temp.val));
            temp = temp.next;
        }

        int flag = 1;
        int indexEnd = list.size() - 1, indexStart = 1;
        while (indexStart <= indexEnd) {
            if (flag % 2 == 1) {
                ans.next = list.get(indexEnd);
                indexEnd--;
            } else {
                ans.next = list.get(indexStart);
                indexStart++;
            }

            ans = ans.next;
            flag++;
        }
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);

        reorderList(node);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
