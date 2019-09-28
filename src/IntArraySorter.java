import java.util.Random;

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

    public void quickSort(){
        int left = 0, right = array.length;
        quickSort(left, right);
    }

    private void quickSort(int left, int right) {
        if (left < right) {
            int pivotPos = choosePivotPos(left, right);
            int pivotValue = array[pivotPos];
            swap(pivotPos, right - 1);
            int pos = partition(pivotValue, left, right - 1);
            swap(pos, right - 1);
            quickSort(left, pos);
            quickSort(pos + 1, right);
        }
    }

    private int partition(int pivotValue, int left, int right) {
        while (true) {
            left = itemFromLeftPos(pivotValue, left, right);
            right = itemFromRightPos(pivotValue, left, right);
            if (left >= right) {
                return left;
            }
            swap(left, right);
        }
    }

    public void fisherYatesShuffle(int n) {
        for (int i = 0; i < n; ++i) {
            int j = new Random().nextInt(n);
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    /*-----------------------AUXILIARY  METHODS------------------------------*/

    private int itemFromLeftPos(int pivot, int left, int right) {
        for (int i = left; i < right; ++i) {
            if (!lessThanOrEqual(array[i], pivot)) {
                return i;
            }
        }
        return right;
    }

    private int itemFromRightPos(int pivot, int left, int right) {
        for (int i = right - 1; i >= left; --i) {
            if (lessThanOrEqual(array[i], pivot)) {
                return i;
            }
        }
        return left;
    }

    private int findTheSmallest(int s) {
        int smallest = s;
        for (int actual = s + 1; actual < array.length; ++actual) {
            if (lessThanOrEqual(array[actual], array[smallest])) {
                smallest = actual;
            }
        }
        return smallest;
    }

    private int choosePivotPos(int left, int right){
        return left + new Random().nextInt(right - left);
    }


    /*--------------------------RADIX SORT------------------------------*/

    private boolean maxDigitLengthReached = false;

    public void radixSort(){
        int n = 1;
        while(!maxDigitLengthReached){
            countSort(n);
            n *= 10;
        }
    }

    private void countSort(int n){
        maxDigitLengthReached = true;
        int[] sorted = new int[array.length];
        int[] buckets = new int[10];
        count(buckets, n);
        if(!maxDigitLengthReached){
            sumCount(buckets);
            sort(buckets, sorted, n);
            copyArray(sorted);
        }
    }

    private void count(int[] buckets, int n){
        for(int i = 0; i < array.length;++i) {
            int value = array[i] / n;
            int digit = value % 10;
            if (value > 0) {
                maxDigitLengthReached = false;
            }
            buckets[digit] += 1;
        }
    }

    private void sumCount(int[] buckets){
        for(int i = 1; i < buckets.length; ++i){
            buckets[i] += buckets[i - 1];
        }
    }

    private void sort(int[] buckets, int[] sorted, int n){
        for(int i = array.length - 1; i >= 0; --i){
            int digit = (array[i]/n)%10;
            int pos = buckets[digit] - 1;
            sorted[pos] = array[i];
            buckets[digit] -= 1;
        }
    }

    private void copyArray(int[] sorted){
        for(int i = 0; i< array.length;++i){
            array[i] = sorted[i];
        }
    }
}

