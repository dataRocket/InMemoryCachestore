public interface CacheStore<K, V>{
    public V get (K k);
    public void put(K k, V v);

    public void invalidate(K k);
}
