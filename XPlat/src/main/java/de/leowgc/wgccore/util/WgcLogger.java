package de.leowgc.wgccore.util;

import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

public class WgcLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(WgcLogger.class);

    private WgcLogger () {}

    public static void info ( String pLog ) {
        WgcLogger.info(Component.literal(pLog));
    }

    public static void info ( Component pInfoLog ) {
        LOGGER.info("[WGC | INFO]: {}", pInfoLog.getString());
    }

    public static void debug ( String pLog ) {
        WgcLogger.debug(Component.literal(pLog));
    }

    public static void debug ( Component pDebugLog ) {
        LOGGER.debug("[WGC | DEBUG]: {}", pDebugLog.getString());
    }

    public static void warn ( String pLog ) {
        WgcLogger.warn(Component.literal(pLog));
    }

    public static void warn ( Component pWarnLog ) {
        LOGGER.warn("[WGC | WARN]: {}", pWarnLog.getString());
    }

    public static void error ( String pLog ) {
        WgcLogger.error(Component.literal(pLog));
    }

    public static void error ( Component pErrorLog ) {
        LOGGER.error("[WGC | ERROR]: {}", pErrorLog.getString());
    }

    public static void info ( Level pLevel, String pLog ) {
        WgcLogger.log(pLevel, Component.literal(pLog));
    }

    public static void log ( Level pLevel, Component pLog ) {
        LOGGER.atLevel(pLevel).log(pLog.getString());
    }

}
