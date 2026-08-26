// sortsort · Booth's Algorithm
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/booth-minimal-rotation

int booth(string s) {
    s += s;
    int n = s.size() / 2;
    vector<int> f(s.size(), -1);
    int k = 0;
    for (int j = 1; j < (int)s.size(); j++) {
        int i = f[j - k - 1];
        while (i != -1 && s[j] != s[k + i + 1]) {
            if (s[j] < s[k + i + 1]) k = j - i - 1;
            i = f[i];
        }
        if (i == -1 && s[j] != s[k]) {
            if (s[j] < s[k]) k = j;
            f[j - k] = -1;
        } else {
            f[j - k] = i + 1;
        }
    }
    return k;
}

// usage: string s = "cdefab"; int pos = booth(s);
// minimal rotation = s.substr(pos) + s.substr(0, pos);
