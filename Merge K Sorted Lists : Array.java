Merge K Sorted Lists / Array
23. Merge k Sorted Lists

Recursion: 
Time: O(NlogK), Space O(1)
N: total number of nodes in two lists. K: number of lists
merge list O(n), logK level, in place sort

ListNode* mergeKLists(vector<ListNode*>& lists) {
    return partition(lists, 0, lists.size() - 1);
}
ListNode* partition(vector<ListNode*>& lists, int left, int right) {
    if (left == right) {
        return lists[left];
    }
    if (left < right) {
        int mid = (left + right) / 2;
        ListNode* left_half = partition(lists, left, mid);
        ListNode* right_half = partition(lists, mid + 1, right);
        return merge(left_half, right_half);
    }
    return NULL;
}
ListNode* merge(ListNode* left_half, ListNode* right_half) {
    if (!left_half) return right_half;
    if (!right_half) return left_half;

    if (left_half->val < right_half->val) {
        left_half->next = merge(left_half->next, right_half);
        return left_half;
    } else {
        right_half->next = merge(right_half->next, left_half);
        return right_half;
    }
}

Priority Queue

Time O(NlogK), Space O(K)

struct compare {
    bool operator() (ListNode* l1, ListNode* l2) {
        return l1->val > l2->val;
    }
};

ListNode* mergeKLists(vector<ListNode*>& lists) {
    // careful! check NULL
    if (lists.size() == 0) return NULL;
    priority_queue<ListNode*, vector<ListNode*>, compare> pq;
    for (int i = 0; i < lists.size(); i++) {
         // careful! check NULL
        if (lists[i]) pq.push(lists[i]);
    }
    ListNode* head = new ListNode(0);
    ListNode* curr = head;
    while (!pq.empty()) {
        ListNode* min = pq.top();
        pq.pop();
        curr->next = min;
        curr = curr->next;
        // careful! check NULL
        if (min->next) {
            pq.push(min->next);
        }
    }
    return head->next;
}

21. Merge Two Sorted Lists
Recursion:
Time O(n + m), Space O(1)
    
ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
    if (!l1) return l2;
    if (!l2) return l1;
    if (l1->val < l2->val) {
        l1->next = mergeTwoLists(l1->next, l2);
        return l1;
    } else {
        l2->next = mergeTwoLists(l2->next, l1);
        return l2;
    }
}

Iteration
Time O(n + m), Space O(1)
ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
    ListNode* p1 = l1;
    ListNode* p2 = l2;
    ListNode* dummy = new ListNode(0);
    ListNode* curr = dummy;
    while (p1 || p2) {
        if (!p1 && !p2) break;
        if (!p1) {
            curr->next = p2;
            break;
        }
        if (!p2) {
            curr->next = p1;
            break;
        }
        if (p1->val < p2->val) {
            curr->next = p1;
            p1 = p1->next;
        } else {
            curr->next = p2;
            p2 = p2->next;
        }
        curr = curr->next;
    }
    return dummy->next;
}
88. Merge Sorted Array
Two pointer
Time: O(n+m), Space O(1)
void merge(vector<int>& nums1, int m, vector<int>& nums2, int n) {
    int idx = m + n - 1;
    int idx1 = m - 1;
    int idx2 = n - 1;
    while (idx2 >= 0) {
        if (idx1 >= 0 && nums1[idx1] > nums2[idx2]) {
            nums1[idx] = nums1[idx1];
            idx1--;
        } else {
            nums1[idx] = nums2[idx2];
            idx2--;
        }
        idx--;
    }
}


88. Merge Sorted Array
// https://leetcode.com/problems/merge-sorted-array/
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
Note: You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
The number of elements initialized in nums1 and nums2 are m and n respectively.

public void merge(int[] nums1, int m, int[] nums2, int n) {
    int k = m + n - 1, i = m - 1, j = n - 1;
    while (i >= 0 && j >= 0) {
        if (nums1[i] >= nums2[j])    nums1[k--] = nums1[i--];
        else    nums1[k--] = nums2[j--];
    }
    while (j >= 0)   nums1[k--] = nums2[j--];
}



21. Merge Two Sorted Lists
//classic iterate
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);//dummy
    ListNode p = dummy;
    while (l1 != null && l2 != null) {
        if (l1.val < l2.val) {
            p.next = l1;
            l1 = l1.next;
        } else {
            p.next = l2;
            l2 = l2.next;
        }
        p = p.next;
    }
    if (l1 != null)    p.next = l1;
    if (l2 != null)    p.next = l2;
    return dummy.next;
} 

//recursive
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {      
    if (l1 == null)     return l2;
    if (l2 == null)     return l1;
    if (l1.val < l2.val) {
        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
    } else {
        l2.next = mergeTwoLists(l2.next, l1);
        return l2;
    }
}



23. Merge k Sorted Lists
// https://leetcode.com/problems/merge-k-sorted-lists/

Solution 1: PriorityQueue 
Time: O(nlogk), Space: O(k)

public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) return null;
    PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>(){
        public int compare(ListNode n1, ListNode n2) {
            return n1.val - n2.val;
        }
    });
    for (ListNode node : lists) 
        if (node != null)   pq.offer(node);
    ListNode dummy = new ListNode(0), tail = dummy;
    while (!pq.isEmpty()) {
        tail.next = pq.poll();
        tail = tail.next;
        if (tail.next != null)  pq.offer(tail.next);
    }
    return dummy.next;
}


Solution 2: K-Merge Sort 
Time: O(nlogk), Space: O(1)
 
public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0)    return null;
    return mergeKLists(lists, 0, lists.length - 1);
}
private ListNode mergeKLists(ListNode[] lists, int start, int end) {
    if (start == end) 
        return lists[start];
    if (start < end){
        int mid = (end - start) / 2 + start;
        ListNode left = mergeKLists(lists, start, mid);
        ListNode right = mergeKLists(lists, mid + 1, end);
        return mergeTwoLists(left, right);
    }
    return null;
}


// public ListNode mergeKLists(List<ListNode> lists) {
//     if (lists.size() == 0)  return null;
//     if (lists.size() == 1)  return lists.get(0);
//     if (lists.size() == 2)  return mergeTwoLists(lists.get(0), lists.get(1));
//     return mergeTwoLists(mergeKLists(lists.subList(0, lists.size() / 2)), 
//     	mergeKLists(lists.subList(lists.size() / 2, lists.size())));
// }

记住 list.subList(int fromIndex, int toIndex) 方法

