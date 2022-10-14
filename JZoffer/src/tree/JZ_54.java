package tree;

import java.util.ArrayList;
import java.util.Comparator;

public class JZ_54 {

    static ArrayList<Integer> arrayList = new ArrayList<>();

    public static int kthLargest(TreeNode root, int k) {
        addList(root);
        arrayList.sort(Comparator.reverseOrder());
        return arrayList.get(k - 1);
    }

    static void addList(TreeNode root) {
        arrayList.add(root.val);
        if (root.left != null && root.right != null) {
            addList(root.left);
            addList(root.right);
        } else if (root.right != null) {
            addList(root.right);
        } else if (root.left != null) {
            addList(root.left);
        }
    }


    static int res, k;
    public static int kthLargest1(TreeNode root, int k) {
        JZ_54.k = k;
        dfs(root);
        return res;
    }

    static void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.right);
        if (k == 0) return;
        if (--k == 0) res = root.val;
        dfs(root.left);
    }

    static void dfs1(TreeNode root) {
        if (root == null) return;
        dfs1(root.right);
        System.out.println(root.val);
        dfs1(root.left);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(4);
        treeNode.left.right = new TreeNode(2);
        System.out.println(kthLargest(treeNode, 1));
        dfs1(treeNode);
    }
}
