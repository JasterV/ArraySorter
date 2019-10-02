import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {
    static final int length = 50;

    @Test
    void array_ordenado() {
        IntArraySorter sorter = new IntArraySorter(ArrayBuilder.buildArray(length));
        sorter.bubbleSort();
        assertTrue(sorter.isSorted());
    }

    @Test
    void array_del_reves() {
        IntArraySorter sorter = new IntArraySorter(ArrayBuilder.buildReversedArray(length));
        sorter.bubbleSort();
        assertTrue(sorter.isSorted());
    }

    @Test
    void array_poco_desordenado() {
        IntArraySorter sorter = new IntArraySorter(ArrayBuilder.buildArray(length));
        sorter.fisherYatesShuffle(length/3);
        sorter.bubbleSort();
        assertTrue(sorter.isSorted());
    }

    @Test
    void array_muy_desordenado() {
        IntArraySorter sorter = new IntArraySorter(ArrayBuilder.buildArray(length));
        sorter.fisherYatesShuffle(length);
        sorter.bubbleSort();
        assertTrue(sorter.isSorted());
    }
}