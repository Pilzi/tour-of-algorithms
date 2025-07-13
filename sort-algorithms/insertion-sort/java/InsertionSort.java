public class InsertionSort {

    /**
     * Sorts the given array using insertion sort algorithm.
     *
     * @param array the array to sort
     */
    public static <T extends Number & Comparable<T>> T[] sort(T[] array) {

        for (int i = 1; i < array.length; i++) {
            T unsortedValue = array[i];

            // Find the correct position for unsortedValue in the sorted part
            int insertIndex = 0;
            while (insertIndex < i && array[insertIndex].compareTo(unsortedValue) < 0 ) {
                insertIndex++;
            }

            // Shift elements to the right to make space
            for (int k = i; k > insertIndex; k--) {
                array[k] = array[k - 1];
            }

            // Finally, insert the unsorted value at the correct place
            array[insertIndex] = unsortedValue;
        }
        return array;
    }
}
