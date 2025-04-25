public class SelectionSort {

    /**
     * returns the given array sorted by the selection sort algorithm
     * @param array as int
     * @param ascendingOrder boolean
     */

    public static void sort(int[] array, boolean ascendingOrder) {
        for (int i = 0; i < array.length; i++) {
            int nextInOrderIndex = array.length - 1;
            for (int j = i; j < array.length; j++) {
                if (ascendingOrder) {
                    if (array[j] < array[nextInOrderIndex]) {
                        nextInOrderIndex = j;
                    }
                } else {
                    if (array[j] > array[nextInOrderIndex]) {
                        nextInOrderIndex = j;
                    }
                }
            }
            int temp = array[i];
            array[i] = array[nextInOrderIndex];
            array[nextInOrderIndex] = temp;
        }
    }
}
