// Stepsort · LRU Cache
// Category: Hashing
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/lru-cache

import java.util.HashMap;

public class Main {
    static class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) { this.key = key; this.value = value; }
    }

    static class LRUCache {
        final int capacity;
        final HashMap<Integer, Node> map = new HashMap<>();
        final Node head = new Node(0, 0); // most recent side
        final Node tail = new Node(0, 0); // least recent side

        LRUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        void pushFront(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        int get(int key) {
            Node node = map.get(key);
            if (node == null) return -1;
            remove(node);
            pushFront(node);
            return node.value;
        }

        void put(int key, int value) {
            Node node = map.get(key);
            if (node != null) {
                node.value = value;
                remove(node);
                pushFront(node);
                return;
            }
            if (map.size() >= capacity) {
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
                System.out.println("  evict key " + lru.key);
            }
            Node fresh = new Node(key, value);
            map.put(key, fresh);
            pushFront(fresh);
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 100);
        cache.put(2, 200);
        System.out.println("get 1 -> " + cache.get(1));
        cache.put(3, 300); // evicts key 2
        System.out.println("get 2 -> " + cache.get(2) + " (was evicted)");
        cache.put(4, 400); // evicts key 1
        System.out.println("get 1 -> " + cache.get(1) + " (was evicted)");
        System.out.println("get 3 -> " + cache.get(3));
    }
}
