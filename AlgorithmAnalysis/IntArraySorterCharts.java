/**
 *This class runs the sort algorithms in some ways and plot the results in charts.
 */
public class IntArraySorterCharts {
    private final int xAxisMaxValues = 200;
    private int xAxisValue;
    private int shuffleGrade;
    private int[] array;
    private IntArraySorter sorter;

    private double[][] bubbleSortSwaps = new double[2][xAxisMaxValues];
    private double[][] insertionSortSwaps = new double[2][xAxisMaxValues];
    private double[][] selectionSortSwaps = new double[2][xAxisMaxValues];
    private double[][] quickSortSwaps = new double[2][xAxisMaxValues];
    private double[][] radixSortSwaps = new double[2][xAxisMaxValues];

    private double[][] bubbleSortComps = new double[2][xAxisMaxValues];
    private double[][] insertionSortComps = new double[2][xAxisMaxValues];
    private double[][] selectionSortComps = new double[2][xAxisMaxValues];
    private double[][] quickSortComps = new double[2][xAxisMaxValues];
    private double[][] radixSortComps = new double[2][xAxisMaxValues];

    /**
     * Runs the sort algorithms increasing the array shuffle grade from 0 to array.length (XAxisMaxValues).
     * Set the array length constant.
     * Creates the charts from the results.
     */
    private void shuffleGradeChart() {
        array = ArrayBuilder.buildArray(xAxisMaxValues);
        for (shuffleGrade = 0; shuffleGrade < xAxisMaxValues; ++shuffleGrade) {
            xAxisValue = shuffleGrade;
            testBubbleSortAndStoreResults(shuffleGrade, array.length, xAxisValue);
            testInsertionSortAndStoreResults(shuffleGrade, array.length, xAxisValue);
            testSelectionSortAndStoreResults(shuffleGrade, array.length, xAxisValue);
            testQuickSortAndStoreResults(shuffleGrade, array.length, xAxisValue);
            testRadixSortAndStoreResults(shuffleGrade, array.length, xAxisValue);
        }
        buildLinealCharts("Shuffle Grade");
    }

    /**
     * Runs the sort algorithms increasing the array length from 0 to XAxisMaxValues .
     * Set the shuffle grade constant.
     * Creates the charts from the results.
     */
    private void lengthGradeChart(){
        for (int arrayLength = 0; arrayLength < xAxisMaxValues; ++arrayLength) {
            xAxisValue = arrayLength;
            shuffleGrade = arrayLength;
            testBubbleSortAndStoreResults(shuffleGrade, arrayLength, xAxisValue);
            testInsertionSortAndStoreResults(shuffleGrade, arrayLength, xAxisValue);
            testSelectionSortAndStoreResults(shuffleGrade, arrayLength, xAxisValue);
            testQuickSortAndStoreResults(shuffleGrade, arrayLength, xAxisValue);
            testRadixSortAndStoreResults(shuffleGrade, arrayLength, xAxisValue);
        }
        buildLinealCharts("Array length");
    }

    private void testBubbleSortAndStoreResults(int shuffleGrade, int arrayLength, int XAxisData){
        buildSorterAndShuffle(shuffleGrade, arrayLength);
        sorter.bubbleSort();
        addValues(bubbleSortSwaps, XAxisData, sorter.getNumSwaps());
        addValues(bubbleSortComps, XAxisData, sorter.getNumComparisons());
    }

    private void testInsertionSortAndStoreResults(int shuffleGrade, int arrayLength, int XAxisData){
        buildSorterAndShuffle(shuffleGrade, arrayLength);
        sorter.insertionSort();
        addValues(insertionSortSwaps, XAxisData, sorter.getNumSwaps());
        addValues(insertionSortComps, XAxisData, sorter.getNumComparisons());
    }
    private void testSelectionSortAndStoreResults(int shuffleGrade, int arrayLength, int XAxisData){
        buildSorterAndShuffle(shuffleGrade, arrayLength);
        sorter.selectionSort();
        addValues(selectionSortSwaps, XAxisData, sorter.getNumSwaps());
        addValues(selectionSortComps, XAxisData, sorter.getNumComparisons());
    }

    private void testQuickSortAndStoreResults(int shuffleGrade, int arrayLength, int XAxisData) {
        buildSorterAndShuffle(shuffleGrade, arrayLength);
        sorter.quickSort();
        addValues(quickSortSwaps, XAxisData, sorter.getNumSwaps());
        addValues(quickSortComps, XAxisData, sorter.getNumComparisons());
    }

    private void testRadixSortAndStoreResults(int shuffleGrade, int arrayLength, int XAxisData) {
        buildSorterAndShuffle(shuffleGrade, arrayLength);
        sorter.radixSort();
        addValues(radixSortSwaps, XAxisData, sorter.getNumSwaps());
        addValues(radixSortComps, XAxisData, sorter.getNumComparisons());
    }

    private void buildLinealCharts(String xAxisName){
        LinearChartBuilder chart = new LinearChartBuilder("Num Swaps by " + xAxisName, "Swaps", xAxisName);
        chart.buildLinearChart(bubbleSortSwaps, insertionSortSwaps, selectionSortSwaps, quickSortSwaps, radixSortSwaps);
        chart = new LinearChartBuilder("Num Comparisons by " + xAxisName, "Comparisons", xAxisName);
        chart.buildLinearChart(bubbleSortComps, insertionSortComps, selectionSortComps, quickSortComps, radixSortComps);
    }

    private void addValues(double[][] array, int x, int y) {
        array[0][x] = x;
        array[1][x] = y;
    }

    private void buildSorterAndShuffle(int shuffleLevel, int arrayLength) {
        array = ArrayBuilder.buildArray(arrayLength);
        sorter = new IntArraySorter(array);
        sorter.fisherYatesShuffle(shuffleLevel);
    }

    public static void main(String[] args) {
        IntArraySorterCharts test = new IntArraySorterCharts();
        test.run();
    }

    public void run() {
        shuffleGradeChart();
        lengthGradeChart();
    }
}