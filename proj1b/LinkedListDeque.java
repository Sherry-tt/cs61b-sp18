public class LinkedListDeque<Item> implements Deque<Item>  {

    private class LinkedNode {

        private Item item;
        private LinkedNode prev;
        private LinkedNode next;

        public LinkedNode(Item i, LinkedNode p, LinkedNode n){
            item = i;
            prev = p;
            next = n;
        }

        private Item getRecursive(int index){
            if(index == 0){
                return this.item;
            }
            return next.getRecursive(index -1);
        }
    }

    private LinkedNode sentinel;
    private LinkedNode last;
    private int size;

    public LinkedListDeque(){
        sentinel = new LinkedNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        last = sentinel;
        size = 0;
    }

//	public LinkedListDeque(T i){
//		sentinel = new LinkedNode(null, null, null);
//		sentinel.next = new LinkedNode(i, sentinel, sentinel);
//		sentinel.prev = sentinel.next;
//		last = sentinel.next;
//		size = 1;
//	}
    @Override
    public void addFirst(Item x){
        sentinel.next = new LinkedNode(x, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        // trace last when only one item in the list and then removed
        if(size == 0) {
            last = sentinel.next;
        }
        size += 1;
    }

    @Override
    public void addLast(Item x){
        last.next = new LinkedNode(x, last, sentinel);
        last = last.next;
        sentinel.prev = last;
        size += 1;
    }

    @Override
    public Item removeFirst(){
        if(isEmpty()){
            System.out.println("empty list!!!");
            return null;
        }
        Item result;
        result = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size -= 1;
        // track last pointer
        if(size == 0) {
            last = sentinel;
            sentinel.prev = sentinel;
            return result;
        }
        sentinel.next.prev = sentinel;
        return result;
    }

    @Override
    public Item removeLast(){
        if(isEmpty()){
            System.out.println("empty list!!!");
            return null;
        }
        Item result;
        result = last.item;
        last.prev.next = sentinel;
        last = last.prev;
        sentinel.prev = last;
        size -= 1;
        return result;
    }

    /** */
    @Override
    public Item get(int index){
        LinkedNode p = sentinel.next;
        for (int i = 0; i < index; i++){
            p = p.next;
        }
        return p.item;
    }

    /** recursive */
    public Item getRecursive(int index){
        if(size() == 0 || index >= size()){
            return null;
        }
        return sentinel.next.getRecursive(index);
    }

    private Item getFirst(){
        return sentinel.next.item;
    }

    private Item getLast(){
        return sentinel.prev.item;
    }

    /** */
    @Override
    public void printDeque(){
        LinkedNode p = sentinel.next;
        for (int i = 0; i < size(); i++){
            System.out.println(p.item);
            p = p.next;
        }
    }

    @Override
    /** using caching method, oop programming, everything is controlled by object  */
    public int size(){
        return size;
    }

    @Override
    /** using caching method, oop programming, everything is controlled by object  */
    public boolean isEmpty(){
        return (size == 0);
    }


}