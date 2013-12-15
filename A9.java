/**
 * @author Anders Dahl
 * @email dahl.a@husky.neu.edu
 * 
 * Also changed equals to use
 * the iterator
 * 
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Collections;

/**
 * Represents a map of values. K represents
 * the key and V represents the value
 * of that key.
 */
public abstract class FMap<K, V>
    implements Iterable<K> {

    /**
     * Creates an empty FMap<K,V>
     * 
     * @return      <code>Empty<K,V></code>
     *              the Empty FMap<K,V>
     */
    public static <K, V> FMap<K, V> empty() {
        return new Empty<K, V>();
    }

    /**
     * Creates an empty FMap<K,V>
     * 
     * @param c     Comparator<K>
     * @return      <code>EmptyRBT<K,V></code>
     *              the EmptyRBT FMap<K,V>
     */
    public static <K, V> FMap<K, V> empty(Comparator<K> c) {
        return new EmptyRBT<K, V>(c);
    }

    /**
     * Creates a non-empty FMap<K,V>
     * 
     * @param k     the key
     * @param v     the value
     * @return      <code>FMap<K,V></code>
     *              the newly created non-empty
     *              FMap<K,V>
     */
    public abstract FMap<K, V> include(K k, V v);
    
    /**
     * Creates an iterator from this FMap<K,V>
     * 
     * @param c     the given Comparator<T>
     * @return      <code>Iterator<K></code>
     *              the created iterator, in
     *              sorted order
     */
    public abstract Iterator<K> iterator();

    /**
     * Creates an iterator from this FMap<K,V>
     * 
     * @param c     the given Comparator<T>
     * @return      <code>Iterator<K></code>
     *              the created iterator, in
     *              sorted order
     */
    public abstract Iterator<K> iterator(Comparator<K> c);
    
    /**
     * Helper for iterator
     * 
     * Adds the keys from this
     * to the given ArrayList<K>
     * 
     * @param a     the given ArrayList<K> that
     *              the keys get added to
     *              by recursion
     * @return      <code>ArrayList<K></code>
     *              the ArrayList<K> with
     *              the keys
     */
    protected abstract ArrayList<K> addKeys(ArrayList<K> a);
    
    /**
     * Checks if the FMap<K,V> is empty
     * 
     * @return      <code>true</code> if the
     *              FMap is empty;
     *              <code>false</code> otherwise.
     */
    public abstract boolean isEmpty();
    
    /**
     * Gets the size of the FMap<K,V>
     * 
     * @return      <code>int</code> returns
     *              a natural number representing
     *              the size
     */
    public abstract int size();

    /**
     * Checks if the FMap<K,V> contains the key k
     * 
     * @param k     the K being checked
     * @return      <code>true</code> if k is
     *              in the FMap;
     *              <code>false</code> otherwise.
     */
    public abstract boolean containsKey(K k);

    /**
     * Gets the value v of the key k
     * 
     * @param k     the given key
     * @return      <code>V</code> the value that
     *              the key gets
     */
    public abstract V get(K k);

    /**
     * Overrides the toString method in the
     * java API. Creates a string from the FMap
     * 
     * @return      <code>String</code>
     *              returns the string
     */
    public String toString() {
        return "{...(" + this.size() +
                " keys mapped to values)...}";
    }
    
    /**
     * Overrides the hashCode method
     * 
     * @return      <code>int</code> the hashCode
     */
    public abstract int hashCode();
    
    /**
     * Overrides the equals method. Checks if an
     * object is equal to this FMap
     * 
     * @param o     the given set object
     * @return      <code>true</code> the two objects
     *              are the same;
     *              <code>false</code> otherwise.
     */
    public abstract boolean equals(Object o);
    
    /**
     * Visits a class without modifying it, uses
     * double dispatch to do something new without
     * making changes
     * 
     * 
     * @param visitor   the visitor
     * @return          <code>FMap<K,V></code>
     *                  the returned FMap<K,V>
     */
    public abstract FMap<K, V> accept(Visitor<K, V> visitor);

}

/**
 * Represents an FMap<K,V> as a list
 */
abstract class AList<K,V> extends FMap<K, V>
    implements Iterable<K> {
    
    /**
     * Creates an ArrayList from the
     * FMap keys
     * 
     * @return      <code>ArrayList<K></code>
     *              the ArrayList<K> from
     *              the keys
     */
    protected abstract ArrayList<K> makeArrayList();
    
}

