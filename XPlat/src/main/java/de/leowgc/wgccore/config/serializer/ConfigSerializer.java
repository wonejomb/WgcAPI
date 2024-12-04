package de.leowgc.wgccore.config.serializer;

public interface ConfigSerializer<T> {

    /**
     * Serialize the given value.
     * @param pValue The given value
     * @return The serialized value
     * @since 1.0.0 - 1.21.4
     */
    String serialize ( T pValue );

    /**
     * Deserialize the given value
     * @param pValue The given value
     * @return The deserialized value
     * @since 1.0.0 - 1.21.4
     */
    T deserialize ( String pValue );

    /**
     * Set a range of values
     * @param pMinValue The min range value
     * @param pMaxValue The max range value
     * @throws IllegalStateException If tries to set a range and serializer isn't numeric
     */
    default void onRange ( T pMinValue, T pMaxValue ) {
        if ( !(pMinValue instanceof Number) ) throw new IllegalStateException("De/Serializer isn't of number type, can not set a range min value.");
        if ( !(pMaxValue instanceof Number) ) throw new IllegalStateException("De/Serializer isn't of number type, can not set a range max value.");
    }

}
