/**
 * Utility methods to box and unbox primitive arrays and their wrapper types.
 *
 * Why no generics?
 * - Java generics do not support primitives (e.g., int, float).
 * - Reflection or Streams are much slower.
 * - Overloaded methods with direct array access are the most performant approach.
 */
public class BoxingUtils {

    // ---------------------
    // BOXING METHODS
    // ---------------------

    /** Box int[] -> Integer[] */
    public static Integer[] box(int[] array) {
        final Integer[] boxed = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            boxed[i] = array[i];
        }
        return boxed;
    }

    /** Box float[] -> Float[] */
    public static Float[] box(float[] array) {
        final Float[] boxed = new Float[array.length];
        for (int i = 0; i < array.length; i++) {
            boxed[i] = array[i];
        }
        return boxed;
    }

    /** Box double[] -> Double[] */
    public static Double[] box(double[] array) {
        final Double[] boxed = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            boxed[i] = array[i];
        }
        return boxed;
    }

    /** Box long[] -> Long[] */
    public static Long[] box(long[] array) {
        final Long[] boxed = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            boxed[i] = array[i];
        }
        return boxed;
    }

    /** Box short[] -> Short[] */
    public static Short[] box(short[] array) {
        final Short[] boxed = new Short[array.length];
        for (int i = 0; i < array.length; i++) {
            boxed[i] = array[i];
        }
        return boxed;
    }

    /** Box byte[] -> Byte[] */
    public static Byte[] box(byte[] array) {
        final Byte[] boxed = new Byte[array.length];
        for (int i = 0; i < array.length; i++) {
            boxed[i] = array[i];
        }
        return boxed;
    }

    // ---------------------
    // UNBOXING METHODS
    // ---------------------

    /** Unbox Integer[] -> int[] */
    public static int[] unbox(Integer[] array) {
        final int[] unboxed = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            unboxed[i] = array[i];
        }
        return unboxed;
    }

    /** Unbox Float[] -> float[] */
    public static float[] unbox(Float[] array) {
        final float[] unboxed = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            unboxed[i] = array[i];
        }
        return unboxed;
    }

    /** Unbox Double[] -> double[] */
    public static double[] unbox(Double[] array) {
        final double[] unboxed = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            unboxed[i] = array[i];
        }
        return unboxed;
    }

    /** Unbox Long[] -> long[] */
    public static long[] unbox(Long[] array) {
        final long[] unboxed = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            unboxed[i] = array[i];
        }
        return unboxed;
    }

    /** Unbox Short[] -> short[] */
    public static short[] unbox(Short[] array) {
        final short[] unboxed = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            unboxed[i] = array[i];
        }
        return unboxed;
    }

    /** Unbox Byte[] -> byte[] */
    public static byte[] unbox(Byte[] array) {
        final byte[] unboxed = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            unboxed[i] = array[i];
        }
        return unboxed;
    }
}
