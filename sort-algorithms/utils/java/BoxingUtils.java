/**
 * Utility methods to box primitive arrays into their wrapper type arrays. <br/>
 *
 * Why no generics? <br/>
 * - Java generics do not support primitives (e.g., int, float). <br/>
 * - Reflection or Streams are much slower. <br/>
 * - Overloaded methods with direct array access are the most performant approach. <br/>
 */
public class BoxingUtils {

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
        final int len = array.length;
        final Long[] boxed = new Long[len];
        for (int i = 0; i < len; i++) {
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
}
