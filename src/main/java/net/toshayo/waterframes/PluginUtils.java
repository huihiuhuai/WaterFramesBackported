package net.toshayo.waterframes;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;

public class PluginUtils {
    public static final String ARCH = System.getProperty("os.arch").toLowerCase().trim();
    private static final String OS = System.getProperty("os.name").toLowerCase().trim();

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
