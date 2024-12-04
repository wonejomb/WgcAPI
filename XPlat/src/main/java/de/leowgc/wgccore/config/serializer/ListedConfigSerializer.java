package de.leowgc.wgccore.config.serializer;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public interface ListedConfigSerializer<T> extends ConfigSerializer<List<T>> {

    /**
     * Set a value de/serializer by the ID
     * @param pSerializer The deserializer id
     */
    ListedConfigSerializer<T> setSerializer (ResourceLocation pSerializer );

    /**
     * The value de/serializer for values.
     * @return The value serializer
     */
    ConfigSerializer<T> getValueSerializer ();

}
