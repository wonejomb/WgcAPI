package de.leowgc.wgccore.util;

public enum TriState {
    TRUE,
    FALSE,
    UNDEFINED
    ;

    public boolean isTrue () {
        return this == TRUE;
    }

    public boolean isFalse () {
        return this == FALSE;
    }

    public boolean isUndefined () {
        return this == UNDEFINED;
    }

}
