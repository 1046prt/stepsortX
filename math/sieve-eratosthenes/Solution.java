// Stepsort · Sieve of Eratosthenes
// Category: Math & Number Theory
// Animated walkthrough: https://stepsort.prakashraj.me/algorithm/sieve-eratosthenes

import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Integer> sieveOfEratosthenes(int limit) {
        List<Integer> primes = new ArrayList<>();
        if (limit < 2) return primes;
        boolean[] isComposite = new boolean[limit + 1];
        for (int p = 2; p * p <= limit; ++p) {
            if (!isComposite[p]) {
                for (int multiple = p * p; multiple <= limit; multiple += p) {
                    isComposite[multiple] = true;
                }
            }
        }
        for (int i = 2; i <= limit; ++i) {
            if (!isComposite[i]) primes.add(i);
        }
        return primes;
    }

    public static void main(String[] args) {
        List<Integer> primes = sieveOfEratosthenes(50);
        System.out.println("Primes up to 50: " + primes);
        System.out.println("Count: " + primes.size());
        System.out.println("Largest: " + primes.get(primes.size() - 1));
    }
}
