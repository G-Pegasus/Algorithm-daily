package tree;

import java.util.HashMap;

public class JZ_7 {
    private HashMap<Integer, Integer> indexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return mBuildTree(preorder, 0, n - 1, 0);
    }

    TreeNode mBuildTree(
            int[] preorder,
            int preorder_left, int preorder_right,
            int inorder_left
    ) {
        // 该种情况说明二叉树是空的
        if (preorder_left > preorder_right) return null;

        // 根据 HashMap 找到位于中序遍历结果中的根结点
        int inorder_root = indexMap.get(preorder[preorder_left]);

        // 找到每次的根节点
        TreeNode root = new TreeNode(preorder[preorder_left]);

        // 得到左子树的结点数目
        int size_left_subtree = inorder_root - inorder_left;

        root.left = mBuildTree(preorder,
                preorder_left + 1, preorder_left + size_left_subtree,
                inorder_left);

        root.right = mBuildTree(preorder,
                preorder_left + size_left_subtree + 1, preorder_right,
                inorder_root + 1);

        return root;
    }
}
