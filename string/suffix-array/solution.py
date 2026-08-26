# sortsort · Suffix Array
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/suffix-array

def build_suffix_array(text):
    # Naive construction: sort suffix starting positions by direct comparison.
    return sorted(range(len(text)), key=lambda i: text[i:])


if __name__ == "__main__":
    text = "banana"
    sa = build_suffix_array(text)
    print("text:", text)
    print("suffix array:", sa)
    print("sorted suffixes:")
    for pos in sa:
        print(pos, "->", text[pos:])
