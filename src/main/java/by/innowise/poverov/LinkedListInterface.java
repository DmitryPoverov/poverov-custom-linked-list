package by.innowise.poverov;

public interface LinkedListInterface<E> {

    int size();
    void addFirst(E element);
    void addLast(E element);
    void add(int index, E element);
    E getFirst();
    E getLast();
    E get(int index);
    E removeFirst();
    E removeLast();
    E remove(int index);
}
