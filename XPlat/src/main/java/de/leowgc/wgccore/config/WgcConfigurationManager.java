package de.leowgc.wgccore.config;

import com.google.common.collect.Maps;
import de.leowgc.wgccore.config.serializer.*;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.function.Supplier;

public final class WgcConfigurationManager {
    public static WgcConfigurationManager INSTANCE;

    private final Map<ResourceLocation, Supplier<ConfigSerializer<?>>> serializers = Maps.newHashMap();

    public WgcConfigurationManager () {
        if ( INSTANCE != null )
            throw new IllegalStateException("Can not re-instance wgc configuration manager.");
        INSTANCE = this;

        this.registerSerializer(ByteCfgSerializer.ID, ByteCfgSerializer::new);
        this.registerSerializer(ShortCfgSerializer.ID, ShortCfgSerializer::new);
        this.registerSerializer(IntCfgSerializer.ID, IntCfgSerializer::new);
        this.registerSerializer(FloatCfgSerializer.ID, FloatCfgSerializer::new);
        this.registerSerializer(LongCfgSerializer.ID, LongCfgSerializer::new);
        this.registerSerializer(DoubleWgcSerializer.ID, DoubleWgcSerializer::new);
        this.registerSerializer(StringCfgSerializer.ID, ShortCfgSerializer::new);
        this.registerSerializer(ResourceLocationCfgSerializer.ID, ResourceLocationCfgSerializer::new);
        this.registerSerializer(ListCfgSerializer.ID, ListCfgSerializer::new);
    }

    public void registerSerializer ( ResourceLocation pID, Supplier<ConfigSerializer<?>> pSerializer ) {
        if ( this.serializers.putIfAbsent(pID, pSerializer) != null )
            throw new IllegalArgumentException("WGC Configuration manager has currently a serializer with the ID: " + pID);
    }

    @SuppressWarnings("unchecked")
    public <T> ConfigSerializer<T> getSerializer ( ResourceLocation pSerializerId ) {
        ConfigSerializer<T> serializer = (ConfigSerializer<T>) this.serializers.get(pSerializerId);
        if ( serializer == null ) throw new ConfigWgcException("No serializer with id: '%s' registered.".formatted(pSerializerId));
        return serializer;
    }

}