/**
 * Represents an empty AList<K,V>
 */
class Empty<K, V> extends AList<K, V>{

    /**
     * Creates an empty constructor
     */
    Empty() {}
    
    /**
     * Creates a non-empty AList<K,V>
     * 
     * @param k     the key
     * @param v     the value
     * @return      <code>AList<K,V></code>
     *              the newly created non-empty
     *              AList<K,V>
     */
    public AList<K, V> include(K k, V v){
        return new Include<K,V>(k, v, this);
    }
    
    /**
     * Creates an KeyIterator from this FMap<K,V>
     * 
     * @return      <code>KeyIterator<K></code>
     *              the created iterator
     */
    public Iterator<K> iterator(){
        return new KeyIterator<K>(this.makeArrayList());
    }
    
    /**
     * Creates an iterator from this FMap<K,V>
     * 
     * @param c     the given Comparator<T>
     * @return      <code>KeyIterator<K></code>
     *              the created iterator, in
     *              sorted order
     */
    public Iterator<K> iterator(Comparator<K> c){
        return new KeyIterator<K>(this.makeArrayList());
    }
    
    /**
     * Creates an ArrayList from the
     * FMap keys
     * 
     * @return      <code>ArrayList<K></code>
     *              the ArrayList<K> from
     *              the keys
     */
    protected ArrayList<K> makeArrayList(){
        return new ArrayList<K>();
    }

    /**
     * Helper for iterator
     * 
     * Adds the keys from this
     * to the given ArrayList<K>
     * 
     * @param a     the given ArrayList<K> that
     *              the keys get added to
     *              by recursion
     * @return      <code>ArrayList<K></code>
     *              the ArrayList<K> with
     *              the keys
     */
    protected ArrayList<K> addKeys(ArrayList<K> a) {
        return a;
    }
    
    /**
     * Checks if the FMap<K,V> is empty
     * 
     * @return      <code>true</code> if the
     *              FMap is empty;
     *              <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return true;
    }

    /**
     * Gets the size of the FMap<K,V>
     * 
     * @return      <code>int</code> returns
     *              a natural number representing
     *              the size
     */
    public int size() {
        return 0;
    }

    /**
     * Checks if the FMap<K,V> contains the key k
     * 
     * @param k     the K being checked
     * @return      <code>true</code> if k is
     *              in the FMap;
     *              <code>false</code> otherwise.
     */
    public boolean containsKey(K k) {
        return false;
    }
    
    /**
     * Gets the value v of the key k
     * 
     * @param k     the given key
     * @return      <code>V</code> the value that
     *              the key gets
     */
    public V get(K k){
        throw new RuntimeException
        ("The key value does not exist - LIST");
    }
    
    /**
     * Overrides the hashCode method
     * 
     * @return      <code>int</code> the hashCode
     */
    public int hashCode() {
        return 0;
    }
    
    /**
     * Overrides the equals method. Checks if an
     * object is equal to this FMap
     * 
     * @param o     the given set object
     * @return      <code>true</code> the two objects
     *              are the same;
     *              <code>false</code> otherwise.
     */
    public boolean equals(Object o) {
        if (o instanceof FMap) {
            @SuppressWarnings("unchecked")
            FMap<K,V> q = (FMap<K,V>) o;
            return q.isEmpty();
        } else {
            return false;
        }
    }
    
    /**
     * Visits a class without modifying it, uses
     * double dispatch to do something new without
     * making changes
     * 
     * 
     * @param visitor   the visitor
     * @return          <code>FMap<K,V></code>
     *                  the returned FMap<K,V>
     */
    public FMap<K, V> accept(Visitor<K, V> visitor) {

        FMap<K, V> m2 = FMap.empty();
        
        return m2;
        
    }
}

/**
 * Represents a non-empty FMap
 */
class Include<K, V> extends AList<K,V>{

    K k0; // the given key
    V v0; // the given value
    FMap<K, V> m0; // the rest of the FMap

    /**
     * Constructor for the concrete class include
     * 
     * @param k0    the given key
     * @param v0    the given value
     * @param m0    the rest of the FMap
     */
    Include(K k0, V v0, FMap<K, V> m0) {
        this.k0 = k0;
        this.v0 = v0;
        this.m0 = m0;
    }
    
