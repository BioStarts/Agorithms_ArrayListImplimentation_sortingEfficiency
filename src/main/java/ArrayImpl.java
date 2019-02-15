import java.util.Arrays;

public class ArrayImpl<E extends Object & Comparable<? super  E>> implements Array<E> {

    private static final int DEFAULT_CAPACITY = 16;
    protected static final int INVALID_INDEX = -1;

    protected E[] data;
    protected int currentSize;

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

    protected void checkGrow() { //проверяем достаточно ли места в массиве и если нет увеличивем в 2 раза
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

    @Override
    public void sortBubble() {
        for (int i = 0; i < currentSize - 1 ; i++) {
            for (int j = 0; j < currentSize - 1 - i; j++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    exchange( j , j + 1);
                }
            }

        }
    }

    @Override
    public void sortSelect() {
        for (int i = 0; i < currentSize - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < currentSize; j++) {
                if (data[j].compareTo(data[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            exchange(minIndex,i);
        }
    }

    @Override
    public void sortInsert() {
        for (int i = 0; i < currentSize; i++) {
            E temp = data[i];

            int in = i;
            while(in > 0 && data[in - 1].compareTo(temp) >= 0) {
                data[in] = data[in - 1];
                in--;
            }
            data[in] = temp;
        }
    }

    private void exchange(int index1, int index2) {
        E temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }


    public static void main(String[] args) {
        int n,arr[];
        n = 15;

        arr = new int [n];
        for (int i=0;i<arr.length;i++)
            arr[i] = (int) ( Math.random() * n);
        for (int i: arr)
            System.out.print( i + " " );


        long start = System.currentTimeMillis();

        long finish = System.currentTimeMillis();
        System.out.println(finish - start);
    }

}
