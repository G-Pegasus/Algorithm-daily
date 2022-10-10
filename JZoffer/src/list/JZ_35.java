package list;

import java.util.HashMap;

public class JZ_35 {

    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Node copy = new Node(head.val);
        Node copy1 = copy, copy2 = copy;
        Node head1 = head, head2 = head;

        HashMap<Node, Integer> value = new HashMap<>();
        HashMap<Node, Integer> value1 = new HashMap<>();
        HashMap<Integer, Node> ans = new HashMap<>();
        ans.put(0, copy);
        value.put(head, 0);
        value.put(null, -1);

        int count = 0;
        while (head.next != null) {
            count++;
            head = head.next;
            copy.next = new Node(head.val);
            copy = copy.next;
            value.put(head, count);
            ans.put(count, copy);
        }

        while (head1.next != null) {
            value1.put(head1, value.get(head1.random));
            head1 = head1.next;
        }
        value1.put(head1, value.get(head1.random));

        while (copy1.next != null) {
            copy1.random = ans.get(value1.get(head2));
            copy1 = copy1.next;
            head2 = head2.next;
        }
        copy1.random = ans.get(value1.get(head2));

        return copy2;
    }


    // 官方题解，哈希+回溯
    HashMap<Node, Node> cachedNode = new HashMap<>();

    public Node copyRandomList1(Node head) {
        if (head == null) {
            return null;
        }
        if (!cachedNode.containsKey(head)) {
            Node headNew = new Node(head.val);
            cachedNode.put(head, headNew);
            headNew.next = copyRandomList1(head.next);
            headNew.random = copyRandomList1(head.random);
        }
        return cachedNode.get(head);
    }
}
