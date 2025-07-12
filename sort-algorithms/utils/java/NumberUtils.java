public class NumberUtils {

    /**
     * Converts a single Number to another the given number format class
     * Supported types: <br/>
     * {@link Integer} / {@code int}, <br/>
     * {@link Float} / {@code float}, <br/>
     * {@link Double} / {@code double}, <br/>
     * {@link Long} / {@code long}.
     */
    @SuppressWarnings({"unchecked"})
    public static <S extends Number, T> T convertNumber(Class<T> returnType, S number) {
        if (isNumeric(number)) {
            if (returnType.equals(Float.class) || returnType.equals(float.class)) {
                return (T) Float.valueOf(number.floatValue());
            } else if (returnType.equals(Double.class) || returnType.equals(double.class)) {
                return (T) Double.valueOf(number.doubleValue());
            } else if (returnType.equals(Integer.class) || returnType.equals(int.class)) {
                return (T) Integer.valueOf(number.intValue());
            } else if (returnType.equals(Long.class) || returnType.equals(long.class)) {
                return (T) Long.valueOf(number.longValue());
            }
        }
        throw new ClassCastException("Cannot cast " + number.getClass().getName() + " to " + returnType);
    }

    /**
     * Check if the input type is instanceOf a numeric type
     * Supported types: <br/>
     * {@link Integer} / {@code int}, <br/>
     * {@link Float} / {@code float}, <br/>
     * {@link Double} / {@code double}, <br/>
     * {@link Long} / {@code long}.
     */
    public static <I> boolean isNumeric(I input) {
        return input instanceof Float || input instanceof Integer || input instanceof Double || input instanceof Long;
    }
}
