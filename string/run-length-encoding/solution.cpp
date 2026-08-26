// Stepsort · Run-Length Encoding
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/run-length-encoding

#include <bits/stdc++.h>
using namespace std;

string rleEncode(const string& text) {
    if (text.empty()) return "";
    string result;
    int count = 1;
    for (size_t i = 1; i <= text.size(); i++) {
        if (i < text.size() && text[i] == text[i - 1]) {
            count++;
        } else {
            result += text[i - 1];
            result += to_string(count);
            count = 1;
        }
    }
    return result;
}

string rleDecode(const string& encoded) {
    string result, digits;
    for (char ch : encoded) {
        if (isdigit((unsigned char)ch)) {
            digits += ch;
        } else {
            result.append(stoi(digits), ch);
            digits.clear();
        }
    }
    return result;
}

int main() {
    string original = "aaabbbcccdde";
    string encoded = rleEncode(original);
    cout << "original: " << original << endl;
    cout << "encoded: " << encoded << endl;
    cout << "decoded: " << rleDecode(encoded) << endl;
    return 0;
}
