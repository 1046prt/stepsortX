// Stepsort · Rotate List
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-rotate

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

// Reduce k modulo the length, then cut the last k nodes off
// and splice them onto the front.
Node* rotateRight(Node* head, int k) {
    if (!head || !head->next || k == 0) return head;
    int length = 1;
    Node* tail = head;
    while (tail->next) {
        tail = tail->next;
        length++;
    }
    k %= length;
    if (k == 0) return head;
    Node* newTail = head;
    for (int i = 0; i < length - k - 1; i++) {
        newTail = newTail->next;
    }
    Node* newHead = newTail->next;
    newTail->next = nullptr;
    tail->next = head;
    return newHead;
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
    Node* head = build({1, 2, 3, 4, 5});
    head = rotateRight(head, 2);
    printList(head);
    head = rotateRight(head, 12);
    printList(head);
    return 0;
}
