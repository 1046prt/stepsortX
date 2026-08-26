// Stepsort · Add Two Numbers
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-add-numbers

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

// Digits are least significant first, so add pairwise with carry.
Node* addTwoNumbers(Node* l1, Node* l2) {
    Node dummy(0);
    Node* tail = &dummy;
    int carry = 0;
    while (l1 || l2 || carry) {
        int total = carry;
        if (l1) {
            total += l1->val;
            l1 = l1->next;
        }
        if (l2) {
            total += l2->val;
            l2 = l2->next;
        }
        carry = total / 10;
        tail->next = new Node(total % 10);
        tail = tail->next;
    }
    return dummy.next;
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
    Node* a = build({2, 4, 3});     // 342
    Node* b = build({5, 6, 4});     // 465
    printList(addTwoNumbers(a, b));

    Node* c = build({9, 9, 9, 9});  // 9999
    Node* d = build({1});           // 1
    printList(addTwoNumbers(c, d));
    return 0;
}
