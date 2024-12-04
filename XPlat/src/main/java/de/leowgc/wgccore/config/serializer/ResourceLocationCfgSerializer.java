package de.leowgc.wgccore.config.serializer;

import de.leowgc.wgccore.util.Constants;
import net.minecraft.resources.ResourceLocation;

public class ResourceLocationCfgSerializer implements ConfigSerializer<ResourceLocation> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "wgc_resource_location");

    public String serialize(ResourceLocation pValue) {
        return "loc(%s)".formatted(pValue);
    }

    public ResourceLocation deserialize(String pValue) {
        pValue = pValue.trim();

        if ( pValue.startsWith("loc(") ) {
            if ( !pValue.endsWith(")") ) throw new ConfigWgcSerializerException("Resource locaton serializer can not de-serialize a value that is not closed on 'loc()'");

            pValue = pValue.substring(4, pValue.length() - 1);
        }

        return ResourceLocation.parse (pValue);
    }

}
