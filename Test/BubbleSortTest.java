import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {
    @Test
    void array_ordenado() {
        IntArraySorter sorter = new IntArraySorter(ArrayBuilder.buildArray(50));
        sorter.bubbleSort();
        assertTrue(sorter.isSorted());
    }

    @Test
    void array_del_reves() {
        IntArraySorter sorter = new IntArraySorter(ArrayBuilder.buildReversedArray(50));
        sorter.bubbleSort();
        assertTrue(sorter.isSorted());
    }

    @Test
    void array_poco_desordenado() {
        IntArraySorter sorter = new IntArraySorter(ArrayBuilder.buildArray(50));
        sorter.fisherYatesShuffle(20);
        sorter.bubbleSort();
        assertTrue(sorter.isSorted());
    }

    @Test
    void array_muy_desordenado() {
        IntArraySorter sorter = new IntArraySorter(ArrayBuilder.buildArray(50));
        sorter.fisherYatesShuffle(50);
        sorter.bubbleSort();
        assertTrue(sorter.isSorted());
    }
}