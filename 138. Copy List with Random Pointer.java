138. Copy List with Random Pointer

C++
Solution 1: unordered_map and recursion
Time O(n), Space O(n)
```
Node* copyRandomList(Node* head) {
        if (!head) return head;
        if (cache.find(head) != cache.end()) return cache[head];
        Node* root = new Node(head->val, NULL, NULL);
        cache[head] = root;
        root->next = copyRandomList(head->next);
        root->random = copyRandomList(head->random);
        return root;
}
unordered_map<Node*, Node*> cache;
```

Solution 2: Iterative
Time O(n), Space O(1)
1. Copy every node and link them together 
original 1->2->4
change to 1->1->2->2->4->4
The front one is the original one and the later one is the copy.
2. Copy the random pointers
3. Split two list
```
Node* copyRandomList(Node* head) {
    Node* curr = head;
    while (curr) {
        Node* newNode = new Node(curr->val, NULL, NULL);
        newNode->next = curr->next;
        curr->next = newNode;
        curr = newNode->next;
    }
    curr = head;
    while (curr) {
        curr->next->random = (curr->random) : curr->random->next : NULL;
        curr = curr->next->next;
    }
    curr = head;
    Node* currRet = head->next;
    Node* ret = head->next;
    while (curr) {
        curr->next = curr->next->next;
        currRet->next = (currRet->next) ? currRet->next->next : NULL;
        curr = curr->next;
        currRet = currRet->next;
    }
    return ret;
}
```
Java

Solution 1: hashmap + 2 iteration
第一遍deep copy每个node并存hashmap，第二遍根据hashmap来deep copy random指针（注意条件：cur.random != null）

public RandomListNode copyRandomList(RandomListNode head) {
    RandomListNode dummy = new RandomListNode(0), cur = dummy;
    Map<RandomListNode, RandomListNode> map = new HashMap<>();
    while (head != null) { 
        RandomListNode newNode = new RandomListNode(head.label);
        map.put(head, newNode);
        newNode.random = head.random;
        cur.next = newNode;
        cur = cur.next;
        head = head.next;
    }
    cur = dummy.next;
    while (cur != null) {
        if (cur.random != null)
            cur.random = map.get(cur.random);
        cur = cur.next;
    }
    return dummy.next;
}


Solution 2: hashmap + 1 iteration

public RandomListNode copyRandomList(RandomListNode head) {
    RandomListNode dummy = new RandomListNode(0), cur = dummy;
    Map<RandomListNode, RandomListNode> map = new HashMap<>();
    while (head != null) {
        RandomListNode newNode = null;
        if (map.containsKey(head))
            newNode = map.get(head);
        else {
            newNode = new RandomListNode(head.label);
            map.put(head, newNode);
        }
        if (head.random != null) // ATTENTION
            if (map.containsKey(head.random))
                newNode.random = map.get(head.random);
            else {
                newNode.random = new RandomListNode(head.random.label);
                map.put(head.random, newNode.random);
            }
        cur.next = newNode;
        cur = cur.next;
        head = head.next;
    }
    return dummy.next;
}


Solution 3: copy -> random -> split 
Space： O(1)

public RandomListNode copyRandomList(RandomListNode head) {
    if (head == null) return null;
    RandomListNode cur = head;
    while (cur != null) {
        RandomListNode newNode = new RandomListNode(cur.label);
        newNode.next = cur.next;
        cur.next = newNode;
        cur = cur.next.next;
    }
    cur = head;
    while (cur != null) {
        if (cur.random != null)
            cur.next.random = cur.random.next;
        cur = cur.next.next;
    }
    cur = head;
    RandomListNode newHead = head.next;
    while (cur != null) {
        RandomListNode newNode = cur.next;
        cur.next = newNode.next;
        cur = cur.next;
        if (cur != null)    newNode.next = cur.next;
    }
    return newHead;
}


