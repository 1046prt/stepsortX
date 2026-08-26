// Stepsort · Spiral Matrix
// Category: LeetCode Patterns
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/spiral-matrix

#include <bits/stdc++.h>
using namespace std;

vector<int> spiralOrder(vector<vector<int>>& matrix) {
    vector<int> result;
    int top = 0, bottom = matrix.size() - 1;
    int left = 0, right = matrix[0].size() - 1;
    while (top <= bottom && left <= right) {
        for (int c = left; c <= right; c++) result.push_back(matrix[top][c]);   // top row
        top++;
        for (int r = top; r <= bottom; r++) result.push_back(matrix[r][right]); // right col
        right--;
        if (top <= bottom) {  // bottom row, right to left
            for (int c = right; c >= left; c--) result.push_back(matrix[bottom][c]);
            bottom--;
        }
        if (left <= right) {  // left column, bottom to top
            for (int r = bottom; r >= top; r--) result.push_back(matrix[r][left]);
            left++;
        }
    }
    return result;
}

int main() {
    vector<vector<int>> matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    vector<int> order = spiralOrder(matrix);
    for (size_t i = 0; i < order.size(); i++) {
        cout << order[i];
        if (i + 1 < order.size()) cout << " ";
    }
    cout << endl;
    return 0;
}
