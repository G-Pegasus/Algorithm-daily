# 数据结构经典模板与例题

## 一、排序&查找

### 1. 快速排序

```java
public int[] quickSort(int[] nums) {
    return sort(nums, 0, nums.length - 1);
}

int[] sort(int[] nums, int left, int right) {
    if (left < right) {
        int index = randomPartition(nums, left, right);
        sort(nums, left, index - 1);
        sort(nums, index + 1, right);
    }
    return nums;
}

// 随机选取一个基准，会更快一些
int randomPartition(int[] nums, int left, int right) {
    int index = new Random(right - left + 1) + left;
    swap(nums, index, left);
    return partition(nums, left, right);
}

int partition(int[] nums, int left, int right) {
    int index = left + 1;
    for (int i = index; i <= right; i++) {
        if (nums[i] < nums[left]) {
            swap(nums, i, index);
            index++;
        }
    }
    swap(nums, index - 1, left);
    return index - 1;
}

int swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

#### 力扣 215：[数组中的第K个最大元素](https://leetcode.cn/problems/kth-largest-element-in-an-array/)

```java
// 快速选择算法
public int findKthLargest(int[] nums, int k) {
    return quickSelect(nums, 0, nums.length - 1, nums.length - k);
}

int quickSelect(int[] arr, int left, int right, int index) {
    int q = randomPartition(arr, left, right);
    if (q == index) {
        return arr[q];
    } else {
        return q < index ? quickSelect(arr, q + 1, right, index) : quickSelect(arr, left, q - 1, index);
    }
}

int randomPartition(int[] arr, int left, int right) {
    int index = new Random().nextInt(right - left + 1) + left;
    swap(arr, right, index);
    return partition(arr, left, right);
}

int partition(int[] arr, int left, int right) {
    int index = left, x = arr[right];
    // 有比最右边小的，都放在左边
    for (int j = left; j < right; j++) {
        if (arr[j] <= x) {
            swap(arr, index++, j);
        }
    }
    // 最后，把最右边的和index交换，这样在index左边的都是比它小的
    swap(arr, index, right);
    return index;
}

void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
```



### 2. 归并排序

```java
public int[] mergeSort(int[] arr) {
    if (arr.length < 2) return arr;
    
    int mid = arr.length / 2;
    int[] left = Arrays.copyOfRange(arr, 0, mid);
    int[] right = Arrays.copyOfRange(arr, mid, arr.length);
    
    return sort(mergeSort(left), mergeSort(right));
}

