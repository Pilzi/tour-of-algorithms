import java.lang.reflect.Array;

public class ArrayUtils {

    /**
     * Converts the numeric input value to a specific type by creating a new array <br/>
     *
     * @param returnType as numeric type
     * @param array array of a numeric type
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
     * @param indexA index as int
     * @param indexB index as int
     * @param array as generic type array
     */
    public static <T> void swapValues(int indexA,int indexB,T[] array) {
        T temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }
}
