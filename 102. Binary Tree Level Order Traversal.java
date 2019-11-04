102. Binary Tree Level Order Traversal

Solution 1: BFS
Time: O(n)

public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null)  return res;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
        List<Integer> tmp = new ArrayList<>();
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            tmp.add(node.val);
            if (node.left != null)     queue.offer(node.left);
            if (node.right != null)    queue.offer(node.right);
        }
        res.add(tmp);
    }
    return res;
}




Solution 2: DFS
Time: O(n)

public List<List<Integer>> levelOrder(TreeNode root) {    
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    dfs(res, root, 0);
    return res;
}
private void dfs(List<List<Integer>> res, TreeNode root, int level) {
    if (root == null)   return;
    if (level == res.size())    res.add(new ArrayList<>());
    res.get(level).add(root.val);
    dfs(res, root.left, level + 1);
    dfs(res, root.right, level + 1);
}


C++
    
vector<vector<int>> levelOrder(TreeNode* root) {
    vector<vector<int>> ret;
    if (!root) return ret; // DO NOT FORGET NULL!!!
    queue<TreeNode*> q;
    q.push(root);
    while(!q.empty()) {
        int size = q.size();
        vector<int> curr;
        for (int i = 0; i < size; i++) {
            TreeNode* front = q.front();
            curr.push_back(front->val);
            q.pop();
            if (front->left) {
                q.push(front->left);
            }
            if (front->right) {
                q.push(front->right);
            }
        }
        ret.push_back(curr);
    }
    return ret;
}

