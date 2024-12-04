package de.leowgc.wgccore.config.serializer;

import de.leowgc.wgccore.util.Constants;
import net.minecraft.resources.ResourceLocation;

public class FloatCfgSerializer implements ConfigSerializer<Float> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "wgc_float");

    private float minValue = Float.MIN_VALUE;
    private float maxValue = Float.MAX_VALUE;

    public String serialize(Float pValue) {
        return "float(%s)".formatted(pValue);
    }

    public Float deserialize(String pValue) {
        pValue = pValue.trim().toLowerCase();
        if ( pValue.startsWith("float(") ) {
            if ( !pValue.endsWith(")") ) throw new ConfigWgcSerializerException("Float deserializer find that some value is parsed with 'float(X)' but the value isn't closed with ')'");

            pValue = pValue.substring(6, pValue.length() - 1);
        }

        float value = Float.parseFloat(pValue);
        if ( value < this.minValue || value > this.maxValue ) throw new ConfigWgcSerializerException("Float de/serializer can not deserialize a value that is out of bounds. Bounds: [%s, %s]".formatted(this.minValue, this.maxValue));
        return value;
    }

    public void onRange(Float pMinValue, Float pMaxValue) {
        this.minValue = pMinValue;
        this.maxValue = pMaxValue;
    }
}
