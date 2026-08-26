// Stepsort · Multi-Constraint Digit DP
// Category: Dynamic Programming
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/multi-constraint-digit-dp

int countNumbers(long long L, long long R) {
    auto solve = [&](long long n) -> long long {
        if (n < 0) return 0;
        string s = to_string(n);
        int len = s.size();
        map<tuple<int,int,int,int,bool>,long long> memo;
        function<long long(int,int,int,int,bool)> dp =
            [&](int pos, int tight, int sumPar, int last, bool started) -> long long {
            if (pos == len) return started && sumPar == 0 ? 1 : 0;
            auto key = make_tuple(pos, tight, sumPar, last, started);
            if (memo.count(key)) return memo[key];
            int limit = tight ? s[pos] - '0' : 9;
            long long result = 0;
            for (int d = 0; d <= limit; d++) {
                int ntight = tight && (d == limit);
                bool nstarted = started || d > 0;
                int np = sumPar, nl = last;
                if (nstarted) {
                    np = (sumPar + d) % 2;
                    if (last != -1 && d == last) continue;
                    nl = d;
                }
                result += dp(pos+1, ntight, np, nl, nstarted);
            }
            return memo[key] = result;
        };
        return dp(0, 1, 0, -1, false);
    };
    return solve(R) - solve(L - 1);
}
