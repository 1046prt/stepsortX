// Stepsort · Copy with Random Pointer
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-copy-random

#include <bits/stdc++.h>
using namespace std;

struct Node {
    int val;
    Node* next;
    Node* random;
    Node(int v) : val(v), next(nullptr), random(nullptr) {}
};

// Deep copy using interleaving, O(1) extra space beyond the clones.
Node* copyRandomList(Node* head) {
    if (!head) return nullptr;

    // Insert a clone right after every original node.
    for (Node* cur = head; cur; cur = cur->next->next) {
        Node* clone = new Node(cur->val);
        clone->next = cur->next;
        cur->next = clone;
    }

    // Aim each clone's random at the clone of its target.
    for (Node* cur = head; cur; cur = cur->next->next) {
        if (cur->random) {
            cur->next->random = cur->random->next;
        }
    }

    // Unweave the interleaved original and cloned chains apart.
    Node dummy(0);
    Node* tail = &dummy;
    for (Node* cur = head; cur; cur = cur->next) {
        Node* clone = cur->next;
        cur->next = clone->next;
        tail->next = clone;
        tail = clone;
    }

    return dummy.next;
}

void printList(Node* head) {
    while (head) {
        cout << "(" << head->val << ", random=";
        if (head->random) {
            cout << head->random->val;
        } else {
            cout << "null";
        }
        cout << ")";
        if (head->next) cout << " -> ";
        head = head->next;
    }
    cout << endl;
}

int main() {
    Node* a = new Node(1);
    Node* b = new Node(2);
    Node* c = new Node(3);
    a->next = b;
    b->next = c;
    a->random = c;
    b->random = a;
    c->random = b;

    Node* copied = copyRandomList(a);
    printList(copied);
    return 0;
}
