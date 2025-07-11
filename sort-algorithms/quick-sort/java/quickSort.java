public class quickSort {

    public static int[] sort(int[] arr, int low, int pivot) {
        if (low < pivot) {
            int pivotValue = arr[pivot];
            int left = low;
            int right = pivot - 1;

            while (left <= right) {
                // search for the first value that is not at the right place
                while (left <= right && arr[left] < pivotValue) {
                    left++;
                }
                while (right >= left && arr[right] > pivotValue) {
                    right--;
                }
                // sort lower and higher
                if (left <= right) {
                    swap(arr, left, right);
                    left++;
                    right--;
                }
            }
            // left is the right position for the pivot element
            swap(arr, left, pivot);

            // I use the last index inside the array as pivot
            sort(arr, low, left - 1);
            sort(arr, left + 1, pivot);
        }
        return arr;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