    /**
     * Creates a non-empty AList<K,V>
     * 
     * @param k     the key
     * @param v     the value
     * @return      <code>AList<K,V></code>
     *              the newly created non-empty
     *              AList<K,V>
     */
    public AList<K, V> include(K k, V v) {
        if (!this.containsKey(k)) {
            return new Include<K, V>(k, v, this);
        } else if (this.k0.equals(k)){
            return new Include<K, V>(k, v, m0);
        } else {
            return new Include<K,V>(k0, v0, m0.include(k, v));
        }
    }

    /**
     * Creates an KeyIterator from this FMap<K,V>
     * 
     * @return      <code>KeyIterator<K></code>
     *              the created iterator
     */
    public Iterator<K> iterator() {
        ArrayList<K> a = this.makeArrayList();

        return new KeyIterator<K>(a);
    }

    /**
     * Creates an iterator from this FMap<K,V>
     * 
     * @param c     the given Comparator<T>
     * @return      <code>KeyIterator<K></code>
     *              the created iterator, in
     *              sorted order
     */
    public Iterator<K> iterator(Comparator<K> c) {
        ArrayList<K> a = this.makeArrayList();
        Collections.sort(a, c);

        return new KeyIterator<K>(a);
    }

    /**
     * Creates an ArrayList from the
     * FMap keys
     * 
     * @return      <code>ArrayList<K></code>
     *              the ArrayList<K> from
     *              the keys
     */
    protected ArrayList<K> makeArrayList() {
        ArrayList<K> a = new ArrayList<K>();

        return this.addKeys(a);
    }

    /**
     * Helper for iterator
     * 
     * Adds the keys from this
     * to the given ArrayList<K>
     * 
     * @param a     the given ArrayList<K> that
     *              the keys get added to
     *              by recursion
     * @return      <code>ArrayList<K></code>
     *              the ArrayList<K> with
     *              the keys
     */
    protected ArrayList<K> addKeys(ArrayList<K> a) {
        if (m0.containsKey(k0)) {
            return m0.addKeys(a);
        } else {
            a.add(0, k0);
            return m0.addKeys(a);
        }
    }
    
    /**
     * Checks if the FMap<K,V> is empty
     * 
     * @return      <code>true</code> if the
     *              FMap is empty;
     *              <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * Gets the size of the FMap<K,V>
     * 
     * @return      <code>int</code> returns
     *              a natural number representing
     *              the size
     */
    public int size() {
        if (m0.containsKey(k0)) {
            return m0.size();
        } else {
            return m0.size() + 1;
        }
    }
    
    /**
     * Checks if the FMap<K,V> contains the key k
     * 
     * @param k     the K being checked
     * @return      <code>true</code> if k is
     *              in the FMap;
     *              <code>false</code> otherwise.
     */
    public boolean containsKey(K k) {
        if (k.equals(k0)) {
            return true;
        } else {
            return m0.containsKey(k);
        }
    }

    /**
     * Gets the value v of the key k
     * 
     * @param k     the given key
     * @return      <code>V</code> the value that
     *              the key gets
     */
    public V get(K k) {
        if (k.equals(k0)) {
            return v0;
        } else {
            return m0.get(k);
        }
    }

    /**
     * Overrides the hashCode method
     * 
     * @return      <code>int</code> the hashCode
     */
    public int hashCode() {
   
        Iterator<K> iterator = this.iterator();
        int hs = 1;
        
        while(iterator.hasNext()){
            K k = iterator.next();
            V v = this.get(k);
            
            //System.out.println("HS: " + hs +
            //                   " K: " + k.hashCode()+ 
            //                   " V: " + v.hashCode());    
            
            hs *= ((k.hashCode() + 5) + (v.hashCode()) + 7); 
            
        }
        
        // System.out.println(hs + " Final");
        
        return hs + this.size();
        
    }
    
