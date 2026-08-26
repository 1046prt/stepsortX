# sortsort · Heap Extract Max
# Category: Tree
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/heap-extract

# Max-heap extract-max: swap root with last, pop, sift down

def sift_down(a, i, size):
    # push a[i] down until it dominates both children
    while True:
        largest = i
        left, right = 2 * i + 1, 2 * i + 2
        if left < size and a[left] > a[largest]:
            largest = left
        if right < size and a[right] > a[largest]:
            largest = right
        if largest == i:
            return
        a[i], a[largest] = a[largest], a[i]
        i = largest


def build_heap(a):
    for i in range(len(a) // 2 - 1, -1, -1):
        sift_down(a, i, len(a))


def extract_max(a):
    # swap root with last, shrink, then sift the new root down
    top = a[0]
    a[0] = a[-1]
    a.pop()
    if a:
        sift_down(a, 0, len(a))
    return top


if __name__ == "__main__":
    data = [9, 4, 7, 1, 8, 20, 15, 3]
    build_heap(data)
    print("heap after build:", data)
    ordered = []
    while data:
        ordered.append(extract_max(data))
    print("extracted in descending order:", ordered)
