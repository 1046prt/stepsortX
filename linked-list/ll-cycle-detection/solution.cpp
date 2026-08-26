// Stepsort · Cycle Detection
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-cycle-detection

#include <bits/stdc++.h>
using namespace std;

struct Node {
    int val;
    Node* next;
    Node(int v) : val(v), next(nullptr) {}
};

// Slow moves 1 step, fast moves 2 steps.
// They meet iff the list contains a cycle.
bool hasCycle(Node* head) {
    Node* slow = head;
    Node* fast = head;
    while (fast != nullptr && fast->next != nullptr) {
        slow = slow->next;
        fast = fast->next->next;
        if (slow == fast) return true;
    }
    return false;
}

// After the pointers meet, restart one at the head;
// advancing both 1 step meets again at the cycle entry.
Node* findCycleStart(Node* head) {
    Node* slow = head;
    Node* fast = head;
    while (fast != nullptr && fast->next != nullptr) {
        slow = slow->next;
        fast = fast->next->next;
        if (slow == fast) {
            slow = head;
            while (slow != fast) {
                slow = slow->next;
                fast = fast->next;
            }
            return slow;
        }
    }
    return nullptr;
}

int main() {
    Node* a = new Node(1);
    Node* b = new Node(2);
    Node* c = new Node(3);
    Node* d = new Node(4);
    Node* e = new Node(5);
    a->next = b;
    b->next = c;
    c->next = d;
    d->next = e;

    cout << boolalpha;
    cout << "plain list has cycle: " << hasCycle(a) << endl;

    e->next = b;  // tail links back to value 2
    cout << "linked tail has cycle: " << hasCycle(a) << endl;
    Node* start = findCycleStart(a);
    if (start != nullptr) cout << "cycle starts at value: " << start->val << endl;
    else cout << "cycle starts at value: none" << endl;

    e->next = nullptr;  // break the cycle again
    cout << "after breaking, has cycle: " << hasCycle(a) << endl;
    return 0;
}
