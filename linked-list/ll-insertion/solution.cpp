// sortsort · Insertion
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-insertion

#include <bits/stdc++.h>
using namespace std;

struct Node {
    int val;
    Node* next;
    Node(int v) : val(v), next(nullptr) {}
};

Node* insertAtHead(Node* head, int val) {
    Node* node = new Node(val);
    node->next = head;
    return node;
}

Node* insertAtTail(Node* head, int val) {
    Node* node = new Node(val);
    if (head == nullptr) return node;
    Node* curr = head;
    while (curr->next != nullptr) curr = curr->next;
    curr->next = node;
    return head;
}

Node* insertAtPosition(Node* head, int pos, int val) {
    if (pos <= 0) return insertAtHead(head, val);
    Node* curr = head;
    for (int i = 0; i < pos - 1 && curr != nullptr; i++) curr = curr->next;
    if (curr == nullptr) return head;  // position out of range: ignore
    Node* node = new Node(val);
    node->next = curr->next;
    curr->next = node;
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
    Node* head = nullptr;
    head = insertAtHead(head, 3);
    cout << "insert 3 at head: ";
    printList(head);
    head = insertAtHead(head, 1);
    cout << "insert 1 at head: ";
    printList(head);
    head = insertAtTail(head, 7);
    cout << "insert 7 at tail: ";
    printList(head);
    head = insertAtPosition(head, 2, 5);
    cout << "insert 5 at index 2: ";
    printList(head);
    head = insertAtPosition(head, 0, 0);
    cout << "insert 0 at index 0: ";
    printList(head);
    return 0;
}
