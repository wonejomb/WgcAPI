package de.leowgc.wgccore.config.serializer;

import de.leowgc.wgccore.util.Constants;
import net.minecraft.resources.ResourceLocation;

public class DoubleWgcSerializer implements ConfigSerializer<Double> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "wgc_double");

    private double minValue = Double.MIN_VALUE;
    private double maxValue = Double.MAX_VALUE;

    public String serialize(Double pValue) {
        return "double(%s)".formatted(pValue);
    }

    public Double deserialize(String pValue) {
        pValue = pValue.trim().toLowerCase();

        if ( pValue.startsWith("double(") ) {
            if ( !pValue.endsWith(")") ) throw new ConfigWgcSerializerException("Double deserializer find that some value is parsed with 'double(X)' but the value isn't closed with ')'");

            pValue = pValue.substring(7, pValue.length() - 1);
        }

        double value = Double.parseDouble(pValue);
        if ( value < this.minValue || value > this.maxValue ) throw new ConfigWgcSerializerException("Double de/serializer can not deserialize a value that is out of bounds. Bounds: [%s, %s]".formatted(this.minValue, this.maxValue));
        return value;
    }

    public void onRange(Double pMinValue, Double pMaxValue) {
        this.minValue = pMinValue;
        this.maxValue = pMaxValue;
    }
}
