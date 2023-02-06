package Includes;

public class HashTableEntry<K, V> {
    public K key;
    public DictionaryEntry<K, V> dictEntry;
    public HashTableEntry(K key, DictionaryEntry<K, V> dictEntry) {
        this.key = key;
        this.dictEntry = dictEntry;
    }
}
