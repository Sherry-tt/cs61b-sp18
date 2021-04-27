public class ArrayDeque<T> {
    private T[]  items;
    private int capacity = 8;
    private int left;
    private int right;
    private int listSize;

    public ArrayDeque() {
        items = (T []) new Object[capacity];
        left = right = 0;
        listSize = 0;
    }

    /* Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (isFull()) {
            resize((int) (capacity * 1.5));
        }
        left = (left - 1 + capacity) % capacity;
        items[left] = item;
        listSize++;
    }

    /* Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (isFull()) {
            resize((int) (capacity * 1.5));
        }
        items[right] = item;
        right = (right + 1) % capacity;
        listSize++;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return listSize == 0;
    }

    /* Returns the number of items in the deque. */
    public int size() {
        return listSize;
    }

    /* Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        if (isEmpty()) {
            System.out.println("There is no item in the list!");
            return;
        }

        if (left < right) {
            for (int i = 0; i < right - 1; i++) {
                System.out.print(items[i] + " ");
            }
            System.out.println(items[right]);
        } else if (left >= right) {
            for (int i = left; i < capacity; i++) {
                System.out.print(items[i] + " ");
            }
            for (int j = 0; j < right - 1; j++) {
                System.out.print(items[j] + " ");
            }
            System.out.println(items[right]);
        }
    }

    /*Removes and returns the item at the front of the deque.  */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T res = items[left];
        left = (left + 1) % capacity;
        if (isLowUsageRate()) {
            resize((int) (capacity * 0.5));
        }
        listSize--;
        return res;
    }

    /* Remove and returns the item at the back of the deque. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        right = (right - 1 + capacity) % capacity;
        T res = items[right];
        if (isLowUsageRate()) {
            resize((int) (capacity * 0.5));
        }
        listSize--;
        return res;
    }

    /*
     * Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (isEmpty() || index > listSize - 1 || index < 0)  {
            return null;
        }
        return items[(left + index) % capacity];
    }


    private boolean isFull() {
        return listSize == capacity;
    }

    private boolean isLowUsageRate() {
        return capacity >= 16 && listSize / (double) capacity < 0.25;
    }

    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];

        if (left < right) {
            System.arraycopy(items, left, newArray, 0, listSize);
        } else {
            int firstPart = capacity - left;
            System.arraycopy(items, left, newArray, 0, firstPart);
            System.arraycopy(items, 0, newArray, firstPart, listSize - firstPart);
        }
        left = 0;
        right = listSize;
        items = newArray;
        capacity = newSize;
    }

}
