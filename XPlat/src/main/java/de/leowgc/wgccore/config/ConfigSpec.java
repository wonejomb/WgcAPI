package de.leowgc.wgccore.config;

import de.leowgc.wgccore.config.serializer.ConfigSerializer;

import java.util.Optional;

public class ConfigSpec<T> {

    private final String identifier;
    private final String description;
    private final ConfigSerializer<T> serializer;
    private final T defaultValue;
    private volatile T currentValue;

    ConfigSpec ( String pIdentifier, String pDescription, ConfigSerializer<T> pSerializer, T pDefaultValue ) {
        this.identifier = pIdentifier;
        this.description = pDescription;
        this.serializer = pSerializer;
        this.defaultValue = pDefaultValue;
        this.currentValue = pDefaultValue;
    }

    public ConfigSpec<T> set ( T pNewValue ) {
        if ( pNewValue != this.currentValue )
            this.currentValue = pNewValue;

        return this;
    }

    public T deserialize () {
        return this.deserialize(this.serializeValue());
    }

    public T deserialize ( String pValue ) {
        T value = this.serializer.deserialize(pValue);
        return this.set(value).currentValue;
    }

    public String serializeValue () {
        return this.serializer.serialize (this.currentValue);
    }

    public String getIdentifier () {
        return this.identifier;
    }

    public Optional<String> getDescription () {
        return Optional.ofNullable(this.description);
    }

    public T getDefaultValue () {
        return this.defaultValue;
    }

}