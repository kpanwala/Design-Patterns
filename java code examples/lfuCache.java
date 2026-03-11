import java.util.*;

class Main {
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);

        cache.put(1, 10);
        cache.put(2, 20);
        cache.get(1); // Key 1 freq: 2, Key 2 freq: 1

        cache.put(3, 30); // Evicts Key 2 (freq 1)
        cache.get(2); 
        cache.get(3); // Key 3 freq: 2, Key 1 freq: 2

        cache.get(3); // Key 3 freq: 3
        cache.put(4, 40); // Evicts Key 1 (freq 2 is lowest)

        cache.get(1); 
        cache.get(4); 
    }
}

class LFUCache {
    int capacity;
    int minFreq;
    Map<Integer, Integer> cache; // key, value
    Map<Integer, Integer> counts; // key, frequency
    Map<Integer, LinkedHashSet<Integer>> seq; // frequency, keys

    public LFUCache(int n) {
        this.capacity = n;
        this.cache = new HashMap<>();
        this.counts = new HashMap<>();
        this.seq = new HashMap<>();
        this.minFreq = 0;
    }

    public void get(int k) {
        if (!cache.containsKey(k)) {
            System.out.println(k + " Not present");
            return;
        }

        int count = counts.get(k);
        counts.put(k, count + 1);
        
        // Move from old frequency list to new
        seq.get(count).remove(k);
        
        // If the current minFreq list is empty, update minFreq
        if (count == minFreq && seq.get(count).isEmpty()) {
            minFreq++;
        }
        
        if (!seq.containsKey(count + 1)) {
            seq.put(count + 1, new LinkedHashSet<>());
        }
        seq.get(count + 1).add(k);
        System.out.println("Fetch " + k + " : " + cache.get(k));
    }

    public void put(int k, int v) {
        if (capacity <= 0) return;

        if (cache.containsKey(k)) {
            cache.put(k, v);
            get(k); // update frequency
            return;
        }

        if (cache.size() >= capacity) {
            int evict = seq.get(minFreq).iterator().next();
            seq.get(minFreq).remove(evict);
            cache.remove(evict);
            counts.remove(evict);
            System.out.println("Evicted: " + evict);
        }

        // Add new item
        cache.put(k, v);
        counts.put(k, 1);
        minFreq = 1; // New items always have freq 1
        if (!seq.containsKey(1)) {
            seq.put(1, new LinkedHashSet<>());
        }
        seq.get(1).add(k);
        System.out.println("Inserted: " + k);
    }
}