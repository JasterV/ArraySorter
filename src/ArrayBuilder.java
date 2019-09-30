/**
 * This class helps us to build and copy arrays.
 */
public class ArrayBuilder {
    /**
     * Builds a sorted array of integers from 0 to length.
     * @param length the array length
     * @return the array created.
     */
    public static int[] buildArray(int length) {
        int[] array = new int[length];
        for (int j = 0; j < array.length; ++j) {
            array[j] = j;
        }
        return array;
    }

    /**
     * Builds a sorted array of integers from length to 0.
     * @param length the array length
     * @return the array created.
     */
    public static int[] buildReversedArray(int length) {
        int[] array = new int[length];
        for (int i = array.length - 1; i >= 0; --i) {
            array[array.length - 1 - i] = i;
        }
        return array;
    }
}
