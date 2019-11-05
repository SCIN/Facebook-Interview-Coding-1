314. Binary Tree Vertical Order Traversal

C++ DFS

vector<vector<int>> verticalOrder(TreeNode* root) {
    vector<vector<int>> ret;
    if (!root) return ret;
    map<int, map<int, vector<int>>> m;
    dfs(m, root, 0, 0);
    for (auto it = m.begin(); it != m.end(); ++it) {
        map<int, vector<int>> currLevel = it->second;
        vector<int> curr;
        for (auto it2 = currLevel.begin(); it2 != currLevel.end(); ++it2) {
            vector<int> v = it2->second;
            for (int i = 0; i < v.size(); i++) {
                curr.push_back(v[i]);
            }   
        }
        ret.push_back(curr);
    }
    return ret;
}
void dfs(map<int, map<int, vector<int>>>& m, TreeNode* root, int level, int height) {
    if (!root) return;
    m[level][height].push_back(root->val);
    dfs(m, root->left, level - 1, height + 1);
    dfs(m, root->right, level + 1, height + 1);
}
   

JAVA:
class TreeNodeWithCol {
    TreeNode treeNode;
    int col;
    public TreeNodeWithCol(TreeNode node, int col) {
        this.treeNode = node;
        this.col = col;
    }
}
public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null)   return res;
    Map<Integer, List<Integer>> map = new HashMap<>();
    Queue<TreeNodeWithCol> bfs = new ArrayDeque<>();
    bfs.add(new TreeNodeWithCol(root, 0));
    int max = 0, min = 0;
    while (!bfs.isEmpty()) {
        TreeNodeWithCol node = bfs.poll();
        int col = node.col;
        if (!map.containsKey(col))  map.put(col, new ArrayList<>());
        map.get(col).add(node.treeNode.val);
        if (node.treeNode.left != null) {
            bfs.offer(new TreeNodeWithCol(node.treeNode.left, col - 1));
            min = Math.min(min, col - 1);
        }
        if (node.treeNode.right != null) {
            bfs.offer(new TreeNodeWithCol(node.treeNode.right, col + 1));
            max = Math.max(max, col + 1);
        }
    }
    for (int i = min; i <= max; i++) 
        res.add(map.get(i));
    return res;
}

//If you wanna avoid using hashmap cuz of key conflicts,you can use doubly-linked list,each node stores a Arraylist of vals,
//then replace Queue<Integer> cols with Queue<LinkedList> cols,each time we poll,we first add it to curr node's arraylist,
//put non-null left node to a new left list(if curr.prev == head),
//put non-null right node to a new right list(if curr.next == tail),
//finally iterate all lists from head to tail
