# 数据结构经典模板与例题

## 一、二叉树

### 1. 二叉树的中序遍历

```java
// 顺序为 右 -> 根 -> 左
// 如果为 左 -> 根 -> 右，将算法中的顺序换一下即可
void dfs(TreeNode root) {
    if (root == null) return;
    dfs(root.right);
    System.out.println(root.val);
    dfs(root.left);
}
```

#### 例题：剑指offer 54：二叉搜索树的第k大结点

```java
int res, k;

public int kthLargest(TreeNode root, int k) {
    this.k = k;
    dfs(root);
    return res;
}

// k减小到0，中途返回
void dfs(TreeNode root) {
    if (root == null) return;
    dfs(root.right);
    if (k == 0) return;
    if (--k == 0) res = root.val;
    dfs(root.left);
}
```

#### 剑指offer 36：[二叉搜索树与双向链表](https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/)

```java
// 不出预料，还是递归问题
Node pre, head;

public Node treeToDoublyList(Node root) {
    if (root == null) return null;
    dfs(root);
    
    pre.right = head;
    head.left = pre.right;
    
    return head;
}

void dfs(root cur) {
    if (cur == null) return;
    dfs(cur.left);
    
    if (pre != null) {
        pre.right = cur;
    } else {
        head = cur;
    }
    cur.left = pre;
    pre = cur;
    dfs(cur.right);
}
```



### 2. 二叉树的前序遍历

```java
// 顺序为 根 -> 左 -> 右
void dfs(TreeNode root) {
    if (root == null) return;
    System.out.println(root.val);
    dfs(root.left);
    dfs(root.right);
}
```

#### 例题：剑指offer 26：树的子结构

```java
public boolean isSubStructure(TreeNode A, TreeNode B) {
    if (A == null || B == null) return false;
    
    // 如果当前已经是其子结构返回true，或其左子树、右子树是其子结构也返回true
    return (recue(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
}

boolean recue(TreeNode A, TreeNode B) {
    // B 为空，说明已经遍历完了，结点都对得上
    if (B == null) return true;
    if (A == null || A.val != B.val) return false;
    return recue(A.left, B.left) && recue(A.right, B.right);
}
```

#### 例题：剑指offer 27：二叉树的镜像

```java
// 意思就是翻转二叉树，左右孩子互换位置
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
```

#### 例题：剑指offer 34：[二叉树中和为某一值的路径](https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/)

```java
// 还是要用到递归
List<List<Integer>> ans = new ArrayList<>();
List<Integer> list = new ArrayList<>();

public List<List<Integer>> pathSum(TreeNode root, int sum) {
    recue(root, sum);
    return ans;
}

void recue(TreeNode node, int target) {
    if (node == null) return;
    
    list.add(node.val);
    target -= node.val;
    
    if (target == 0 && node.left == null && node.right == null) {
        ans.add(new ArrayList<>(list));
    }
    
    recue(node.left, target);
    recue(node.right, target);
    // 回溯需要将新加入的结点删除
    list.remove(list.size() - 1);
}
```

#### 剑指offer 68：[二叉树的最近公共祖先](https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/) Ⅱ

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) {
        return root;
    }
    
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    
    // 左边为空说明p,q都在右子树中
    if (left == null)
        return right;
    
    // 同上
    if (right == null)
        return left;
    
    return root;
}
```



**类似题型（`对称性递归`）记得去做 -> LeetCode 100, 104, 110, 543, 617, 572, 965**

### 3. 二叉树的后序遍历

```java
// 顺序为 右 -> 左 -> 根
void dfs(TreeNode root) {
    if (root == null) return;
    dfs(root.right);
    dfs(root.left);
    System.out.println(root.val);
}
```

#### 例题：剑指offer 33：[二叉搜索树的后序遍历序列](https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/)

```java
public boolean verifyPostorder(int[] postorder) {
    return recue(postorder, 0, postorder.length - 1);
}

boolean recue(int[] postorder, int i, int j) {
    if (i >= j) return true;
    
    int p = i;
    while (postorder[p] < postorder[j]) p++;
    
    int m = p;
    while (postorder[p] > postorder[j]) p++;
    
    return p == j && recue(postorder, i, m - 1) && recue(postorder, m, j - 1);
}
```



### 4. 二叉树的层序遍历（*）

```java
// 层序遍历需要用到 广度优先搜索
void bfs(TreeNode root) {
    Queue<TreeNode> queue = new ArrayDeque<>();
    
    if (root != null) {
        queue.offer(root);
    }
    
    while (!queue.isEmpty()){
        int n = queue.size();
        
        for (int i = 0; i < n; i++) {
            // 循环中将这一层的结点弹出队列
            TreeNode node = queue.poll();
            System.out.println(node.val);
            
            // 将下一层的结点加入队列
            if (node.left != null) {
                queue.offer(node.left);
            }
            
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }
}
```



### 树的综合例题

#### 剑指offer 68：[二叉搜索树的最近公共祖先](https://leetcode.cn/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/) Ⅰ

```java
// 迭代的思想
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) return null;
    
    // 因为是二叉搜索树，所以比根节点小的都在左边，大的都在右边
    // 如果一大一小，则直接返回根节点
    while (root != null) {
        if (root.val < p.val && root.val < q.val) {
            root = root.right;
        } else if (root.val > p.val && root.val > q.val) {
            root = root.left;
        } else {
            break;
        }
    }
    
    return root;
}

// 递归的思想
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) return null;
    
    if (root.val < p.val && root.val < q.val) {
        return lowestCommonAncestor(root.right, p, q);
    } else if (root.val > p.val && root.val > q.val) {
        return lowestCommonAncestor(root.left, p, q);
    } 
    
    return root;
}
```



#### 剑指offer 55：计算二叉树的深度

```java
// 深度优先搜索
public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    else {
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth + rightDepth) + 1;
    }
}
```



#### 剑指offer 7：根据二叉树的前序遍历和中序遍历重建二叉树

```java
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
    
    // 创建每次的根节点
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
```

