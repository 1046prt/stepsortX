// Stepsort · Palindrome Check
// Category: Linked List
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/ll-palindrome

#include <bits/stdc++.h>
using namespace std;

struct Node {
    int val;
    Node* next;
    Node(int v) : val(v), next(nullptr) {}
};

Node* reverseList(Node* head) {
    Node* prev = nullptr;
    Node* curr = head;
    while (curr != nullptr) {
        Node* next = curr->next;
        curr->next = prev;
        prev = curr;
        curr = next;
    }
    return prev;
}

bool isPalindrome(Node* head) {
    // Step 1: find the middle with slow/fast pointers.
    if (head == nullptr || head->next == nullptr) return true;
    Node* slow = head;
    Node* fast = head;
    while (fast->next != nullptr && fast->next->next != nullptr) {
        slow = slow->next;
        fast = fast->next->next;
    }

    // Step 2: reverse the second half.
    Node* second = reverseList(slow->next);

    // Step 3: compare the two halves.
    Node* p1 = head;
    Node* p2 = second;
    while (p2 != nullptr) {
        if (p1->val != p2->val) return false;
        p1 = p1->next;
        p2 = p2->next;
    }
    return true;
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
    vector<initializer_list<int>> tests = {
        {1, 2, 3, 2, 1},
        {1, 2, 2, 1},
        {1, 2, 3},
        {},
    };
    cout << boolalpha;
    for (auto& values : tests) {
        Node* head = buildList(values);
        cout << "is palindrome: " << isPalindrome(head) << endl;
    }
    return 0;
}
