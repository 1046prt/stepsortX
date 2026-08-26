// Stepsort · Bloom Filter
// Category: Randomized Algorithms
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rand-bloom-filter

#include <bits/stdc++.h>
using namespace std;

class BloomFilter {
    vector<bool> bits;

    // Two independent-ish hash functions over the bit array size.
    static size_t h1(const string& s, size_t size) {
        unsigned long h = 0;
        for (char c : s) h = (h * 31 + static_cast<unsigned char>(c)) % size;
        return h;
    }

    static size_t h2(const string& s, size_t size) {
        unsigned long h = 5381;
        for (char c : s) h = (h * 33 + static_cast<unsigned char>(c)) % size;
        return h;
    }

  public:
    explicit BloomFilter(size_t size) : bits(size, false) {}

    void add(const string& item) {
        bits[h1(item, bits.size())] = true;
        bits[h2(item, bits.size())] = true;
    }

    bool possiblyContains(const string& item) const {
        return bits[h1(item, bits.size())] && bits[h2(item, bits.size())];
    }
};

int main() {
    BloomFilter filter(64);
    for (string word : {"apple", "banana", "cherry"}) filter.add(word);
    cout << boolalpha;
    cout << "apple inserted -> present? " << filter.possiblyContains("apple") << endl;
    for (string word : {"date", "fig", "grape", "kiwi"}) {
        if (filter.possiblyContains(word)) {
            cout << word << " -> false positive (never inserted)" << endl;
        } else {
            cout << word << " -> definitely absent" << endl;
        }
    }
    return 0;
}
