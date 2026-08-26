// sortsort · LRU Cache
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lru-cache

#include <bits/stdc++.h>
using namespace std;

struct Node {
    int key, value;
    Node* prev;
    Node* next;
    Node(int k = 0, int v = 0) : key(k), value(v), prev(nullptr), next(nullptr) {}
};

class LRUCache {
    int capacity;
    unordered_map<int, Node*> map;
    Node* head;  // most recent side
    Node* tail;  // least recent side

    void remove(Node* node) {
        node->prev->next = node->next;
        node->next->prev = node->prev;
    }

    void pushFront(Node* node) {
        node->next = head->next;
        node->prev = head;
        head->next->prev = node;
        head->next = node;
    }

public:
    explicit LRUCache(int cap) : capacity(cap) {
        head = new Node();
        tail = new Node();
        head->next = tail;
        tail->prev = head;
    }

    int get(int key) {
        auto it = map.find(key);
        if (it == map.end()) return -1;
        Node* node = it->second;
        remove(node);
        pushFront(node);
        return node->value;
    }

    void put(int key, int value) {
        auto it = map.find(key);
        if (it != map.end()) {
            Node* node = it->second;
            node->value = value;
            remove(node);
            pushFront(node);
            return;
        }
        if ((int)map.size() >= capacity) {
            Node* lru = tail->prev;
            cout << "  evict key " << lru->key << endl;
            remove(lru);
            map.erase(lru->key);
            delete lru;
        }
        Node* node = new Node(key, value);
        map[key] = node;
        pushFront(node);
    }
};

int main() {
    LRUCache cache(2);
    cache.put(1, 100);
    cache.put(2, 200);
    cout << "get 1 -> " << cache.get(1) << endl;
    cache.put(3, 300);  // evicts key 2
    cout << "get 2 -> " << cache.get(2) << " (was evicted)" << endl;
    cache.put(4, 400);  // evicts key 1
    cout << "get 1 -> " << cache.get(1) << " (was evicted)" << endl;
    cout << "get 3 -> " << cache.get(3) << endl;
    return 0;
}
