package by.innowise.poverov;

import java.util.Objects;

public class CustomLinkedList<E> implements LinkedListInterface<E> {

    private static class Node<E> {

        private final E data;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public CustomLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E getFirst() {
        checkNotEmpty();
        return head.data;
    }

    @Override
    public E getLast() {
        checkNotEmpty();
        return tail.data;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);
        return getNodeByIndex(index).data;
    }


    @Override
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.next = head;
        head = newNode;
        if (size == 0) {
            tail = newNode;
        }
        size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);
        if (size == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, E element) {
        checkPositionIndex(index);

        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node<E> prev = getNodeByIndex(index - 1);
            Node<E> newNode = new Node<>(element);
            newNode.next = prev.next;
            prev.next = newNode;
            size++;
        }
    }

    @Override
    public E removeFirst() {
        checkNotEmpty();
        E removedData = head.data;
        head = head.next;
        size--;
        if (size == 0) {
            tail = null;
        }
        return removedData;
    }

    @Override
    public E removeLast() {
        checkNotEmpty();

        if (size == 1) {
            return removeFirst();
        }

        Node<E> prev = getNodeByIndex(size - 2);
        E removedData = tail.data;
        prev.next = null;
        tail = prev;
        size--;
        return removedData;
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);

        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }

        Node<E> prev = getNodeByIndex(index - 1);
        E toRemove = prev.next.data;
        prev.next = prev.next.next;
        size--;
        return toRemove;
    }

    private Node<E> getNodeByIndex(int index) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void checkNotEmpty() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node<E> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }

        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if  (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomLinkedList<?> that = (CustomLinkedList<?>) o;
        if (this.size != that.size) {
            return false;
        }

        Node<E> thisCurrentNode = head;
        Node<?> thatCurrentNode = that.head;

        while (thisCurrentNode != null && thatCurrentNode != null) {
            if (!Objects.equals(thisCurrentNode.data,thatCurrentNode.data)) {
                return false;
            } else {
                thisCurrentNode = thisCurrentNode.next;
                thatCurrentNode = thatCurrentNode.next;

            }
        }
        return thisCurrentNode == null && thatCurrentNode == null;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        Node<E> currentNode = head;
        while (currentNode != null) {
            hash = 31 * hash + (currentNode.data == null ? 0 : currentNode.data.hashCode());
            currentNode = currentNode.next;
        }
        return hash;
    }
}
