// Stepsort · Sort Linked List
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-sort

#include <bits/stdc++.h>
using namespace std;

struct Node {
    int val;
    Node* next;
    Node(int v) : val(v), next(nullptr) {}
};

Node* build(const vector<int>& values) {
    Node dummy(0);
    Node* tail = &dummy;
    for (int v : values) {
        tail->next = new Node(v);
        tail = tail->next;
    }
    return dummy.next;
}

// Slow/fast pointers: slow stops at the end of the first half.
Node* splitMiddle(Node* head) {
    Node* slow = head;
    Node* fast = head->next;
    while (fast && fast->next) {
        slow = slow->next;
        fast = fast->next->next;
    }
    return slow;
}

Node* mergeLists(Node* a, Node* b) {
    Node dummy(0);
    Node* tail = &dummy;
    while (a && b) {
        if (a->val <= b->val) {
            tail->next = a;
            a = a->next;
        } else {
            tail->next = b;
            b = b->next;
        }
        tail = tail->next;
    }
    tail->next = a ? a : b;
    return dummy.next;
}

Node* sortList(Node* head) {
    if (!head || !head->next) return head;
    Node* mid = splitMiddle(head);
    Node* rightHalf = mid->next;
    mid->next = nullptr;
    Node* left = sortList(head);
    Node* right = sortList(rightHalf);
    return mergeLists(left, right);
}

void printList(Node* head) {
    while (head) {
        cout << head->val;
        if (head->next) cout << " -> ";
        head = head->next;
    }
    cout << endl;
}

int main() {
    Node* head = build({5, 3, 8, 1, 9, 2, 7});
    printList(sortList(head));
    return 0;
}
