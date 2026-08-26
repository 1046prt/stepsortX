// Stepsort · Remove Duplicates
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-remove-duplicates

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

// Sorted input keeps duplicates adjacent, so one pass suffices.
void removeDuplicates(Node* head) {
    Node* current = head;
    while (current && current->next) {
        if (current->val == current->next->val) {
            Node* duplicate = current->next;
            current->next = duplicate->next;
            delete duplicate;
        } else {
            current = current->next;
        }
    }
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
    Node* head = build({1, 2, 2, 3, 4, 4, 4, 5});
    printList(head);
    removeDuplicates(head);
    printList(head);
    return 0;
}
