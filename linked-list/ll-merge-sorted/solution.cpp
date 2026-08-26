// sortsort · Merge Two Sorted Lists
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-merge-sorted

#include <bits/stdc++.h>
using namespace std;

struct Node {
    int val;
    Node* next;
    Node(int v) : val(v), next(nullptr) {}
};

// Attach the smaller front node to a dummy tail each round.
Node* mergeSortedLists(Node* a, Node* b) {
    Node dummy(0);
    Node* tail = &dummy;
    while (a != nullptr && b != nullptr) {
        if (a->val <= b->val) { tail->next = a; a = a->next; }
        else { tail->next = b; b = b->next; }
        tail = tail->next;
    }
    tail->next = (a != nullptr) ? a : b;
    return dummy.next;
}

Node* buildList(initializer_list<int> values) {
    Node* head = nullptr;
    Node* tail = nullptr;
    for (int v : values) {
        Node* node = new Node(v);
        if (head == nullptr) { head = node; tail = node; }
        else { tail->next = node; tail = node; }
    }
    return head;
}

void printList(Node* head) {
    if (head == nullptr) {
        cout << "(empty)" << endl;
        return;
    }
    for (Node* curr = head; curr != nullptr; curr = curr->next) {
        cout << curr->val;
        if (curr->next != nullptr) cout << " -> ";
    }
    cout << endl;
}

int main() {
    Node* a = buildList({1, 3, 5, 7});
    Node* b = buildList({2, 4, 6, 8});
    Node* merged = mergeSortedLists(a, b);
    cout << "list a: ";
    printList(a);
    cout << "list b: ";
    printList(b);
    cout << "merged: ";
    printList(merged);
    return 0;
}
