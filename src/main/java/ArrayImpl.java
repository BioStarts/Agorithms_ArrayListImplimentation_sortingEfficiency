import java.util.Arrays;

public class ArrayImpl<E> implements Array<E> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final int INVALID_INDEX = -1;

    private E[] data;
    private int currentSize;

    public ArrayImpl() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayImpl(int initialCapacity) {
        this.data =(E[]) new Object[initialCapacity];
    }


    public void add(E value) {
        checkGrow();
        this.data[currentSize++] = value;
    }

    private void checkGrow() { //проверяем достаточно ли места в массиве и если нет увеличивем в 2 раза
        if (currentSize<data.length)
            return;

        data = Arrays.copyOf(data,data.length * 2);
    }

    public E get(int index) {
        return data[index];
    }


    public boolean remove(E value) {
        return removeByIndex(indexOf(value));
    }

    public void remove(int index) {
        boolean result = removeByIndex(index);
        if (!result){
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    private boolean removeByIndex(int index){
        if (index == INVALID_INDEX || index < 0 || index >= currentSize)
            return false;

        for (int i = index; i < currentSize - 1; i++) {
            data[i] = data[i+1];
        }

        data[currentSize - 1] = null;
        currentSize--;

        return true;
    }


    public boolean contains(E value) {//проверяем есть ли впринципе такой элемент в массиве по значению
        return indexOf(value) != -1 ? true : false;
    }

    public int indexOf(E value) {//ищем индекс элемента по значению
        for (int i = 0; i < currentSize; i++) {
            if (data[i].equals(value)){
                return i;
            }
        }
        return INVALID_INDEX;
    }

    public int getSize() {
        return currentSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }
}