int[] sort(int[] left, int[] right) {
    int[] ans = new int[left.length + right.length];
    int i = 0;
    
    while (left.length > 0 && right.length > 0) {
        if (left[0] < right[0]) {
            ans[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        } else {
            ans[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }
    }
    
    while (left.length > 0) {
        ans[i++] = left[0];
        left = Arrays.copyOfRange(left, 1, left.length);
    }
    
    while (right.length > 0) {
        ans[i++] = right[0];
        right = Arrays.copyOfRange(right, 1, right.length);
    }
    
    return ans;
}
```

### 3. 选择排序

```java
public int[] SelectionSort(int[] arr) {
	// 总共要经过 n-1 轮比较
	for (int i = 0; i < arr.length - 1; i++) {
    	int min = i;

    	// 每轮需要比较的次数 n-i
        for (int j = i + 1; j < arr.length; j++) {
        	if (arr[j] < arr[min]) {
            	// 记录目前能找到的最小值元素的下标
                min = j;
            }
        }

        // 将找到的最小值和i位置所在的值进行交换
        if (i != min) {
        	int tmp = arr[i];
            arr[i] = arr[min];
            arr[min] = tmp;
        }
	}
    
    return arr;
}
```

### 4. 插入排序

```java
public int[] insertSort(int[] arr) {
	 // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
     for (int i = 1; i < arr.length; i++) {

     	// 记录要插入的数据
        int tmp = arr[i];

        // 从已经排序的序列最右边的开始比较，找到比其小的数
        int j = i;
        while (j > 0 && tmp < arr[j - 1]) {
        	arr[j] = arr[j - 1];
            j--;
        }

        // 存在比其小的数，插入
        if (j != i) {
        	arr[j] = tmp;
        }

	}
    return arr;    
}
```

### 5. 堆排序

```java
public int[] heapSort(int[] arr) {
    int len = arr.length;
    buildMaxHeap(arr, len);
    
    for (int i = len - 1; i > 0; i--) {
        swap(arr, 0, i);
        len--;
        heapify(arr, 0, len);
    }
    
    return arr;
}

void buildMaxHeap(int[] arr, int len) {
    for (int i = len << 1; i >= 0; i--) {
        heapify(arr, i, len);
    }
}

void heapify(int[] arr, int i, int len) {
    int left = 2 * i + 1;
    int right = 2 * i + 2;
    int largest = i;
    
    if (left < len && arr[left] > arr[largest]) {
        largest = left;
    }
    
    if (right < len && arr[right] > arr[largest]) {
        largest = right;
    }
    
    if (largest != i) {
        swap(arr, i, largest);
        heapify(arr, largest, len);
    }
}

void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
```

### 6. 二分查找

#### 剑指offer 53：[在排序数组中查找数字 I](https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/)

```java
public int search(int[] nums, int target) {
    return helper(nums, target) - helper(nums, target - 1);
}

int helper(int nums[], int target) {
    int left = 0, right = nums.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (nums[mid] <= target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return left;
}
```

#### 剑指offer 53： [0～n-1中缺失的数字](https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/)

```java
public int missingNumber(int[] nums) {
    int left = 0, right = nums.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == mid) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return left;
}
```

### 7. 其他查找方法

#### 剑指offer 4：[二维数组中的查找](https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/)

```java
// 神级思路，无敌了
// 将这个二维数组看做一个类似于二叉树的结构
// 从左下角开始，当前数 < target，则删除当前数所在列；当前数 > target，则删除当前数所在行
public boolean findNumberIn2DArray(int[][] matrix, int target) {
    int i = matrix.length - 1, j = 0;
    while (i >= 0 && j < matrix[0].length) {
        if (matrix[i][j] < target) j++;
        else if (matrix[i][j] > target) i--;
        else return true;
    }
    return false;
}
```

#### 剑指offer 44：[数字序列中某一位的数字](https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/)

```java
// 涉及到数学知识
public int findNthDigit(int n) {
    int digit = 1;
    long start = 1;
    long count = 9;
    
    while (n > count) {
        n -= count;
        digit++;
        start *= 10;
        count = 9 * start * digit;
    }
    
    long num = start + (n - 1) / digit;
    int ans = (n - 1) % digit;
    return (num + "").charAt(ans) - '0';
}
```



## 二、链表&数组

### 1. 力扣 25：[K 个一组翻转链表](https://leetcode.cn/problems/reverse-nodes-in-k-group/)

```java
public ListNode reverseKGroup(ListNode head, int k) {
    ListNode temp = new ListNode(0);
    temp.next = head;
    
    ListNode pre = temp;
    ListNode end = temp;
    
    while (end.next != null) {
        for (int i = 0; i < k && end != null; i++) end = end.next;
        if (end == null) break;
        
        ListNode start = pre.next;
        ListNode next = end.next;
        end,next = null;
        pre.next = reverse(start);
        start.next = next;
        
        pre = start;
        end = pre;
    }
    
    return temp.next;
}

// 反转链表的方法
ListNode reverse(ListNode head) {
    ListNode pre = null, curr = head;
    while (curr != null) {
        ListNode next = curr.next;
        curr.next = pre;
        pre = curr;
        curr = next;
    }
    return pre;
}
```

### 2. 剑指offer 29 & 力扣 54 [螺旋矩阵](https://leetcode.cn/problems/spiral-matrix/)

```java
public List<Integer> spiralOrder(int[][] matrix) {
    int left = 0, right = matrix[0].length - 1;
    int top = 0, bottom = matrix.length - 1;
    List<Integer> ans = new ArrayList<>();
    
    while (true) {
        for (int i = left; i <= right; i++) ans.add(matrix[top][i]);
        if (++top > bottom) break;
        
        for (int i = top; i <= bottom; i++) ans.add(matrix[i][right]);
        if (--right < left) break;
        
        for (int i = right; i >= left; i--) ans.add(matrix[bottom][i]);
        if (--bottom < top) break;
        
        for (int i = bottom; i >= top; i--) ans.add(matrix[i][left]);
        if (++left > right) break;
    }
    
    return ans;
}
```

### 3. 力扣 15：[三数之和](https://leetcode.cn/problems/3sum/)

```java
public List<List<Integer>> threeSum(int[] nums) {
    int n = nums.length;
    Arrays.sort(nums);
    List<List<Integer>> ans = new ArrayList<>();
    
    for (int first = 0; first < n; first++) {
        if (first > 0 && nums[first] == nums[first - 1]) continue;
        
        int third = n - 1;
        int target = -nums[first];
        
        for (int second = first + 1; second < n; second++) {
            if (second > first + 1 && nums[second] == nums[second - 1]) continue;
            
            while (second < third && nums[second] + nums[third] > target) third--;
            
            if (second == third) break;
            
            if (nums[second] + nums[third] == target) {
                List<Integer> list = new ArrayList<>();
                list.add(first);
                list.add(second);
                list.add(third);
                ans.add(list);
            }
        }
    }
    
    return ans;
}
```



## 三、二叉树

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
public TreeNode mirrorTree(TreeNode root) {
    if (root == null) return null;
    mirrorTool(root);
    return root;
}

void mirrorTool(TreeNode root) {
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

#### 力扣 199：[二叉树的右视图](https://leetcode.cn/problems/binary-tree-right-side-view/)

```java
// 终归还是层序遍历，只不过加入链表中的是每层的最后一个结点
public List<Integer> rightSideView(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    Queue<TreeNode> queue = new ArrayDeque<>();
    
    if (root != null) queue.offer(root);
    
    while (!queue.isEmpty()) {
        int n = queue.size();
        
        for (int i = 0; i < n; i++) {
            TreeNode node = queue.poll();
            if (i == n - 1) {
                ans.add(node.val);
            }
            
            if (node.left != null) {
                queue.offer(node.left);
            }
            
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }
    
    return ans;
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
        return Math.max(leftDepth, rightDepth) + 1;
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

#### 5. 力扣 110：[平衡二叉树](https://leetcode.cn/problems/balanced-binary-tree/)

```java
public boolean isBalanced(TreeNode root) {
    if (root == null) return true;
    return (Math.abs(deepth(root.left) - deepth(root.right)) >= 1) && isBalanced(root.left) && isBalanced(root,right);
}

int deepth(TreeNode root) {
    if (root == null) return 0;
    return Math.max(deepth(root.left), deepth(root.right)) + 1;
}
```



## 四、动态规划

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
        nums[i] += Math.max(nums[n - 1], 0);
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

#### 力扣 221：[最大正方形](https://leetcode.cn/problems/maximal-square/)

```java
public int maximalSquare(char[][] matrix) {
    int rows = matrix.length, columns = matrix[0].length;
    int maxSide = 0;
    int[][] dp = new int[rows][columns];
    
    if (matrix == null || rows == 0 || columns == 0) return 0;
    
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
            if (matrix[i][j] == '1') {
                if (i == 0 || j == 0) {
                    dp[i][j] == 1;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                }
            }
            maxSide = Math.max(maxSide, dp[i][j]);
        }
    }
    
    return maxSide * maxSide;
}
```



## 五、其他算法

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

### 3. 哈希

#### 剑指offer 48 & 力扣 3：[无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters/)

```java
// 该题属于滑动窗口 + 哈希
public int lengthOfLongestSubstring(String s) {
    Set<Character> maxString = new HashSet<>();
    int n = s.length();
    int right = 0, maxLength = 0;
    
    for (int i = 0; i < n; i++) {
        if (i != 0) {
            maxString.remove(s.charAt(i - 1));
        }
        
        // 循环直到遇到 HashSet 中存在的字符
        while (rigth < n && !maxString.contains(s.charAt(rigth))) {
            maxString.add(s.charAt(right++));
        }
        maxLength = Math.max(maxLength, right - i);
    }
    return maxLength;
}
```

#### 力扣 146：[LRU 缓存](https://leetcode.cn/problems/lru-cache/)

```java
Map<Integer, Integer> map;
Deque<Integer> deque;
int capacity;

// 本题思路为：通过定义一个队列，put的时候如果此时map中有这个key，那么替换其value，并在队列中转移到最后面代表是最近使用过的。
// 如果没有这个key，判断是否超出容量，如果没有超出，则put进map和队列；
// 如果超出了，就将队列最前面的key取出来，在map中删除然后再添加新元素。(因为队列最前面的元素是最久没用过的)
// get的时候，判断map中是否有这个key，如果有，就将其在队列中删除再添加进队尾，表示最近使用过。
public LRUCache(int capacity) {
    map = new HashMap<>(capacity);
    deque = new ArrayDeque<>(capacity);
    this.capacity = capacity;
}

public int get(int key) {
    if (map.containsKey(key)) {
        deque.remove(key);
        deque.offerLast(key);
        return map.get(key);
    }
    return -1;
}

public void put(int key, int value) {
    if (map.containsKey(key)) {
        map.replace(key, value);
        deque.remove(key);
        deque.offerLast(key);
    } else {
        if (deque.size() < capacity) {
            map.put(key, value);
            deque.offerLast(key);
        } else {
            int key1 = deque.pollFirst();
            deque.offerLast(key);
            map.remove(key1);
            map.put(key, value);
        }
    }
}
```

#### 力扣 41：[缺失的第一个正数](https://leetcode.cn/problems/first-missing-positive/)

```java
// 原地哈希
public int firstMissingPositive(int[] nums) {
    int n = nums.length;
    HashMap<Integer, Integer> map = new HashMap<>();
    
    for (int i = 0; i < n; i++) {
        if (nums[i] <= 0) nums[i] = n + 1;
        map.put(i + 1, nums[i]);
    }
    
    for (int i = 1; i <= n; i++) {
        if (map.get(i) <= n) {
            nums[map.get(i) - 1] = nums[map.get(i) - 1] > 0 ? -nums[map.get(i) - 1] : nums[map.get(i) - 1];
        }
    }
    
    for (int i = 0; i < n; i++) {
        if (nums[i] > 0) {
            return i + 1;
        }
    }
    
    return n + 1;
}
```

### 4. 深度优先搜索

#### 力扣 200：[岛屿数量](https://leetcode.cn/problems/number-of-islands/)

```java
public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) return 0;
    
    int nr = grid.length, nr = grid.length;
    int num = 0;
    
    for (int r = 0; r < nr; r++) {
        for (int c = 0; c < nc; c++) {
            num++;
            dfs(grid, r, c);
        }
    }
    
    return num;
}

void dfs(char[][] grid, int r, int c) {
    int nr = grid.length, nr = grid.length;
    if (r < 0 || c < 0 || c >= nc || r >= nr || grid[r][c] == '0') return;
    
    grid[r][c] = '0'; // 与 ‘1’ 相邻的 ‘1’ 标记为 ‘0’
    dfs(grid, r - 1, c);
    dfs(grid, r + 1, c);
    dfs(grid, r, c - 1);
    dfs(grid, r, c + 1);
}
```


