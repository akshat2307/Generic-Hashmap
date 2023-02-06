package Includes;

public class DictionaryEntry<K, V> {
    public K key;
    public V value;
    public DictionaryEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}