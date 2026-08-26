// Stepsort · Deletion
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-deletion

#include <bits/stdc++.h>
using namespace std;

struct Node {
    int val;
    Node* next;
    Node(int v) : val(v), next(nullptr) {}
};

// Remove the first node whose value equals target.
Node* deleteByValue(Node* head, int target) {
    if (head == nullptr) return nullptr;
    if (head->val == target) return head->next;
    Node* curr = head;
    while (curr->next != nullptr && curr->next->val != target) {
        curr = curr->next;
    }
    if (curr->next != nullptr) curr->next = curr->next->next;
    return head;
}

// Remove the node at the given 0-based index.
Node* deleteAtPosition(Node* head, int pos) {
    if (head == nullptr) return nullptr;
    if (pos == 0) return head->next;
    Node* curr = head;
    for (int i = 0; i < pos - 1; i++) {
        curr = curr->next;
        if (curr == nullptr) return head;  // position out of range: ignore
    }
    if (curr->next != nullptr) curr->next = curr->next->next;
    return head;
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
    Node* head = buildList({4, 2, 6, 2, 9});
    cout << "start: ";
    printList(head);

    head = deleteByValue(head, 2);
    cout << "delete value 2: ";
    printList(head);

    head = deleteByValue(head, 4);
    cout << "delete value 4: ";
    printList(head);

    head = deleteAtPosition(head, 1);
    cout << "delete index 1: ";
    printList(head);

    head = deleteAtPosition(head, 0);
    cout << "delete index 0: ";
    printList(head);
    return 0;
}
