import java.util.HashMap;
import java.util.Map;

public class MapCacheStore implements CacheStore<String, String> {

    private final int timeToLive;
    private Map<String, String> cacheStore;
    private int capacity;

    public MapCacheStore(int timeToLive, int capacity) {
        this.timeToLive = timeToLive;
        this.cacheStore = new HashMap<>();
    }
    @Override
    public String get(String s) {
        return cacheStore.get(s);
    }

    @Override
    public void put(String s, String s2) {
        synchronized (this) {
            if(cacheStore.size() == this.capacity) {
                evictFromCache();
            } else {
                this.capacity += 1;
            }
            cacheStore.put(s, s2);
        }
    }

    private void evictFromCache() {
    }

    @Override
    public void invalidate(String s) {
        synchronized (this) {
            this.cacheStore.remove(s);
            this.capacity -= 1;
        }
    }
}
