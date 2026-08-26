# sortsort · Run-Length Encoding
# Category: String
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/run-length-encoding

def rle_encode(text):
    if not text:
        return ""
    parts = []
    count = 1
    for i in range(1, len(text)):
        if text[i] == text[i - 1]:
            count += 1
        else:
            parts.append(text[i - 1] + str(count))
            count = 1
    parts.append(text[-1] + str(count))
    return "".join(parts)


def rle_decode(encoded):
    parts = []
    digits = ""
    for ch in encoded:
        if ch.isdigit():
            digits += ch
        else:
            parts.append(ch * int(digits))
            digits = ""
    return "".join(parts)


if __name__ == "__main__":
    original = "aaabbbcccdde"
    encoded = rle_encode(original)
    print("original:", original)
    print("encoded:", encoded)
    print("decoded:", rle_decode(encoded))
