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


    public void fisherYatesShuffle(int n) {
            for (int i = 0; i < n; ++i) {
                int j = new Random().nextInt(n);
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
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
    public void radix() {
        int n = 1;
        int d = modMax();
        for (int i = 0; i < d; i++) {
            countsort(n);
            n = n * 10;
        }


    }

    private int modMax() {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        int n = 10;
        int count = 1;
        while (true) {
            int d = (max / n) % 10;
            if (d == 0) {
                return count;
            }
            n=n*10;
            count++;
        }
    }

    private void countsort(int n) {
        int[] orden = new int[array.length];
        int[] count = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i=0; i<array.length; ++i){
            ++count[((array[i])/n)%10];
        }
        for (int i=1; i<10; ++i) {
            count[i] += count[i-1];
        }
        for (int i = 0; i<n; ++i) {
            orden[count[((array[i])/n)%10]-1] = array[i];
            --count[((array[i])/n)%10];
        }

        for (int i =0;i<array.length;i++){
            array[i]=orden[i];
        }


    }

}

