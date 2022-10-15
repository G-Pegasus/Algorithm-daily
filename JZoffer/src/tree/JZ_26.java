package tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class JZ_26 {

    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;

        return recue(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    static boolean recue(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null || A.val != B.val) return false;
        return (recue(A.left, B.left) && recue(A.right, B.right));
    }



    // 层序遍历测试
    /*
    static void bfs(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();

        if (root != null) {
            queue.offer(root);
        }

        while (!queue.isEmpty()){
            int n = queue.size();

            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                System.out.println(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    } */

    public static void main(String[] args) {
        TreeNode treeNodeA = new TreeNode(3);
        treeNodeA.left = new TreeNode(4);
        treeNodeA.right = new TreeNode(5);
        treeNodeA.left.left = new TreeNode(1);
        treeNodeA.left.right = new TreeNode(2);

        TreeNode treeNodeB = new TreeNode(4);
        treeNodeB.left = new TreeNode(1);

        System.out.println(isSubStructure(treeNodeA, treeNodeB));

        // bfs(treeNodeA);
    }
}
