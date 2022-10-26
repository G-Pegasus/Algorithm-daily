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

## 二、动态规划

### 1. 简单问题

#### 剑指offer 42：[连续子数组的最大和](https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/)

```java
public int maxSubArray(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    dp[0] = nums[0];
    
    for (int i = 1; i < n; i++) {
        // 如果上一个数小于0，那么再相加还不如原来大，所以取 dp[i] = nums[i]
        if (dp[i - 1] < 0) {
            dp[i] = nums[i];
        // 如果上一个数是正数，那么就与之前的数相加
        } else {
            dp[i] = dp[i - 1] + nums[i];
        }
    }
    
    Arrays.sort(dp);
    // dp 中的最大值就是连续数组的最大值
    return dp[n - 1];
}


// 题解的实现要好得多
public int maxSubArray(int[] nums) {
    int res = nums[0];
    
    for (int i = 1; i < n; i++) {
        nums[i] += Math.max(res, 0);
        res = Math.max(nums[i], res);
    }
    
    return res;
}
```

### 2. 中等问题

#### 剑指offer 14：[剪绳子](https://leetcode.cn/problems/jian-sheng-zi-lcof/)Ⅰ

```java
// 方法一：动态规划
// 每个正整数对应的最大乘积取决于比它小的正整数对应的最大乘积
// 0 和 1 不能继续拆分，所以 dp[0] = dp[1] = 0，so 2 <= i <= n and 1 <= j < i.
// 所以有两种情况，拆分或者不拆分
// 1.拆分就取 j * dp[i - j]
// 2.不拆分就取 j * (i - j)
public int cuttingRope(int n) {
    int[] dp = new int[n + 1];
    for (int i = 2; i <= n; i++) {
        int curMax = 0;
        for (int j = 1; j < i; j++) {
            curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
        }
        dp[i] = curMax;
    }
    return dp[n];
}


// 方法二：数学方法
// 如果拆分结果中有4，可以继续拆分成 2 * 2
// 而且如果有3个2，则可以替换成2个3，因为 3 * 3 > 2 * 2 * 2
// 由此可知，当 n >= 4时，拆分结果中仅有 2和3
public int cuttingRope(int n) {
    if (n == 2) return 1;
    if (n == 3) return 2;
    
    int a = n / 3;
    int b = n % 3;
    if (b == 0) {
        return (int) Math.pow(3, a);
    } else if (b == 1) {
        return (int) Math.pow(3, a - 1) * 4;
    } else {
        return (int) Math.pow(3, a) * 2;
    }
}

```

#### 剑指offer 46：[把数字翻译成字符串](https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/)

```java
// 动态规划，类似于斐波那契进阶版
// dp[0] = dp[1] = 1
// if 当前的数字在 10~25 之间就执行 dp[i] = dp[i-1] + dp[i-2]
// else dp[i] = dp[i-1]
public int translateNum(int num) {
    String s = String.valueOf(num);
    int n = s.length();
    int a = 1, b = 1;
    
    for (int i = 2; i <= n; i++) {
        int tmp = Integer.parseInt(s.substring(i - 2, i));
        int c = tmp >= 10 && tmp <= 25 ? a + b : b;
        a = b;
        b = c;
    }
    
    return b;
}
```

#### 剑指offer 47：[礼物的最大价值](https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof/)

```java
// 我自己的垃圾思路，每次dp的位置都是本位置与上下的最大值相加
public int maxValue(int[][] grid) {
    int[][] dp = new int[grid.length][grid[0].length];
    dp[0][0] = grid[0][0];
    
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            if (i > 0 && j > 0) {
                dp[i][j] = Math.max(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j]);
            } else if (i > 0) {
                dp[i][j] = dp[i - 1][j] + grid[i][j];
            } else if (j > 0) {
                dp[i][j] = dp[i][j - 1] + grid[i][j];
            }
        }
    }
        
    return dp[grid.length - 1][grid[0].length - 1];
}


// 大佬的优化解法，因为之前只有一行或者一列的时候都要在循环里加两层判断，所以这个直接提出来
// 并且他还没有另开一个数组，直接在原数组上修改了
public int maxValue(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    
    for(int j = 1; j < n; j++) // 初始化第一行
        grid[0][j] += grid[0][j - 1];
    
    for(int i = 1; i < m; i++) // 初始化第一列
        grid[i][0] += grid[i - 1][0];
    
    for(int i = 1; i < m; i++)
        for(int j = 1; j < n; j++) 
            grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
    
    return grid[m - 1][n - 1];
}
```

