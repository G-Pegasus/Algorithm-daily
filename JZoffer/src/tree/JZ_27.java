package tree;

public class JZ_27 {

    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;

        mirrorTool(root);

        return root;
    }

    static void mirrorTool(TreeNode root) {
        TreeNode temp;

        temp = root.left;
        root.left = root.right;
        root.right = temp;

        if (root.left != null)
            mirrorTool(root.left);

        if (root.right != null)
            mirrorTool(root.right);
    }



    // 官方题解
    public TreeNode mirrorTree1(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = mirrorTree1(root.left);
        TreeNode right = mirrorTree1(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
