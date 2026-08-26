// Stepsort · Nth from End
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-nth-from-end

#include <bits/stdc++.h>
using namespace std;

struct Node {
    int val;
    Node* next;
    Node(int v) : val(v), next(nullptr) {}
};

// Advance first n steps, then move both pointers together;
// when first leaves the list, second sits n from the end.
Node* nthFromEnd(Node* head, int n) {
    Node* first = head;
    for (int i = 0; i < n; i++) {
        if (first == nullptr) return nullptr;  // n exceeds the list length
        first = first->next;
    }
    Node* second = head;
    while (first != nullptr) {
        first = first->next;
        second = second->next;
    }
    return second;
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

int main() {
    Node* head = buildList({10, 20, 30, 40, 50});
    for (int n : {1, 3, 5, 6}) {
        Node* node = nthFromEnd(head, n);
        if (node != nullptr) {
            cout << n << "-th from end: " << node->val << endl;
        } else {
            cout << n << "-th from end: not found" << endl;
        }
    }
    return 0;
}
