133. Clone Graph
// https://leetcode.com/problems/clone-graph/

Deep Copy

public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
	Map<Integer, UndirectedGraphNode> map = new HashMap<>();
	return dfsClone(node, map);
}
public UndirectedGraphNode dfsClone(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> map) {
	if (node == null)	return null;
	if (map.containsKey(node.label))	return map.get(node.label);
	UndirectedGraphNode cloneNode = new UndirectedGraphNode(node.label);
	map.put(cloneNode.label, cloneNode);
	for (UndirectedGraphNode n : node.neighbors) 
		cloneNode.neighbors.add(dfsClone(n, map));
	return cloneNode;
}

如果图不连通，定义hashset，set<node> 每deep copy完一个，就remove
Given a reference of a node in a connected undirected graph, 
return a deep copy (clone) of the graph. Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
C++ Recursive Solution
```
public:
Node* cloneGraph(Node* node) {
	if (!node) return node;
	if (cache.find(node) != cache.end()) return cache[node];
	Node* root = new Node(node->val, vector<Node*>(node->neighbors.size(), NULL));
	cache[root] = node;
	for (int i = 0; i < node->neighbors.size(); i++) {
		if (cache.find(node->neighbors[i]) != cache.end()) {
			root->neighbors[i] = cache[node->neighbors[i]];
		} else {
			root->neighbors[i] = cloneGraph(node->neighbors[i]);
		}
	}
	return root;
}
private:
unordered_map<Node*, Node*> cache;
```
What if the graph is not connected?
public:
Node* cloneGraphHelper(Node* node) {
	if (!node) return node;
	if (cache.find(node) != cache.end()) return cache[node];
	//erase from st
	st.erase(node);
	Node* root = new Node(node->val, vector<Node*>(node->neighbors.size(), NULL));
	cache[root] = node;
	for (int i = 0; i < node->neighbors.size(); i++) {
		if (cache.find(node->neighbors[i]) != cache.end()) {
			root->neighbors[i] = cache[node->neighbors[i]];
		} else {
			root->neighbors[i] = cloneGraph(node->neighbors[i]);
		}
	}
	return root;
}
Node* cloneGraph(Node* node) {
	putNodeIntoSet(node);
	while (!st.empty()) {
		cloneGraphHelper(*(st.begin()));
	}
	return cache[node];
}
void putNodeIntoSet(Node* node) {
	st.insert(node);
	for (int i = 0; i < node->neighbors.size(); i++) {
		st.insert(node->neighbors[i]);
		putNodeIntoSet(node->neighbors[i]);
	}
}
private:
unordered_set<Node*> st;
unordered_map<Node*, Node*> cache;

