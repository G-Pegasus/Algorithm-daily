package array;


public class ArrayList<E> {

    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[capacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    // 添加元素，在末尾
    public void add(E element) {
        add(size, element);
    }

    // 指定在某个位置添加元素，需要挪动 index 后面的元素
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    // 保证数组要有多少容量才够，**扩容**
    @SuppressWarnings("unchecked")
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;
        // 扩容的容量为之前的 1.5 倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        // 将旧数组的内容拷贝过来
        if (size >= 0)
            System.arraycopy(elements, 0, newElements, 0, size);
        // 指向到本 elements
        elements = newElements;
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    private void  rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    // 根据下标删除元素
    public void remove(int index) {
        if (size - (index + 1) >= 0)
            System.arraycopy(elements, index + 1, elements, index + 1 - 1, size - (index + 1));

        // 将最后一个元素清空
        elements[--size] = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    public void set(int index, E element) {
        rangeCheck(index);
        elements[index] = element;
    }

    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(element)) return i;
            }
        }

        // 没找到就返回 -1
        return ELEMENT_NOT_FOUND;
    }

    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    public void clear() {
        // 让数组里面指向的对象的地址挂掉，但是数组还可以复用，当添加新对象时，再把新对象的地址存进来
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        // 直接使 size = 0，即可清空数组
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            string.append(elements[i]);
            // 如果不是最后一个元素，就在每个元素后面加一个 ", "
            if (i != size - 1) {
                string.append(", ");
            }
        }
        string.append("]");

        return string.toString();
    }
}
