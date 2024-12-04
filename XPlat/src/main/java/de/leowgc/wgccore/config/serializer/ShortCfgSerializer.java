package de.leowgc.wgccore.config.serializer;

import de.leowgc.wgccore.util.Constants;
import net.minecraft.resources.ResourceLocation;

public class ShortCfgSerializer implements ConfigSerializer<Short> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "wgc_short");

    private short minValue = Short.MIN_VALUE;
    private short maxValue = Short.MAX_VALUE;

    public String serialize(Short pValue) {
        return "short(%s)".formatted(pValue);
    }

    public Short deserialize(String pValue) {
        pValue = pValue.trim().toLowerCase();
        if ( pValue.startsWith("short(") ) {
            if ( !pValue.endsWith(")") ) throw new ConfigWgcSerializerException("Short deserializer find that some value is parsed with 'short(X)' but the value isn't closed with ')'");

            pValue = pValue.substring(6, pValue.length() - 1);
        }

        short value = Short.parseShort(pValue);
        if ( value < this.minValue || value > this.maxValue ) throw new ConfigWgcSerializerException("Short de/serializer can not deserialize a value that is out of bounds. Bounds: [%s, %s]".formatted(this.minValue, this.maxValue));
        return value;
    }

    public void onRange(Short pMinValue, Short pMaxValue) {
        this.minValue = pMinValue;
        this.maxValue = pMaxValue;
    }
}
