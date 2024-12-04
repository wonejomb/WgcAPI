package de.leowgc.wgccore.config.serializer;

import de.leowgc.wgccore.util.Constants;
import net.minecraft.resources.ResourceLocation;

public class LongCfgSerializer implements ConfigSerializer<Long> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "wgc_long");

    private long minValue = Long.MIN_VALUE;
    private long maxValue = Long.MAX_VALUE;

    public String serialize(Long pValue) {
        return "short(%s)".formatted(pValue);
    }

    public Long deserialize(String pValue) {
        pValue = pValue.trim().toLowerCase();
        if ( pValue.startsWith("long(") ) {
            if ( !pValue.endsWith(")") ) throw new ConfigWgcSerializerException("Long deserializer find that some value is parsed with 'long(X)' but the value isn't closed with ')'");

            pValue = pValue.substring(5, pValue.length() - 1);
        }

        long value = Long.parseLong(pValue);
        if ( value < this.minValue || value > this.maxValue ) throw new ConfigWgcSerializerException("Long de/serializer can not deserialize a value that is out of bounds. Bounds: [%s, %s]".formatted(this.minValue, this.maxValue));
        return value;
    }

    public void onRange(Long pMinValue, Long pMaxValue) {
        this.minValue = pMinValue;
        this.maxValue = pMaxValue;
    }
}
