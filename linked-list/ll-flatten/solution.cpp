// Stepsort · Flatten Multi-Level List
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-flatten

#include <bits/stdc++.h>
using namespace std;

struct Node {
    int val;
    Node* next;
    Node* down;
    Node(int v, Node* n = nullptr, Node* d = nullptr)
        : val(v), next(n), down(d) {}
};

// Depth-first: finish a whole child chain before visiting its sibling.
Node* flatten(Node* head) {
    if (!head) return nullptr;
    stack<Node*> st;
    st.push(head);
    Node dummy(0);
    Node* tail = &dummy;
    while (!st.empty()) {
        Node* node = st.top();
        st.pop();
        tail->next = node;
        tail = node;
        if (node->next) st.push(node->next);
        if (node->down) st.push(node->down);
        node->next = nullptr;
        node->down = nullptr;
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
    Node* n7 = new Node(7);
    Node* n6 = new Node(6, nullptr, n7);
    Node* n5 = new Node(5, nullptr, n6);
    Node* n8 = new Node(8);
    Node* n4 = new Node(4, nullptr, n8);
    Node* n3 = new Node(3);
    Node* n2 = new Node(2, n3, n5);
    Node* n1 = new Node(1, n2);

    printList(flatten(n1));
    return 0;
}
