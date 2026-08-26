// sortsort · Booth's Algorithm
// Category: String
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/booth-minimal-rotation

static int booth(String s) {
    String ss = s + s;
    int n = s.length();
    int[] f = new int[ss.length()];
    Arrays.fill(f, -1);
    int k = 0;
    for (int j = 1; j < ss.length(); j++) {
        int i = f[j - k - 1];
        while (i != -1 && ss.charAt(j) != ss.charAt(k + i + 1)) {
            if (ss.charAt(j) < ss.charAt(k + i + 1)) k = j - i - 1;
            i = f[i];
        }
        if (i == -1 && ss.charAt(j) != ss.charAt(k)) {
            if (ss.charAt(j) < ss.charAt(k)) k = j;
            f[j - k] = -1;
        } else {
            f[j - k] = i + 1;
        }
    }
    return k;
}
