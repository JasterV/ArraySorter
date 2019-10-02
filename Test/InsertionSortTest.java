import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {
    static final int length = 50;

    @Test
    void array_ordenado() {
        IntArraySorter sorter = new IntArraySorter(ArrayBuilder.buildArray(length));
        sorter.insertionSort();
        assertTrue(sorter.isSorted());
    }

    @Test
    void array_del_reves() {
        IntArraySorter sorter = new IntArraySorter(ArrayBuilder.buildReversedArray(length));
        sorter.insertionSort();
        assertTrue(sorter.isSorted());
    }

    @Test
    void array_poco_desordenado() {
        IntArraySorter sorter = new IntArraySorter(ArrayBuilder.buildArray(length));
        sorter.fisherYatesShuffle(length/3);
        sorter.insertionSort();
        assertTrue(sorter.isSorted());
    }

    @Test
    void array_muy_desordenado() {
        IntArraySorter sorter = new IntArraySorter(ArrayBuilder.buildArray(length));
        sorter.fisherYatesShuffle(length);
        sorter.insertionSort();
        assertTrue(sorter.isSorted());
    }
}