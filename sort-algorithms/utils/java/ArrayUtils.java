import java.lang.reflect.Array;

public class ArrayUtils {

    /**
     * Converts the numeric input value to a specific type by creating a new array <br/>
     *
     * @param returnType as numeric type
     * @param array as array of a numeric type
     * @return array of type returnType
     */
    @SuppressWarnings({"unchecked"})
    public static <O extends Number, I extends Number> O[] convertArrayType(Class<O> returnType, I[] array) {
        // It's necessary to use Array at this point because it calls the native newArray method inside the jvm.cpp
        final O[] convertedArray = (O[]) Array.newInstance(returnType, array.length);
        // It's necessary to define convertedArray new Array because arrays have fixed types
        for (int i = 0; i < array.length; i++) {
            convertedArray[i] = NumberUtils.convertNumber(returnType, array[i]);
        }
        return convertedArray;
    }

    /**
     * Swap a and b inside the array
     *
     * @param indexA index as int
     * @param indexB index as int
     * @param array  as generic type array
     */
    public static <T> void swapValues(int indexA, int indexB, T[] array) {
        T temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }

    /**
     * Copies elements from the source array to the target array by index. <br/>
     * If the source array is longer than the destination array, the remaining elements will be ignored.
     *
     * @param source      as generic type array
     * @param destination as generic type array
     */
    public static <T> T[] copyArray(T[] source, T[] destination) {

        for (int i = 0; i < destination.length; i++) {
            destination[i] = source[i];
        }
        return destination;
    }

    /**
     * Creates a new array of the given generic type
     *
     * @param aClass class of array type
     * @param length as int representing the length of the array
     * @return array of type {@code aClass}
     * @param <T> as generic type
     */

    @SuppressWarnings({"unchecked"})
    public static <T> T[] createArray(Class<?> aClass, int length) {
        return (T[]) Array.newInstance(aClass, length);
    }
}
