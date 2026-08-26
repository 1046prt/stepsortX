# sortsort · Gale-Shapley (Stable Matching)
# Category: Network Flow & Matching
# Animated walkthrough: https://stepsort.prakashraj.me/algorithm/nf-stable-matching

def gale_shapley(men_pref, women_pref):
    """Men-proposing Gale-Shapley; preference lists are ordered best first."""
    n = len(men_pref)
    next_choice = [0] * n          # how far down his list each man has proposed
    fiance = [-1] * n              # woman -> man currently engaged to her
    free = list(range(n))          # stack of unmatched men
    while free:
        man = free.pop()
        woman = men_pref[man][next_choice[man]]
        next_choice[man] += 1
        rival = fiance[woman]
        if rival == -1:
            fiance[woman] = man
        elif women_pref[woman].index(man) < women_pref[woman].index(rival):
            fiance[woman] = man
            free.append(rival)
        else:
            free.append(man)
    return [fiance.index(man) for man in range(n)]  # man -> woman


def is_stable(men_pref, women_pref, partner):
    n = len(partner)
    for m in range(n):
        w = partner[m]
        for w2 in range(n):
            if w2 == w:
                continue
            m2 = partner.index(w2)
            man_prefers = men_pref[m].index(w2) < men_pref[m].index(w)
            woman_prefers = women_pref[w2].index(m) < women_pref[w2].index(m2)
            if man_prefers and woman_prefers:
                return False
    return True


if __name__ == "__main__":
    men_pref = [
        [0, 1, 2],
        [1, 0, 2],
        [0, 1, 2],
    ]
    women_pref = [
        [1, 0, 2],
        [0, 2, 1],
        [0, 1, 2],
    ]
    partner = gale_shapley(men_pref, women_pref)
    for m in range(len(partner)):
        print("Man", m, "engaged to Woman", partner[m])
    print("Matching is stable:", is_stable(men_pref, women_pref, partner))
