package de.leowgc.wgccore.config.serializer;

import de.leowgc.wgccore.util.Constants;
import net.minecraft.resources.ResourceLocation;

public class ByteCfgSerializer implements ConfigSerializer<Byte> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "wgc_byte");

    private byte minValue = Byte.MIN_VALUE;
    private byte maxValue = Byte.MAX_VALUE;

    public String serialize(Byte pValue) {
        return "byte(%s)".formatted(pValue);
    }

    public Byte deserialize(String pValue) {
        pValue = pValue.trim().toLowerCase();

        if ( pValue.startsWith("byte(") ) {
            if ( !pValue.endsWith(")") ) throw new ConfigWgcSerializerException("Byte deserializer find that some value is parsed with 'byte(X)' but the value isn't closed with ')'");

            pValue = pValue.substring(5, pValue.length() - 1);
        }

        byte value = Byte.parseByte(pValue);
        if ( value < this.minValue || value > this.maxValue ) throw new ConfigWgcSerializerException("Byte de/serializer can not deserialize a value that is out of bounds. Bounds: [%s, %s]".formatted(this.minValue, this.maxValue));
        return value;
    }

    public void onRange(Byte pMinValue, Byte pMaxValue) {
        this.minValue = pMinValue;
        this.maxValue = pMaxValue;
    }

}
