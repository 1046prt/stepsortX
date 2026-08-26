// sortsort · Merge K Sorted Lists
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/merge-k-sorted-lists

#include <bits/stdc++.h>
using namespace std;

struct ListNode {
    int val;
    ListNode* next;
    ListNode(int v) : val(v), next(nullptr) {}
};

ListNode* mergeTwo(ListNode* a, ListNode* b) {
    ListNode dummy(0);
    ListNode* tail = &dummy;
    while (a && b) {
        if (a->val <= b->val) { tail->next = a; a = a->next; }
        else { tail->next = b; b = b->next; }
        tail = tail->next;
    }
    tail->next = a ? a : b;
    return dummy.next;
}

ListNode* mergeKLists(vector<ListNode*>& lists) {
    if (lists.empty()) return nullptr;
    int n = lists.size();
    for (int interval = 1; interval < n; interval *= 2) {  // divide and conquer
        for (int i = 0; i + interval < n; i += interval * 2) {
            lists[i] = mergeTwo(lists[i], lists[i + interval]);
        }
    }
    return lists[0];
}

ListNode* buildList(initializer_list<int> vals) {
    ListNode dummy(0);
    ListNode* tail = &dummy;
    for (int v : vals) {
        tail->next = new ListNode(v);
        tail = tail->next;
    }
    return dummy.next;
}

void printList(ListNode* head) {
    while (head) {
        cout << head->val;
        if (head->next) cout << " -> ";
        head = head->next;
    }
    cout << endl;
}

int main() {
    vector<ListNode*> lists = {
        buildList({1, 4, 5}),
        buildList({1, 3, 4}),
        buildList({2, 6})
    };
    printList(mergeKLists(lists));
    return 0;
}
