import java.util.LinkedList;

import Includes.DictionaryEntry;
import Includes.HashTableEntry;
import Includes.KeyAlreadyExistException;
import Includes.KeyNotFoundException;
import Includes.NullKeyException;

import java.lang.reflect.Array;

public class COL106Dictionary<K, V> {

    private LinkedList<DictionaryEntry<K, V>> dict;
    public LinkedList<HashTableEntry<K, V>>[] hashTable;
    public LinkedList<K> kk = new LinkedList<>();
    public int sz = 0;
    public K keys[] = (K[])Array.newInstance(LinkedList.class, 10);
    public int N = 10;
    public int tablesize = 0;
    public int collisions = 0;

    @SuppressWarnings("unchecked")
    COL106Dictionary(int hashTableSize) {
        dict = new LinkedList<DictionaryEntry<K, V>>();
        hashTable = (LinkedList<HashTableEntry<K, V>>[]) Array.newInstance(LinkedList.class, hashTableSize);
        tablesize = hashTableSize;

    }
    public void insert(K key, V value) throws KeyAlreadyExistException, NullKeyException {

        if(key==null){
            throw new NullKeyException();
        }
        int x = hash(key);
        boolean found = false;
        int sizee = 0;
        if(hashTable[x] == null){
            hashTable[x] = new LinkedList<HashTableEntry<K, V>>();
        }
        for(HashTableEntry<K, V> pp: hashTable[x]){
            sizee++;
            if(pp.key.equals(key)){
                found = true;
                break;
            }
        }
        if(found){
            throw new KeyAlreadyExistException();
        }
        if(sizee >= 1){
            collisions++;
        }

        DictionaryEntry<K, V> de = new DictionaryEntry<K, V>(key, value);
        
        dict.add(de);
        HashTableEntry<K, V> he = new HashTableEntry<K, V>(key, dict.getLast()); 
        hashTable[x].addLast(he);
        sz++;

    }

    public V delete(K key) throws NullKeyException, KeyNotFoundException{

        
        if(key==null){
            throw new NullKeyException();
        }

        int x = hash(key);
        if(hashTable[x]==null){throw new KeyNotFoundException();}
        for(HashTableEntry<K, V> pp: hashTable[x]){
            if(pp.key.equals(key)){
                V ans = pp.dictEntry.value;
                dict.remove(pp.dictEntry);
                hashTable[x].remove(pp);
                sz--;
                return ans;
            }
        }
        throw new KeyNotFoundException();
    }

    public V update(K key, V value) throws NullKeyException, KeyNotFoundException{

        if(key==null || value == null){
            throw new NullKeyException();
        }
        int x = hash(key);
        if(hashTable[x] == null){
            throw new KeyNotFoundException();
        }
        for(HashTableEntry<K, V> pp: hashTable[x]){
            if(pp.key.equals(key)){
                V ans = pp.dictEntry.value;
                pp.dictEntry.value = value;
                return ans;
            }
        }
        throw new KeyNotFoundException();
    }

    public V get(K key) throws NullKeyException, KeyNotFoundException {
        if(key==null){
            throw new NullKeyException();
        }
        
        int x= hash(key);

        if(hashTable[x] == null){
            throw new KeyNotFoundException();
        }
        for(HashTableEntry<K, V> pp: hashTable[x]){
            
            if(pp.key.equals(key)){
                V kk = pp.dictEntry.value;
                
                return kk;
            }
        }

        throw new KeyNotFoundException();
    }

    public Boolean exist(K key)throws NullKeyException {

        if(key==null){
            throw new NullKeyException();
        }
        // System.out.println(key);
        int x = hash(key);
        if(hashTable[x] == null){
            return false;
        }
        // if(hashTable[x]==null)return false;
        for(HashTableEntry<K, V> pp:hashTable[x]){
            // System.out.print(pp.key + " ");
            if(pp.key.equals(key)){
                // System.out.println();
                return true;
            }
        }
        return false;
    }

    public int size() {

        return sz;
    }

    // @SuppressWarnings("unchecked")
    public K[] keys(Class<K> cls) {

        K ans[] = (K[])Array.newInstance(cls, sz);
        int i = 0;
        
        for(DictionaryEntry<K, V> hello: dict){
            ans[i] = hello.key;
            i++;
        }
        return ans;
    }

    // @SuppressWarnings("unchecked")
    public V[] values(Class<V> cls) {


        V ans[] = (V[])Array.newInstance(cls, sz);
        int i = 0;
        for(DictionaryEntry<K, V> xx: dict){
            V val = xx.value;
            ans[i] = val;
            i++;
        }
        return ans;
    }

    public int hash(K key) {

        String s = key.toString();
        int ans = 0;
        int m = tablesize;
        for(int i = 0; i<s.length(); i++){
            int cur = (int)(s.charAt(i)) + 1;
            for(int j=1; j<=i; j++){
                cur*=131;
                cur = cur%m;
            }
            ans+=cur;
            ans = ans%m;
        }
        return ans;
    }





}
        