    /**
     * Overrides the equals method. Checks if an
     * object is equal to this FMap
     * 
     * @param o     the given set object
     * @return      <code>true</code> the two objects
     *              are the same;
     *              <code>false</code> otherwise.
     */
    public boolean equals(Object o) {
        if (o instanceof FMap) {
            @SuppressWarnings("unchecked")
            FMap<K, V> q = (FMap<K, V>) o;
            
            if(q.size() == this.size()){
                Iterator<K> i = this.iterator();
                
                while(i.hasNext()){
                    K k = i.next();
                    
                    if(!q.containsKey(k)){
                        return false;
                    } else {
                        if(!q.get(k).equals(this.get(k))){
                            return false;
                        }
                    }
                }
                return true;   
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * Visits a class without modifying it, uses
     * double dispatch to do something new without
     * making changes
     * 
     * 
     * @param visitor   the visitor
     * @return          <code>FMap<K,V></code>
     *                  the returned FMap<K,V>
     */
    public FMap<K, V> accept(Visitor<K, V> visitor) {

        FMap<K, V> m2 = FMap.empty();

        for (K k : this) {

            V v = visitor.visit(k, this.get(k));
            m2 = m2.include(k, v);

        }
        return m2;
    }
}

/**
 * Represents an FMap<K,V> as a red and black tree
 */
abstract class RBTree<K,V> extends FMap<K,V>
    implements Iterable<K> {
    
    public String color; // the color
    
    /**
     * Constructor for RBTree<K,V>
     * 
     * @param color     the given color
     */
    public RBTree(String color) {
        this.color = color;
    }

    /**
     * Creates a non-empty RBTree<K,V>
     * 
     * @param k     the key
     * @param v     the value
     * @return      <code>RBTree<K,V></code>
     *              the newly created non-empty
     *              RBTree<K,V>
     */
    public abstract RBTree<K,V> include(K k, V v);
    
    /**
     * Helper for iterator
     * 
     * Traverses through the RBTree<K,V> and
     * adds the keys to the ArrayList<K> a
     * 
     * @param a     the given ArrayList<K>
     */
    protected abstract void traverse(ArrayList<K> a);
    
    /**
     * Helper for balance
     * 
     * Gets the left of the RBTree<K,V>
     * 
     * @return      <code>RBTree<K,V></code>
     *              the left
     */
    protected abstract RBTree<K,V> getLeft();
    
    /**
     * Helper for balance
     * 
     * Gets the right of the RBTree<K,V>
     * 
     * @return      <code>RBTree<K,V></code>
     *              the right
     */
    protected abstract RBTree<K,V> getRight();
    
    /**
     * Helper for balance
     * 
     * Gets the key of the RBTree<K,V>
     * 
     * @return      <code>RBTree<K,V></code>
     *              the key
     */
    protected abstract K getKey();
    
    /**
     * Helper for balance
     * 
     * Gets the value of the RBTree<K,V>
     * 
     * @return      <code>RBTree<K,V></code>
     *              the value
     */
    protected abstract V getValue();
    
}

/**
 * Represents an empty RBTree<K,V>
 */
class EmptyRBT<K, V> extends RBTree<K, V> {

    Comparator<K> comparator; // the given Comparator<K>
    
    /**
     * Constructor for EmptyRBT
     */
    EmptyRBT() {
        super("black");
    }
    
    /**
     * Constructor for EmptyRBT
     * 
     * @param comparator     the given Comparator<K>
     */
    EmptyRBT(Comparator<K> comparator) {
        super("black");
        this.comparator = comparator;
    }
    
    /**
     * Creates a non-empty RBTree<K,V>
     * 
     * @param k     the key
     * @param v     the value
     * @return      <code>RBTree<K,V></code>
     *              the newly created non-empty
     *              RBTree<K,V>
     */
    public RBTree<K, V> include(K k, V v){
        return new Node<K,V>(k, v,
                this, this,
                comparator,
                "red");
    }
    
    /**
     * Creates an KeyIterator from this FMap<K,V>
     * 
     * @return      <code>KeyIterator<K></code>
     *              the created iterator
     */
    public Iterator<K> iterator(){
        ArrayList<K> a = new ArrayList<K>();
        return new KeyIterator<K>(a);
    }
    
    /**
     * Creates an iterator from this FMap<K,V>
     * 
     * @param c     the given Comparator<T>
     * @return      <code>KeyIterator<K></code>
     *              the created iterator, in
     *              sorted order
     */
    public Iterator<K> iterator(Comparator<K> c){
        ArrayList<K> a = new ArrayList<K>();
        return new KeyIterator<K>(a);
    }
    
    /**
     * Helper for iterator
     * 
     * Traverses through the RBTree<K,V> and
     * adds the keys to the ArrayList<K> a
     * 
     * @param a     the given ArrayList<K>
     */
    protected void traverse(ArrayList<K> a) {
        return;
    }
    
    /**
     * Checks if the FMap<K,V> is empty
     * 
     * @return      <code>true</code> if the
     *              FMap is empty;
     *              <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return true;
    }

    /**
     * Gets the size of the FMap<K,V>
     * 
     * @return      <code>int</code> returns
     *              a natural number representing
     *              the size
     */
    public int size() {
        return 0;
    }

    /**
     * Checks if the FMap<K,V> contains the key k
     * 
     * @param k     the K being checked
     * @return      <code>true</code> if k is
     *              in the FMap;
     *              <code>false</code> otherwise.
     */
    public boolean containsKey(K k) {
        return false;
    }
    
    /**
     * Gets the value v of the key k
     * 
     * @param k     the given key
     * @return      <code>V</code> the value that
     *              the key gets
     */
    public V get(K k){
        throw new RuntimeException
        ("The key value does not exist-RBT");
    }
    
    /**
     * Overrides the hashCode method
     * 
     * @return      <code>int</code> the hashCode
     */
    public int hashCode() {
        return 0;
    }

    /**
     * Overrides the equals method. Checks if an
     * object is equal to this FMap
     * 
     * @param o     the given set object
     * @return      <code>true</code> the two objects
     *              are the same;
     *              <code>false</code> otherwise.
     */
    public boolean equals(Object o) {
        if (o instanceof FMap) {
            @SuppressWarnings("unchecked")
            FMap<K,V> q = (FMap<K,V>) o;
            return q.isEmpty();
        } else {
            return false;
        }
    }
    
    /**
     * Helper for iterator
     * 
     * Adds the keys from this
     * to the given ArrayList<K>
     * 
     * @param a     the given ArrayList<K> that
     *              the keys get added to
     *              by recursion
     * @return      <code>ArrayList<K></code>
     *              the ArrayList<K> with
     *              the keys
     */
    protected ArrayList<K> addKeys(ArrayList<K> a) {
        throw new RuntimeException("addKeys: Unsupported" +
          	"for EmptyRBT");
    }
    
    /**
     * Visits a class without modifying it, uses
     * double dispatch to do something new without
     * making changes
     * 
     * 
     * @param visitor   the visitor
     * @return          <code>FMap<K,V></code>
     *                  the returned FMap<K,V>
     */
    public FMap<K, V> accept(Visitor<K, V> visitor) {

        FMap<K, V> m2 = FMap.empty(comparator);

        return m2;
    }
    
    /**
     * Helper for balance
     * 
     * Gets the left of the RBTree<K,V>
     * 
     * @return      <code>RBTree<K,V></code>
     *              the left
     */
    protected RBTree<K,V> getLeft() {
        throw new RuntimeException("getLeft: Unsupported" +
        		"for emptyRBT");
    }
    
    /**
     * Helper for balance
     * 
     * Gets the right of the RBTree<K,V>
     * 
     * @return      <code>RBTree<K,V></code>
     *              the right
     */
    protected RBTree<K,V> getRight() {
        throw new RuntimeException("getRight: Unsupported" +
        		"for emptyRBT");
    }
    
    /**
     * Helper for balance
     * 
     * Gets the key of the RBTree<K,V>
     * 
     * @return      <code>RBTree<K,V></code>
     *              the key
     */
    protected K getKey() {
        throw new RuntimeException("getKey: Unsupported" +
        		"for emptyRBT");
    }
    
    /**
     * Helper for balance
     * 
     * Gets the value of the RBTree<K,V>
     * 
     * @return      <code>RBTree<K,V></code>
     *              the value
     */
    protected V getValue() {
        throw new RuntimeException("getValue: Unsupported" +
        		"for emptyRBT");
    }
    
}

/**
 * Represents a non-empty RBTree<K>
 */
class Node<K, V> extends RBTree<K, V> {

    K k0; // the given key
    V v0; // the given value
    RBTree<K, V> left; // the left node
    RBTree<K, V> right; // the right node
    Comparator<K> comparator; // the given comparator
    int size; // the size

    /**
     * The constructor for the
     * concrete class Node
     * 
     * @param k0        the given key
     * @param v0        the given value
     * @param left      the left node
     * @param right     the right node
     * @param comparator         the given comparator
     * @param size      the size
     * @param color     the color
     */
    Node(K k0, V v0, RBTree<K, V> left, RBTree<K, V> right,
            Comparator<K> comparator, String color) {
        super(color);
        this.k0 = k0;
        this.v0 = v0;
        this.left = left;
        this.right = right;
        this.comparator = comparator;
        this.initSize();
    }
    
    /**
     * Initializes the size of the node
     */
    void initSize(){
        size = 1 + left.size()
                 + right.size();
    }

    /**
     * Creates a non-empty FMap<K,V>
     * 
     * @param k     the key
     * @param v     the value
     * @return      <code>RBTree<K,V></code>
     *              the newly created non-empty
     *              RBTree<K,V>
     */
    public RBTree<K, V> include(K k, V v) {

        // Adds the key and value then
        // balances the tree if needed
        // by recursively working its
        // way up
        
        // Makes sure two reds are never
        // connected
        
        if (k.equals(k0)) {
            return new Node<K,V>(k, v,
                    this.left,
                    this.right,
                    this.comparator,
                    this.color);
        } else if (comparator.compare(k, k0) < 0) {
            return this.balance(
                    new Node<K,V>(k0, v0,
                    this.left.include(k, v),
                    this.right,
                    this.comparator,
                    this.color));
        } else {
            return this.balance(
                    new Node<K,V>(k0, v0,
                    this.left,
                    this.right.include(k, v),
                    this.comparator,
                    this.color));
        }
        
    }

    /**
     * Creates an KeyIterator from this FMap<K,V>
     * 
     * @return      <code>KeyIterator<K></code>
     *              the created iterator
     */
    public Iterator<K> iterator() {
        ArrayList<K> a = new ArrayList<K>();
        this.traverse(a);
        return new KeyIterator<K>(a);
    }

    /**
     * Creates an iterator from this FMap<K,V>
     * 
     * @param c     the given Comparator<T>
     * @return      <code>KeyIterator<K></code>
     *              the created iterator, in
     *              sorted order
     */
    public Iterator<K> iterator(Comparator<K> c) {

        ArrayList<K> a = new ArrayList<K>();
        this.traverse(a);
        Collections.sort(a, c);
        
        return new KeyIterator<K>(a);

    }

    /**
     * Helper for iterator
     * 
     * Traverses through the RBTree<K,V> and
     * adds the keys to the ArrayList<K> a
     * 
     * @param a     the given ArrayList<K>
     */
    protected void traverse(ArrayList<K> a) {
        left.traverse(a);
        a.add(k0);
        right.traverse(a);
        
    }
    
    /**
     * Checks if the FMap<K,V> is empty
     * 
     * @return      <code>true</code> if the
     *              FMap is empty;
     *              <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * Gets the size of the FMap<K,V>
     * 
     * @return      <code>int</code> returns
     *              a natural number representing
     *              the size
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the FMap<K,V> contains the key k
     * 
     * @param k     the K being checked
     * @return      <code>true</code> if k is
     *              in the FMap;
     *              <code>false</code> otherwise.
     */
    public boolean containsKey(K k) {
        if (k.equals(k0)) {
            return true;
        } else if (comparator.compare(k, k0) < 0){
            return this.left.containsKey(k);
        } else {
            return this.right.containsKey(k);
        }
    }

    /**
     * Gets the value v of the key k
     * 
     * @param k     the given key
     * @return      <code>V</code> the value that
     *              the key gets
     */
    public V get(K k) {
        if (k.equals(k0)) {
            return v0;
        } else if (comparator.compare(k, k0) < 0) {
            return this.left.get(k);
        } else {
            return this.right.get(k);
        }
    }
    
    /**
     * Overrides the hashCode method
     * 
     * @return      <code>int</code> the hashCode
     */
    public int hashCode() {
        
        Iterator<K> iterator = this.iterator();
        int hs = 1;
        
        while(iterator.hasNext()){
            K k = iterator.next();
            V v = this.get(k);
            
            //System.out.println("HS: " + hs +
            //                   " K: " + k.hashCode()+ 
            //                   " V: " + v.hashCode());    
            
            hs *= ((k.hashCode() + 5) + (v.hashCode()) + 7); 
            
        }
        
        // System.out.println(hs + " Final");
        
        return hs + this.size();
        
    }

    /**
     * Overrides the equals method. Checks if an
     * object is equal to this FMap
     * 
     * @param o     the given set object
     * @return      <code>true</code> the two objects
     *              are the same;
     *              <code>false</code> otherwise.
     */
    public boolean equals(Object o) {
        if (o instanceof FMap) {
            @SuppressWarnings("unchecked")
            FMap<K, V> q = (FMap<K, V>) o;

            if (q.size() == this.size()) {
                Iterator<K> i = this.iterator();

                while (i.hasNext()) {
                    K k = i.next();

                    if (!q.containsKey(k)) {
                        return false;
                    } else {
                        if (!q.get(k).equals(this.get(k))) {
                            return false;
                        }
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Helper for iterator
     * 
     * Adds the keys from this
     * to the given ArrayList<K>
     * 
     * @param a     the given ArrayList<K> that
     *              the keys get added to
     *              by recursion
     * @return      <code>ArrayList<K></code>
     *              the ArrayList<K> with
     *              the keys
     */
    protected ArrayList<K> addKeys(ArrayList<K> a) {
        throw new RuntimeException("addKeys: Unsupported" +
        		"for Node");
    }
    
    /**
     * Visits a class without modifying it, uses
     * double dispatch to do something new without
     * making changes
     * 
     * 
     * @param visitor   the visitor
     * @return          <code>FMap<K,V></code>
     *                  the returned FMap<K,V>
     */
    public FMap<K, V> accept(Visitor<K, V> visitor) {

        FMap<K, V> m2 = FMap.empty(comparator);

        for (K k : this) {

            V v = visitor.visit(k, this.get(k));
            m2 = m2.include(k, v);

        }
        return m2;
    }
    
    /**
     * Helper for include
     * 
     * Balances the given RBTree<K,V>
     * 
     * @param RBT   the given RBTree<K,V>
     * @return      <code>RBTree<K,V></code>
     *              balances the RBT<K,V> to make
     *              sure two red nodes are not
     *              connected
     */
    protected RBTree<K,V> balance(Node<K,V> RBT) {
        if(this.getLeft().isEmpty() &&
                this.getRight().isEmpty()){
            return RBT;
        } else if (RBT.getLeft().color.equals("red") &&
                // Short circuits if the first is incorrect, 
                // if it is empty the color is black
                RBT.getLeft().getLeft().
                color.equals("red")){
            return RBT.LL();
        } else if (RBT.getRight().color.equals("red") &&
                RBT.getRight().getRight().
                color.equals("red")){
            return RBT.RR();
        } else if (RBT.getLeft().color.equals("red") &&
                RBT.getLeft().getRight().
                color.equals("red")){
            return RBT.LR();
        } else if (RBT.getRight().color.equals("red") &&
                RBT.getRight().getLeft().
                color.equals("red")){
            return RBT.RL();
        } else {
            // Doesn't need to be balanced
            return RBT;
        }
    }
    
    /**
     * Helper for balance
     * 
     * Gets the left of the RBTree<K,V>
     * 
     * @return      <code>RBTree<K,V></code>
     *              the left
     */
    protected RBTree<K,V> getLeft() {
        return this.left;
    }
    
    /**
     * Helper for balance
     * 
     * Gets the right of the RBTree<K,V>
     * 
     * @return      <code>RBTree<K,V></code>
     *              the right
     */
    protected RBTree<K,V> getRight() {
        return this.right;
    }
    
    /**
     * Helper for balance
     * 
     * Gets the key of the RBTree<K,V>
     * 
     * @return      <code>RBTree<K,V></code>
     *              the key
     */
    protected K getKey() {
        return this.k0;
    }
    
    /**
     * Helper for balance
     * 
     * Gets the value of the RBTree<K,V>
     * 
     * @return      <code>RBTree<K,V></code>
     *              the value
     */
    protected V getValue() {
        return this.v0;
    }
    
    /**
     * Helper for balance
     * 
     * Creates a new RBTree<K,V> were
     * two red nodes are not connected.
     * In this case, the direction left,
     * left connects two red nodes
     * 
     * @return      <code>RBTree<K,V></code>
     *              the fixed RBTree<K,V>
     */
    protected RBTree<K,V> LL() {
        RBTree<K,V> x = this.getLeft().getLeft();
        RBTree<K,V> y = this.getLeft();
        RBTree<K,V> z = this;
        
        RBTree<K,V> a = this.getLeft().getLeft().getLeft();
        RBTree<K,V> b = this.getLeft().getLeft().getRight();
        RBTree<K,V> c = this.getLeft().getRight();
        RBTree<K, V> d = this.getRight();

        return this.formBalanced(x, y, z, a, b, c, d);
    }
    
    /**
     * Helper for balance
     * 
     * Creates a new RBTree<K,V> were
     * two red nodes are not connected.
     * In this case, the direction right,
     * right connects two red nodes
     * 
     * @return      <code>RBTree<K,V></code>
     *              the fixed RBTree<K,V>
     */
    protected RBTree<K,V> RR() {
        RBTree<K,V> x = this;
        RBTree<K,V> y = this.getRight();
        RBTree<K,V> z = this.getRight().getRight();
        
        RBTree<K,V> a = this.getLeft();
        RBTree<K,V> b = this.getRight().getLeft();
        RBTree<K,V> c = this.getRight().getRight().getLeft();
        RBTree<K,V> d = this.getRight().getRight().getRight();

        return this.formBalanced(x, y, z, a, b, c, d);
    }
    
    /**
     * Helper for balance
     * 
     * Creates a new RBTree<K,V> were
     * two red nodes are not connected.
     * In this case, the direction left,
     * right connects two red nodes
     * 
     * @return      <code>RBTree<K,V></code>
     *              the fixed RBTree<K,V>
     */
    protected RBTree<K,V> LR() {
        RBTree<K,V> x = this.getLeft();
        RBTree<K,V> y = this.getLeft().getRight();
        RBTree<K,V> z = this;
        
        RBTree<K,V> a = this.getLeft().getLeft();
        RBTree<K,V> b = this.getLeft().getRight().getLeft();
        RBTree<K,V> c = this.getLeft().getRight().getRight();
        RBTree<K,V> d = this.getRight();

        return this.formBalanced(x, y, z, a, b, c, d);
    }
    
    /**
     * Helper for balance
     * 
     * Creates a new RBTree<K,V> were
     * two red nodes are not connected.
     * In this case, the direction right,
     * left connects two red nodes
     * 
     * @return      <code>RBTree<K,V></code>
     *              the fixed RBTree<K,V>
     */
    protected RBTree<K,V> RL() {
        RBTree<K,V> x = this;
        RBTree<K,V> y = this.getRight().getLeft();
        RBTree<K,V> z = this.getRight();
        
        RBTree<K,V> a = this.getLeft();
        RBTree<K,V> b = this.getRight().getLeft().getLeft();
        RBTree<K,V> c = this.getRight().getLeft().getRight();
        RBTree<K,V> d = this.getRight().getRight();
        
        return this.formBalanced(x, y, z, a, b, c, d);
    }
    
    /**
     * Helper for balance
     * 
     * Creates a balanced tree where
     * two red nodes are not next to
     * each other
     * 
     * @param x     x RBTree<K,V>
     * @param y     y RBTree<K,V>
     * @param z     z RBTree<K,V>
     * @param a     a RBTree<K,V>
     * @param b     b RBTree<K,V>
     * @param c     c RBTree<K,V>
     * @param d     d RBTree<K,V>
     * @return      <code>RBTree<K,V></code>
     *              the fixed RBTree<K,V>
     */
    protected RBTree<K,V> formBalanced(
            RBTree<K,V> x, RBTree<K,V> y,
            RBTree<K,V> z, RBTree<K,V> a,
            RBTree<K,V> b, RBTree<K,V> c,
            RBTree<K,V> d) {
        
        RBTree<K, V> l = new Node<K, V>(
                x.getKey(), 
                x.getValue(), 
                a, 
                b,
                comparator, 
                "black");
        RBTree<K, V> r = new Node<K, V>(
                z.getKey(), 
                z.getValue(), 
                c, 
                d,
                comparator, 
                "black");
        
        return new Node<K, V>(
                y.getKey(), 
                y.getValue(), 
                l, 
                r,
                comparator, 
                "red");
    }

}

/**
 * Represents an iterator made up from
 * the given ArrayList<K>
 */
class KeyIterator<K> implements Iterator<K> {

    ArrayList<K> a; // the given ArrayList<K>
    Iterator<K> it; // the iterator made from
                    // the ArrayList<K>
    
    /**
     * Constructor for the concrete
     * class KeyIterator<K>
     * 
     * @param a     the given ArrayList<K>
     * @param it    the iterator made from
     *              the ArrayList<K>
     */
    KeyIterator(ArrayList<K> a) {
        this.a = a;
        it = a.iterator();
    }

    /**
     * Checks if the iterator has a next
     * 
     * @return      <code>true</code> if the
     *              iterator<K> has a next
     *              <code>false</code> otherwise.
     */
    public boolean hasNext() {
        return it.hasNext();
    }

    /**
     * Gets the next of the iterator
     * 
     * @return      <code>K</code> the next
     *              element, if there is no
     *              such element it throws
     *              an exception
     */
    public K next() {
        return it.next();
    }

    /**
     * Removes an element from the iterator,
     * but it is not properly implemented yet.
     * For now, it just throws an
     * UnsupportedOperationException
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
}
