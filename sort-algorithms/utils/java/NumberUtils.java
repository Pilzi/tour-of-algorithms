public class NumberUtils {

    /**
     * Converts a single Number to the given numeric target type.
     * <br/>
     * Supported types are:
     * <br/>
     * {@link Integer} / {@code int}, <br/>
     * {@link Float} / {@code float}, <br/>
     * {@link Double} / {@code double}, <br/>
     * {@link Long} / {@code long}, <br/>
     * {@link Short} / {@code short}, <br/>
     * {@link Byte} / {@code byte}. <br/>
     */
    @SuppressWarnings({"unchecked"})
    public static <S extends Number, T extends Number> T convertNumber(Class<T> returnType, S number) {
        if (returnType.equals(Float.class) || returnType.equals(float.class)) {
            return (T) Float.valueOf(number.floatValue());
        } else if (returnType.equals(Double.class) || returnType.equals(double.class)) {
            return (T) Double.valueOf(number.doubleValue());
        } else if (returnType.equals(Integer.class) || returnType.equals(int.class)) {
            return (T) Integer.valueOf(number.intValue());
        } else if (returnType.equals(Long.class) || returnType.equals(long.class)) {
            return (T) Long.valueOf(number.longValue());
        } else if (returnType.equals(Short.class) || returnType.equals(short.class)) {
            return (T) Short.valueOf(number.shortValue());
        } else if (returnType.equals(Byte.class) || returnType.equals(byte.class)) {
            return (T) Byte.valueOf(number.byteValue());
        }
        throw new ClassCastException("Cannot cast " + number.getClass().getName() + " to " + returnType);
    }
}
