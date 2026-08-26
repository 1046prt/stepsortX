// sortsort · Find Intersection
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-intersection

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

int getLength(Node* head) {
    int count = 0;
    while (head) {
        count++;
        head = head->next;
    }
    return count;
}

// Length alignment: start both walks equally far from the shared tail.
Node* getIntersectionNode(Node* a, Node* b) {
    int lenA = getLength(a);
    int lenB = getLength(b);
    while (lenA > lenB) {
        a = a->next;
        lenA--;
    }
    while (lenB > lenA) {
        b = b->next;
        lenB--;
    }
    // Advance together until the two pointers meet (or both run out).
    while (a != b) {
        a = a->next;
        b = b->next;
    }
    return a;
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
    Node* shared = build({8, 10});
    Node* a = build({3, 7});
    Node* tailA = a;
    while (tailA->next) tailA = tailA->next;
    tailA->next = shared;             // A: 3 -> 7 -> 8 -> 10

    Node* b = build({99});
    b->next = shared;                 // B: 99 -> 8 -> 10

    Node* hit = getIntersectionNode(a, b);
    if (hit) {
        cout << "intersecting lists meet at: " << hit->val << endl;
    } else {
        cout << "intersecting lists do not meet" << endl;
    }

    Node* other = build({5, 6});
    Node* miss = getIntersectionNode(a, other);
    if (miss) {
        cout << "unexpected meeting at: " << miss->val << endl;
    } else {
        cout << "disjoint lists never meet" << endl;
    }
    return 0;
}
