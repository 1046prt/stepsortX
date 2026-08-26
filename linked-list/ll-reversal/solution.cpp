// sortsort · Reversal
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-reversal

#include <bits/stdc++.h>
using namespace std;

struct Node {
    int val;
    Node* next;
    Node(int v) : val(v), next(nullptr) {}
};

// Walk the list flipping each next pointer backwards.
Node* reverseList(Node* head) {
    Node* prev = nullptr;
    Node* curr = head;
    while (curr != nullptr) {
        Node* next = curr->next;  // save the rest of the list
        curr->next = prev;        // flip one pointer
        prev = curr;              // advance prev
        curr = next;              // advance curr
    }
    return prev;
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
    Node* head = buildList({1, 2, 3, 4, 5});
    cout << "before: ";
    printList(head);
    head = reverseList(head);
    cout << "after:  ";
    printList(head);
    return 0;
}
