package arrListLib.vm;

public interface VMCollection<T> {
    int size();
    void addFirst(T item);
    void addLast(T item);
    void deleteFirst();
    void deleteLast();
}
