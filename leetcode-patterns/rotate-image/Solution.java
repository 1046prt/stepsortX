// Stepsort · Rotate Image
// Category: Arrays & Stacks
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/rotate-image

import java.util.Arrays;

public class RotateImage {
    static void rotate(int[][] matrix) {
        int n = matrix.length;
        // Transpose
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                int t = matrix[i][j]; matrix[i][j] = matrix[j][i]; matrix[j][i] = t;
            }
        // Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int t = matrix[i][left]; matrix[i][left] = matrix[i][right]; matrix[i][right] = t;
                left++; right--;
            }
        }
    }

    public static void main(String[] args) {
        int[][] m = {{1,2,3},{4,5,6},{7,8,9}};
        rotate(m);
        for (int[] row : m) System.out.println(Arrays.toString(row));
    }
}
