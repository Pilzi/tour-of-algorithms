public class SelectionSort {

    /**
     * returns the array sorted by the selection sort algorithm
     *
     * @param array          as int
     * @param ascendingOrder boolean
     */

    public static void sort(int[] array, boolean ascendingOrder) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = array.length - 1;
            for (int j = i; j < array.length; j++) {
                if (ascendingOrder && array[j] < array[minIndex]) {
                    minIndex = j;
                } else if (!ascendingOrder && array[j] > array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }
}
