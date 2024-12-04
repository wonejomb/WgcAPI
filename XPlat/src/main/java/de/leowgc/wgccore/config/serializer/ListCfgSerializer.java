package de.leowgc.wgccore.config.serializer;

import de.leowgc.wgccore.config.WgcConfigurationManager;
import de.leowgc.wgccore.util.Constants;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListCfgSerializer<T> implements ListedConfigSerializer<T> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "wgc_list");

    private ConfigSerializer<T> serializer;

    public String serialize ( List<T> pValue ) {
        return pValue.stream().map(this.getValueSerializer()::serialize).collect(Collectors.joining(", "));
    }

    public List<T> deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.startsWith("[") ) {
            if ( !pValue.endsWith("]") ) throw new ConfigWgcSerializerException("No closing brace found. List must have no braces, or be wrapped in [ and ].");

            pValue = pValue.substring(1, pValue.length() - 1);
        }
        String[] split = pValue.split(",");

        return Arrays.stream(split).map(String::trim).filter(s -> !s.isEmpty()).map(this.getValueSerializer()::deserialize).toList();
    }

    public ListedConfigSerializer<T> setSerializer(ResourceLocation pSerializer) {
        this.serializer = WgcConfigurationManager.INSTANCE.getSerializer(pSerializer);

        return this;
    }

    public ConfigSerializer<T> getValueSerializer() {
        return this.serializer;
    }
}
