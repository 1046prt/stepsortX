// Stepsort · Linked List Cycle
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/linked-list-cycle

#include <bits/stdc++.h>
using namespace std;

struct ListNode {
    int val;
    ListNode* next;
    ListNode(int v) : val(v), next(nullptr) {}
};

bool hasCycle(ListNode* head) {
    ListNode* slow = head;
    ListNode* fast = head;
    while (fast && fast->next) {
        slow = slow->next;
        fast = fast->next->next;
        if (slow == fast) return true;
    }
    return false;
}

ListNode* build(const vector<int>& values, int pos) {
    // pos = index the tail points back to, or -1 for no cycle.
    vector<ListNode*> nodes;
    for (int v : values) nodes.push_back(new ListNode(v));
    for (size_t i = 0; i + 1 < nodes.size(); i++) nodes[i]->next = nodes[i + 1];
    if (!nodes.empty() && pos >= 0) nodes.back()->next = nodes[pos];
    return nodes.empty() ? nullptr : nodes[0];
}

int main() {
    cout << boolalpha << hasCycle(build({1, 2, 3, 4}, 1)) << endl;
    cout << hasCycle(build({1, 2, 3, 4}, -1)) << endl;
    return 0;
}
