package tree;

public class LC_617 {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return null;

        TreeNode node;
        if (root1 == null || root2 == null) node = root1 == null ? root2 : root1;
        else node = new TreeNode(root1.val + root2.val);

        if (root1 != null && root2 != null) {
            node.left = mergeTrees(root1.left, root2.left);
            node.right = mergeTrees(root1.right, root2.right);
        }

        return node;
    }
}
