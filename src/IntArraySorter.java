import java.util.concurrent.ThreadLocalRandom;

public class IntArraySorter {
    private final int[] array;
    private int numComparisons;
    private int numSwaps;

    public int getNumComparisons() {
        return numComparisons;
    }

    public int getNumSwaps() {
        return numSwaps;
    }

    public int getArrayLength(){
        return array.length;
    }
    public IntArraySorter(int[] array) {
        this.array = array;
    }

    public boolean isSorted() {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public void swap(int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        numSwaps += 1;
    }

    public boolean lessThanOrEqual(int i1, int i2) {
        numComparisons += 1;
        return i1 <= i2;
    }

    public void bubbleSort() {
        for (int s = 0; s < array.length; s++) {
            int swaps = numSwaps;
            for (int i = array.length - 1; i > s; i--) {
                if (!lessThanOrEqual(array[i - 1], array[i])) {
                    swap(i - 1, i);
                }
            }
            if (numSwaps == swaps) {
                break;
            }
        }
    }

    public void selectionSort() {
        for (int i = 0; i < array.length - 1; ++i) {
            int smallest = findTheSmallest(i);
            if(smallest != i){
                swap(i, smallest);
            }
        }
    }

    public void quickSort(int start, int end) {
        if (start < end) {
            int pivot = partition(start, end);
            quickSort(start, pivot);
            quickSort(pivot + 1, end);
        }
    }

    public void insertionSort() {
        for (int s = 1; s < array.length; ++s) {
            int insert = s;
            for (int i = s - 1; i >= 0; i--) {
                if (!lessThanOrEqual(array[i], array[insert])) {
                    swap(i, insert);
                    insert = i;
                } else {
                    break;
                }
            }
        }
    }

    public void fisherYatesShuffle(int n) {
        for (int i = n - 1; i >= 0; --i) {
            int j = ThreadLocalRandom.current().nextInt(array.length);
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    /* --------------------PRIVATE METHODS----------------------- */

    private int findTheSmallest(int s) {
        int smallest = s;
        for (int actual = s + 1; actual < array.length; ++actual) {
            if (lessThanOrEqual(array[actual], array[smallest])) {
                smallest = actual;
            }
        }
        return smallest;
    }

     private int partition(int start, int end) {
        //Choose a pivot and swap it to the end of the array.
        int pivot = choosePivotAndSwap(start, end);
        int leftItem = start, rightItem = end - 2;
        int result;
        while (leftItem <= rightItem) {
            if(array[leftItem] < pivot){ leftItem += 1; }
            if(array[rightItem] > pivot){ rightItem -= 1; }
            if(leftItem > rightItem){
                break;
            }
            if(array[leftItem] > pivot && array[rightItem] < pivot){
                swap(leftItem, rightItem);
                leftItem += 1;
                rightItem -= 1;
            }
        }
        swap(leftItem, end - 1);
        return leftItem;
    }

    private int itemFromLeft(int pivot, int start, int end) {
        for (int i = start; i < end; ++i) {
            if (!lessThanOrEqual(array[i], pivot)) {
                return i;
            }
        }
        return end;
    }

    private int itemFromRight(int pivot, int start, int end) {
        for (int i = end - 1; i >= start; --i) {
            if (lessThanOrEqual(array[i], pivot)) {
                return i;
            }
        }
        return start;
    }

    private int choosePivotAndSwap(int start, int end) {
        int index = ThreadLocalRandom.current().nextInt(start, end);
        int pivot = array[index];
        swap(index, end - 1);
        return pivot;
    }
}



