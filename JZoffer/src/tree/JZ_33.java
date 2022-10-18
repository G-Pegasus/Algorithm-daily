package tree;

public class JZ_33 {

    public boolean verifyPostorder(int[] postorder) {
        return recue(postorder, 0, postorder.length - 1);
    }

    boolean recue(int[] postorder, int i, int j) {
        if (i >= j) {
            return true;
        }
        int p = i;
        while (postorder[p] < postorder[j]) p++;
        int m = p;
        while (postorder[p] > postorder[j]) p++;
        return p == j && recue(postorder, i, m - 1) && recue(postorder, m, j - 1);
    }
}
