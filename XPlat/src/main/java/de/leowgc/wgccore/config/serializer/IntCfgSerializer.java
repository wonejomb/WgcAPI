package de.leowgc.wgccore.config.serializer;

import de.leowgc.wgccore.util.Constants;
import net.minecraft.resources.ResourceLocation;

public class IntCfgSerializer implements ConfigSerializer<Integer> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "wgc_int");

    private int minValue = Integer.MIN_VALUE;
    private int maxValue = Integer.MAX_VALUE;

    public String serialize(Integer pValue) {
        return "int(%s)".formatted(pValue);
    }

    public Integer deserialize(String pValue) {
        pValue = pValue.trim().toLowerCase();
        if ( pValue.startsWith("int(") ) {
            if ( !pValue.endsWith(")") ) throw new ConfigWgcSerializerException("Int deserializer find that some value is parsed with 'int(X)' but the value isn't closed with ')'");

            pValue = pValue.substring(4, pValue.length() - 1);
        }

        int value = Integer.parseInt(pValue);
        if ( value < this.minValue || value > this.maxValue ) throw new ConfigWgcSerializerException("Int de/serializer can not deserialize a value that is out of bounds. Bounds: [%s, %s]".formatted(this.minValue, this.maxValue));
        return value;
    }

    public void onRange(Integer pMinValue, Integer pMaxValue) {
        this.minValue = pMinValue;
        this.maxValue = pMaxValue;
    }
}
