import java.util.*;

// Define the generic type T at the class level
class LRUCache<T> {
    private final int capacity;
    // LinkedHashSet maintains the order of elements based on access/insertion
    private final LinkedHashSet<T> cache;

    public LRUCache(int size) {
        this.capacity = size;
        this.cache = new LinkedHashSet<>(size);
    }

    public T get(T val) {
        if (!cache.contains(val)) {
            return null; 
        }
        // To "refresh" the item, we must remove and re-add it
        cache.remove(val);
        cache.add(val);
        return val;
    }

    public void put(T val) {
        if (cache.contains(val)) {
            cache.remove(val);
        } else if (cache.size() >= capacity) {
            // Remove the first element (the oldest/Least Recently Used)
            T oldest = cache.iterator().next();
            cache.remove(oldest);
        }
        cache.add(val);
        
        System.out.println("Cache after putting " + val + ": " + cache);
    }
}

public class Main {
    public static void main(String[] args) {
        // Specify the type (Integer, String, etc.) when instantiating
        LRUCache<Integer> cache = new LRUCache<>(3);
        
        cache.put(1);
        cache.put(2);
        cache.put(3);
        cache.put(4); // 1 is evicted (oldest)
        cache.put(5); // 2 is evicted (oldest)
        
        cache.get(3); // 3 becomes the "most recent"
        cache.put(6); // 4 is evicted (because 3 was refreshed)
    }
}