package arrListLib.vm;

public class VMArrayList<T> implements VMCollection<T> {
    private Object[] array;
    private int globalSize = 2;
    private int length = 0;
    private int pointer;
    public VMArrayList(){
        array = new Object[globalSize];
        pointer = globalSize / 2;
    }
    @Override
    public int size(){
        return length;
    }
    @Override
    public void addFirst(T item){
        checkResize();
        ++length;
        array[pointer - length] = item;
    }
    @Override
    public void addLast(T item){
        checkResize();
        ++length;
        array[pointer] = item;
        ++pointer;
    }
    @Override
    public void deleteFirst() {
        this.delete(0);
    }
    @Override
    public void deleteLast() {
        this.delete(length - 1);
    }
    public void concatArray(T[] array){
        for (T item : array) {
            this.addLast(item);
        }
    }
    public T get(int index){
        checkIndex(index);
        @SuppressWarnings("unchecked")
        final T result = (T) array[indexMask(index)];
        return result;
    }
    public T getFirst(){
        return get(0);
    }
    public T getLast(){
        return get(length - 1);
    }
    public void set(int index, T item){
        checkIndex(index);
        array[indexMask(index)] = item;
    }
    public void delete(int index){
        checkIndex(index);
        for(int i = indexMask(index) + 1; i < pointer; ++i)
            array[i - 1] = array[i];
        array[pointer - 1] = null;
        --pointer;
        --length;
    }
    private int indexMask(int index){
        return pointer - length + index;
    }

    private void checkIndex(int index){
        if(index < 0 || index >= length)
            outOfRangeException(index);
    }

    private void checkResize(){
        if(pointer - length == 0 || pointer == globalSize)
            resize();
    }

    private void outOfRangeException(int index){
        String exMessage = "WRONG INDEX!\nIndex " + index + " out of bounds for length " + length;
        throw new ArrayIndexOutOfBoundsException(exMessage);
    }

    private void resize(){
        int tempPointer = pointer;
        globalSize *= 2;
        pointer = globalSize/2 - (int)Math.round((double)length/2) + length;
        Object[] tempArr = new Object[globalSize];
        for(int i = pointer - length, j = 0; i < pointer; ++i, ++j) {
            @SuppressWarnings("unchecked")
            final T result = (T) array[tempPointer - length + j];
            tempArr[i] = result;
        }
        array = tempArr;
    }
}

