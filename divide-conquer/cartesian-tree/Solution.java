// Stepsort · Cartesian Tree
// Category: Divide & Conquer
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/cartesian-tree

public class Main {
    public static void main(String[] args) {
        int[] values = {9, 3, 7, 1, 8, 12, 10, 20};
        int n = values.length;
        int[] parent = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        java.util.Arrays.fill(parent, -1);
        java.util.Arrays.fill(left, -1);
        java.util.Arrays.fill(right, -1);
        int[] stack = new int[n];
        int top = -1;
        for (int i = 0; i < n; i++) {
            int last = -1;
            while (top >= 0 && values[stack[top]] > values[i]) {
                last = stack[top--];
            }
            if (last != -1) {
                left[i] = last;
                parent[last] = i;
            }
            if (top >= 0) {
                parent[i] = stack[top];
                right[stack[top]] = i;
            }
            stack[++top] = i;
        }
        StringBuilder sb = new StringBuilder("parents:");
        for (int v = 0; v < n; v++) sb.append(" ").append(parent[v]);
        System.out.println(sb);
    }
}
