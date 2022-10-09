package list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JZ_6 {

    private static final List<Integer> list = new ArrayList<>();

    public static int[] reversePrint(ListNode head) {
        addList(head);
        int[] array = new int[list.size()];
        int j = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            array[j] = list.get(i);
            j++;
        }

        return array;
    }

    public static void addList(ListNode head) {
        if (head == null)
            return;
        else
            list.add(head.val);
        while (head.next != null) {
            list.add(head.next.val);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        System.out.println(Arrays.toString(reversePrint(listNode)));
    }
}