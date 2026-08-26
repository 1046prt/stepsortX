// sortsort · Suffix Automaton
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/suffix-automaton

import java.util.*;

public class Main {
    static class State {
        int len, link = -1;
        Map<Character, Integer> next = new HashMap<>();
        State(int len) { this.len = len; }
    }

    static List<State> st = new ArrayList<>(List.of(new State(0)));
    static int last = 0;

    static void extend(char c) {
        State curState = new State(st.get(last).len + 1);
        st.add(curState);
        int cur = st.size() - 1;
        int p = last;
        while (p != -1 && !st.get(p).next.containsKey(c)) {
            st.get(p).next.put(c, cur);
            p = st.get(p).link;
        }
        if (p == -1) {
            st.get(cur).link = 0;
        } else {
            int q = st.get(p).next.get(c);
            if (st.get(q).len == st.get(p).len + 1) {
                st.get(cur).link = q;
            } else {
                State clone = new State(st.get(p).len + 1);
                clone.next = new HashMap<>(st.get(q).next);
                clone.link = st.get(q).link;
                st.add(clone);
                while (p != -1 && st.get(p).next.get(c) == q) {
                    st.get(p).next.put(c, clone);
                    p = st.get(p).link;
                }
                st.get(q).link = clone;
                st.get(cur).link = clone;
            }
        }
        last = cur;
    }

    public static void main(String[] args) {
        for (char c : "abab".toCharArray()) extend(c);
        System.out.println("states: " + st.size());           // 6
        long total = 0;
        for (int i = 1; i < st.size(); i++)
            total += st.get(i).len - st.get(st.get(i).link).len;
        System.out.println("distinct substrings: " + total);  // 7
    }
}
