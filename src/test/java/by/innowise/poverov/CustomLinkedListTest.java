package by.innowise.poverov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomLinkedListTest {

    private LinkedListInterface<String> list;
    private static final String STRING_1 = "1";
    private static final String STRING_2 = "2";
    private static final String STRING_3 = "3";
    private static final String STRING_NEW_1 = "new1";
    private static final String STRING_NEW_3 = "new3";
    private static final String STRING_EMPTY_LIST = "[]";
    private static final String STRING_NEW1_3_2_1 = "[new1, 3, 2, 1]";
    private static final String STRING_1_2_3 = "[1, 2, 3]";
    private static final String STRING_1_3 = "[1, 3]";
    private static final String STRING_3_2_1 = "[3, 2, 1]";
    private static final String STRING_1_2_3_NEW3 = "[1, 2, 3, new3]";
    private static final String STRING_1_2_NEW3_3 = "[1, 2, new3, 3]";

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int MINUS_ONE = -1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;

    @BeforeEach
    void setUp() {
        list = new CustomLinkedList<>();
    }

    @Test
    void size() {
        list.add(0, STRING_1);
        list.add(1, STRING_2);
        list.add(2, STRING_3);

        Assertions.assertEquals(THREE, list.size());
        list.addFirst(STRING_NEW_1);
        Assertions.assertEquals(FOUR, list.size());
        list.addLast(STRING_NEW_3);
        Assertions.assertEquals(FIVE, list.size());
        list.removeFirst();
        Assertions.assertEquals(FOUR, list.size());
    }

    @Test
    void getFirst() {
        Assertions.assertThrows(IllegalStateException.class, () -> list.getFirst());
        list.add(0, STRING_1);
        Assertions.assertEquals(STRING_1, list.getFirst());

        list.add(1, STRING_2);
        list.add(2, STRING_3);
        Assertions.assertEquals(STRING_3, list.getLast());
    }

    @Test
    void getLast() {
        Assertions.assertThrows(IllegalStateException.class, () -> list.getLast());
        list.add(0, STRING_1);
        Assertions.assertEquals(STRING_1, list.getLast());

        list.add(1, STRING_2);
        list.add(2, STRING_3);
        Assertions.assertEquals(STRING_3, list.getLast());
    }

    @Test
    void get() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(ZERO));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(MINUS_ONE));
        list.add(0, STRING_1);
        list.add(1, STRING_2);
        list.add(2, STRING_3);
        Assertions.assertEquals(STRING_2, list.get(ONE));
    }

    @Test
    void addFirst() {
        list.addFirst(STRING_1);
        Assertions.assertEquals(list.getFirst(), list.getLast());

        list.addFirst(STRING_2);
        list.addFirst(STRING_3);
        Assertions.assertEquals(STRING_3_2_1, list.toString());

        list.addFirst(STRING_NEW_1);
        Assertions.assertEquals(STRING_NEW_1, list.getFirst());
        Assertions.assertEquals(STRING_NEW1_3_2_1, list.toString());
    }

    @Test
    void addLast() {
        list.addLast(STRING_1);
        Assertions.assertEquals(list.getFirst(), list.getLast());

        list.addLast(STRING_2);
        list.addLast(STRING_3);
        Assertions.assertEquals(STRING_1_2_3, list.toString());

        list.addLast(STRING_NEW_3);
        Assertions.assertEquals(STRING_NEW_3, list.getLast());
        Assertions.assertEquals(STRING_1_2_3_NEW3, list.toString());
    }

    @Test
    void add() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.add(ONE, STRING_1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.add(MINUS_ONE, STRING_1));
        list.add(ZERO, STRING_1);
        Assertions.assertEquals(ONE, list.size());

        list.add(ONE, STRING_2);
        list.add(TWO, STRING_3);
        Assertions.assertEquals(THREE, list.size());
        Assertions.assertEquals(STRING_1_2_3, list.toString());

        list.add(TWO, STRING_NEW_3);
        Assertions.assertEquals(FOUR, list.size());
        Assertions.assertEquals(STRING_1_2_NEW3_3, list.toString());
    }

    @Test
    void removeFirst() {
        Assertions.assertThrows(IllegalStateException.class, () -> list.removeFirst());

        list.add(0, STRING_1);
        Assertions.assertEquals(ONE, list.size());
        Assertions.assertEquals(list.getFirst(), list.getLast());

        Assertions.assertEquals(STRING_1, list.removeFirst());
        Assertions.assertEquals(ZERO, list.size());
    }

    @Test
    void removeLast() {
        Assertions.assertThrows(IllegalStateException.class, () -> list.removeLast());

        list.add(0, STRING_1);
        Assertions.assertEquals(ONE, list.size());

        Assertions.assertEquals(list.getLast(), list.removeLast());
        Assertions.assertEquals(ZERO, list.size());
    }

    @Test
    void remove() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(ZERO));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(MINUS_ONE));

        list.add(0, STRING_1);
        list.add(1, STRING_2);
        list.add(2, STRING_3);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(THREE));

        Assertions.assertEquals(STRING_2, list.remove(ONE));
        Assertions.assertEquals(TWO, list.size());
        Assertions.assertEquals(STRING_1_3, list.toString());
    }

    @Test
    void testToString() {
        Assertions.assertEquals(STRING_EMPTY_LIST, list.toString());

        list.add(0, STRING_1);
        list.add(1, STRING_2);
        list.add(2, STRING_3);
        Assertions.assertEquals(STRING_1_2_3, list.toString());

        list.remove(ONE);
        Assertions.assertEquals(STRING_1_3, list.toString());
    }
}