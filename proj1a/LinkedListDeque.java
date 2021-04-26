public class LinkedListDeque<T> {
    public class Node{
        public T item;
        public Node next;
        public Node prev ;

        public Node(Node n, T i, Node m){
            item = i;
            prev = n;
            next = m;
        }
    }

    public Node sentinel; //This is a Circular Sentinel
    public int size;

    /* Creates an empty list. */
    public LinkedListDeque( ){
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /* Adds an item to the front of the list. */
    public void addFirst(T item){
        sentinel.next.prev = new Node(sentinel, item, sentinel.next);
        sentinel.next = sentinel.next.prev;
        size ++;
    }

    /* Adds an item to the back of the list. */
    public void addLast(T item){
        sentinel.prev.next = new Node(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size ++;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty(){
        return size == 0;
    }

    /* Returns the number of items in the deque. */
    public int size(){
        return size;
    }

    /* Prints the items in the deque from first to last, separated by a space. */
     public void printDeque(){
        for (Node temp = sentinel.next; temp != sentinel; temp = temp.next){
            if (temp.next == sentinel){
                System.out.println(temp.item);
            }
            System.out.print(temp.item + " ");
        }
     }

     /* Removes and returns the item at the back of the deque.
      * If no such item exists, returns null */
     public T removeFirst(){
         if (sentinel.next == sentinel) return null;
         T res = sentinel.next.item;
         sentinel.next = sentinel.next.next;
         sentinel.next.prev = sentinel;
         size--;
         return res;
     }

     /* Removes and returns the item at the back of the deque.
      * If no such item exists, returns null.*/
     public T removeLast(){
         if (sentinel.next == sentinel) return null;
         T res = sentinel.prev.item;
         sentinel.prev = sentinel.next.prev;
         sentinel.next.next = sentinel;
         size--;
         return res;
     }

    /*
     * Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque!
     */
     public T get(int index){
         if (size <= index)  return null;
         Node temp = sentinel.next;
         while(index > 0){
             temp = temp.next;
             index--;
         }
         return temp.item;
     }

    /* Same as get, but uses recursion. */
    public T getRecursive(int index){
        if (size <= index) return null;
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(Node node, int i ){
        if (i == 0) return node.item;
        return getRecursive(node.next, i-1);
    }
}
