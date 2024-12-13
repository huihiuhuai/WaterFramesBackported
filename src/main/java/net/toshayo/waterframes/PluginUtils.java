package net.toshayo.waterframes;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;

public class PluginUtils {
    public static final String ARCH = System.getProperty("os.arch").toLowerCase().trim();
    private static final String OS = System.getProperty("os.name").toLowerCase().trim();

    public static void info(Logger logger, String message, Object p0) {
        logger.info(message, p0);
    }

    public static void info(Logger logger, String message, Object p0, Object p1) {
        logger.info(message, p0, p1);
    }

    public static void info(Logger logger, String message, Object p0, Object p1, Object p2) {
        logger.info(message, p0, p1, p2);
    }

    public static void info(Logger logger, String message, Object p0, Object p1, Object p2, Object p3) {
        logger.info(message, p0, p1, p2, p3);
    }

    public static void info(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        logger.info(message, p0, p1, p2, p3, p4);
    }

    public static void info(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        logger.info(message, p0, p1, p2, p3, p4, p5);
    }

    public static void info(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        logger.info(message, p0, p1, p2, p3, p4, p5, p6);
    }

    public static void info(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        logger.info(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    public static void info(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        logger.info(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public static void info(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        logger.info(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    public static void info(Logger logger, Marker marker, String message, Object p0) {
        logger.info(marker, message, p0);
    }

    public static void info(Logger logger, Marker marker, String message, Object p0, Object p1) {
        logger.info(marker, message, p0, p1);
    }

    public static void info(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2) {
        logger.info(marker, message, p0, p1, p2);
    }

    public static void info(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
        logger.info(marker, message, p0, p1, p2, p3);
    }

    public static void info(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        logger.info(marker, message, p0, p1, p2, p3, p4);
    }

    public static void info(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        logger.info(marker, message, p0, p1, p2, p3, p4, p5);
    }

    public static void info(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        logger.info(marker, message, p0, p1, p2, p3, p4, p5, p6);
    }

    public static void info(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        logger.info(marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    public static void info(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        logger.info(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public static void info(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        logger.info(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    public static void warn(Logger logger, String message, Object p0) {
        logger.warn(message, p0);
    }

    public static void warn(Logger logger, String message, Object p0, Object p1) {
        logger.warn(message, p0, p1);
    }

    public static void warn(Logger logger, String message, Object p0, Object p1, Object p2) {
        logger.warn(message, p0, p1, p2);
    }

    public static void warn(Logger logger, String message, Object p0, Object p1, Object p2, Object p3) {
        logger.warn(message, p0, p1, p2, p3);
    }

    public static void warn(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        logger.warn(message, p0, p1, p2, p3, p4);
    }

    public static void warn(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        logger.warn(message, p0, p1, p2, p3, p4, p5);
    }

    public static void warn(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        logger.warn(message, p0, p1, p2, p3, p4, p5, p6);
    }

    public static void warn(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        logger.warn(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    public static void warn(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        logger.warn(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public static void warn(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        logger.warn(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    public static void warn(Logger logger, Marker marker, String message, Object p0) {
        logger.warn(marker, message, p0);
    }

    public static void warn(Logger logger, Marker marker, String message, Object p0, Object p1) {
        logger.warn(marker, message, p0, p1);
    }

    public static void warn(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2) {
        logger.warn(marker, message, p0, p1, p2);
    }

    public static void warn(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
        logger.warn(marker, message, p0, p1, p2, p3);
    }

    public static void warn(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        logger.warn(marker, message, p0, p1, p2, p3, p4);
    }

    public static void warn(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        logger.warn(marker, message, p0, p1, p2, p3, p4, p5);
    }

    public static void warn(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        logger.warn(marker, message, p0, p1, p2, p3, p4, p5, p6);
    }

    public static void warn(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        logger.warn(marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    public static void warn(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        logger.warn(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public static void warn(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        logger.warn(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    public static void error(Logger logger, String message, Object p0) {
        logger.error(message, p0);
    }

    public static void error(Logger logger, String message, Object p0, Object p1) {
        logger.error(message, p0, p1);
    }

    public static void error(Logger logger, String message, Object p0, Object p1, Object p2) {
        logger.error(message, p0, p1, p2);
    }

    public static void error(Logger logger, String message, Object p0, Object p1, Object p2, Object p3) {
        logger.error(message, p0, p1, p2, p3);
    }

    public static void error(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        logger.error(message, p0, p1, p2, p3, p4);
    }

    public static void error(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        logger.error(message, p0, p1, p2, p3, p4, p5);
    }

    public static void error(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        logger.error(message, p0, p1, p2, p3, p4, p5, p6);
    }

    public static void error(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        logger.error(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    public static void error(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        logger.error(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public static void error(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        logger.error(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    public static void error(Logger logger, Marker marker, String message, Object p0) {
        logger.error(marker, message, p0);
    }

    public static void error(Logger logger, Marker marker, String message, Object p0, Object p1) {
        logger.error(marker, message, p0, p1);
    }

    public static void error(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2) {
        logger.error(marker, message, p0, p1, p2);
    }

    public static void error(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
        logger.error(marker, message, p0, p1, p2, p3);
    }

    public static void error(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        logger.error(marker, message, p0, p1, p2, p3, p4);
    }

    public static void error(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        logger.error(marker, message, p0, p1, p2, p3, p4, p5);
    }

    public static void error(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        logger.error(marker, message, p0, p1, p2, p3, p4, p5, p6);
    }

    public static void error(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        logger.error(marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    public static void error(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        logger.error(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public static void error(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        logger.error(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    public static void debug(Logger logger, String message, Object p0) {
        logger.debug(message, p0);
    }

    public static void debug(Logger logger, String message, Object p0, Object p1) {
        logger.debug(message, p0, p1);
    }

    public static void debug(Logger logger, String message, Object p0, Object p1, Object p2) {
        logger.debug(message, p0, p1, p2);
    }

    public static void debug(Logger logger, String message, Object p0, Object p1, Object p2, Object p3) {
        logger.debug(message, p0, p1, p2, p3);
    }

    public static void debug(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        logger.debug(message, p0, p1, p2, p3, p4);
    }

    public static void debug(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        logger.debug(message, p0, p1, p2, p3, p4, p5);
    }

    public static void debug(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        logger.debug(message, p0, p1, p2, p3, p4, p5, p6);
    }

    public static void debug(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        logger.debug(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    public static void debug(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        logger.debug(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public static void debug(Logger logger, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        logger.debug(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    public static void debug(Logger logger, Marker marker, String message, Object p0) {
        logger.debug(marker, message, p0);
    }

    public static void debug(Logger logger, Marker marker, String message, Object p0, Object p1) {
        logger.debug(marker, message, p0, p1);
    }

    public static void debug(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2) {
        logger.debug(marker, message, p0, p1, p2);
    }

    public static void debug(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
        logger.debug(marker, message, p0, p1, p2, p3);
    }

    public static void debug(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        logger.debug(marker, message, p0, p1, p2, p3, p4);
    }

    public static void debug(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        logger.debug(marker, message, p0, p1, p2, p3, p4, p5);
    }

    public static void debug(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        logger.debug(marker, message, p0, p1, p2, p3, p4, p5, p6);
    }

    public static void debug(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
        logger.debug(marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    public static void debug(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
        logger.debug(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public static void debug(Logger logger, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
        logger.debug(marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    public static boolean Platform_is64Bit() {
        String model = System.getProperty("sun.arch.data.model", System.getProperty("com.ibm.vm.bitmode"));
        if (model != null) {
            return "64".equals(model);
        } else {
            return "x86-64".equals(ARCH) || "ia64".equals(ARCH) || "ppc64".equals(ARCH) || "ppc64le".equals(ARCH) || "sparcv9".equals(ARCH) || "mips64".equals(ARCH) || "mips64el".equals(ARCH) || "amd64".equals(ARCH) || "aarch64".equals(ARCH);
        }
    }

    public static boolean Platform_isARM() {
        return ARCH.startsWith("arm") || ARCH.startsWith("aarch");
    }

    public static boolean Platform_isWindows() {
        return OS.startsWith("windows");
    }

    public static boolean Platform_isMac() {
        return !Platform_isWindows() && !Platform_isLinux();
    }

    public static boolean Platform_isLinux() {
        return OS.startsWith("linux") && !"dalvik".equalsIgnoreCase(System.getProperty("java.vm.name"));
    }

    public static ByteBuffer MemoryAlloc_create(int pSize) {
        return BufferUtils.createByteBuffer(pSize);
    }

    public static ByteBuffer MemoryAlloc_resize(ByteBuffer pBuffer, int pByteSize) {
        if (pBuffer == null) {
            throw new IllegalArgumentException("Buffer cannot be null");
        }

        ByteBuffer newBuffer = BufferUtils.createByteBuffer(pByteSize);
        pBuffer.position(0);
        newBuffer.put(pBuffer);
        newBuffer.flip();

        return newBuffer;
    }

    public static void MemoryAlloc_free(ByteBuffer pBuffer) {
        pBuffer = null;
    }
}
