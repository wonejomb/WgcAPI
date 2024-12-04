package de.leowgc.wgccore.config.serializer;

import de.leowgc.wgccore.util.Constants;
import net.minecraft.resources.ResourceLocation;

public class StringCfgSerializer implements ConfigSerializer<String> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "wgc_str");

    public String serialize(String pValue) {
        return "str(\"%s\")".formatted(pValue);
    }

    public String deserialize(String pValue) {
        if ( pValue.startsWith("str(\"") ) {
            if ( !pValue.endsWith("\")") ) throw new ConfigWgcSerializerException("String serializer can not de-serializer a value that is parsed with str(\"Yout ext goes here\") but don't close with ')'");

            pValue = pValue.substring(5, pValue.length() - 2);
        }

        return pValue;
    }
}
