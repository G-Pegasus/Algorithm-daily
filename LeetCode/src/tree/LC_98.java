package tree;

import java.util.ArrayList;

public class LC_98 {

    static ArrayList<Integer> list = new ArrayList<>();

    public static boolean isValidBST(TreeNode root) {
        if (root.left == null && root.right == null) return true;

        dfs(root);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) return false;
        }

        return true;
    }

    static void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(4);
        treeNode.right = new TreeNode(6);
        treeNode.right.left = new TreeNode(3);
        treeNode.right.right = new TreeNode(7);
        System.out.println(isValidBST(treeNode));
    }
}