#### 剑指offer 49：[丑数](https://leetcode.cn/problems/chou-shu-lcof/)

```java
// 思路比较简单，看代码就能看懂
public int nthUglyNumber(int n) {
    int[] dp = new int[n];
    int a = 0, b = 0, c = 0;
    dp[0] = 1;
    
    for (int i = 1; i < n; i++) {
        dp[i] = Math.min(Math.min(dp[a] * 2, dp[b] * 3), dp[c] * 5);
        if (dp[i] == dp[a] * 2) a++;
        if (dp[i] == dp[b] * 3) b++;
        if (dp[i] == dp[c] * 5) c++;
    }
    
    return dp[n - 1];
}
```

#### 剑指offer 60：[ n个骰子的点数](https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof/)

```java
// 此题真的好难，555~
// dp.length = 5 * n + 1 长度，也就是说，迭代的时候吗，每次加5
public double[] dicesProbability(int n) {
    double[] dp = new double[6];
    Arrays.fill(dp, 1.0 / 6.0);
    
    for (int i = 2; i <= n; i++) {
        double[] res = new double[5 * i + 1];
        for (int j = 0; j < dp.length; j++) {
            for (int k = 0; k < 6; k++) {
                res[j + k] += dp[j] / 6.0;
            }
        }
        dp = res;
    }
    
    return dp;
}
```

太难了，不知道怎么解释，放几张图片更好

![step1](https://pic.leetcode.cn/1614960989-vkPMks-Picture4.png)

![step2](https://pic.leetcode.cn/1614960989-lzbHYA-Picture5.png)

![step3](https://pic.leetcode.cn/1614960989-pNSQec-Picture6.png)

![step4](https://pic.leetcode.cn/1614960989-oRLcts-Picture7.png)

![step5](https://pic.leetcode.cn/1614960989-SlimYn-Picture10.png)

以此类推...

![step6](https://pic.leetcode.cn/1614960989-AnyWXD-Picture11.png)

![step7](https://pic.leetcode.cn/1614960989-WyeOfz-Picture12.png)

#### 剑指offer 63：[股票的最大利润](https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof/)

```java
// 定义一个花销 cost，和当前 price 比较，比它小就不动了
// 收益 profit = max(profit, price - cost)
public static int maxProfit(int[] prices) {
    int cost = Integer.MAX_VALUE, profit = 0;
    
    for (int price: prices) {
        cost = Math.min(cost, price);
        profit = Math.max(profit, price - cost);
    }
    
    return profit;
}
```



## 三、其他算法

### 1. 双指针对撞

#### 剑指offer 57：[和为s的两个数字](https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/)Ⅰ

```java
// 先写上我自己的垃圾算法
// 主要是构建哈希集合，然后寻找两个符合条件的数
public int[] twoSum(int[] nums, int target) {
    Set<Integer> set = new HashSet<>();
    int[] ans = new int[2];
    
    for (int num : nums) {
        set.add(num);
        if (set.contains(target - num)) {
            ans[0] = num;
            ans[1] = target - num;
        }
    }
    
    return ans;
}



// 再来看看大佬的解法
public int[] twoSum(int[] nums, int target) {
    int n = nums.length;
    int l = 0, r = n - 1;
    
    while (l < r) {
        if ((nums[l] + nums[r]) == target) {
            return new int[]{nums[l], nums[r]};
        } else if ((nums[l] + nums[r]) < target) {
           	l++;
        } else {
            r--;
        }
    }
    
    return new int[0];
}
```

### 2. 滑动窗口

#### 剑指offer 57：[和为s的两个数字](https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/)Ⅱ

``` java
// 滑动窗口问题
public int[][] findContinuousSequence(int target) {
    int start = 1, end = 2, sum = 3;
    ArrayList<int[]> ans = new ArrayList<>();
    
    while (start < end) {
        if (sum == target) {
            int[] res = new int[end - start + 1];
            for (int i = start; i <= end; i++) {
                res[i - start] = i;
            }
            ans.add(res);
        }
        
        // 如果此时 sum >= target 那么头部的窗口向右移一格，反之尾部窗口向左移一格
        if (sum >= target) {
            sum -= start;
            start--;
        } else {
            end++;
            sum += end;
        }
    }
    
    return ans.toArray(new int[0][]);
}
```

