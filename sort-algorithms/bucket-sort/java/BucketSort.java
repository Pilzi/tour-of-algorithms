public class BucketSort {

    // I use a hardcoded amount bucket for each decimal like [0.0, 0.1, 0.2...]
    public static int BUCKET_COUNT = 10;

    public static float[] sort(float[] arr) {
        float[][] buckets = new float[BUCKET_COUNT][0]; // I initialize an empty float array for each bucket and grow it as needed

        // sort each float in a matching bucket
        for (float floatToSort : arr) {
            int bucketIndex = (int) (floatToSort * BUCKET_COUNT);
            int selectedBucketLength = buckets[bucketIndex].length;
            buckets[bucketIndex] = growBucketByOne(buckets, bucketIndex);
            buckets[bucketIndex][selectedBucketLength] = floatToSort;
        }

        float[] result = new float[arr.length];
        int resultIndex = 0;

        for (float[] floats : buckets) {
            float[] sortedBucket = InsertionSort.sort(floats); // WIP
            for (float bucket : sortedBucket) {
                result[resultIndex] = bucket;
                resultIndex++;
            }
        }
        return result;
    }

    /**
     * I don't use the array copy method because I want to prevent using predefined functions
     */

    public static float[] growBucketByOne(float[][] buckets, int bucketIndex) {
        int extendedLength = buckets[bucketIndex].length + 1;
        float[] newRow =  new float[extendedLength];
     // alternative: System.arraycopy(buckets[bucketIndex], 0, newRow, 0, buckets[bucketIndex].length);
        for (int i = 0; i < buckets[bucketIndex].length; i++) {
            newRow[i] = buckets[bucketIndex][i];
        }
        return newRow;
    }

}
