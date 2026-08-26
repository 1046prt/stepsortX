# Stepsort · Course Schedule
# Category: LeetCode Patterns
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/course-schedule

from collections import deque


def can_finish(num_courses, prerequisites):
    # Kahn topological sort: possible iff no cycle remains
    graph = [[] for _ in range(num_courses)]
    indegree = [0] * num_courses
    for course, prereq in prerequisites:
        graph[prereq].append(course)
        indegree[course] += 1
    queue = deque(i for i in range(num_courses) if indegree[i] == 0)
    processed = 0
    while queue:
        node = queue.popleft()
        processed += 1
        for nxt in graph[node]:
            indegree[nxt] -= 1
            if indegree[nxt] == 0:
                queue.append(nxt)
    return processed == num_courses


if __name__ == "__main__":
    print(can_finish(2, [[1, 0]]))
    print(can_finish(2, [[1, 0], [0, 1]]))
    print(can_finish(4, [[1, 0], [2, 1], [3, 2]]))
