package net.toshayo.waterframes.transformers;

import net.minecraft.launchwrapper.IClassTransformer;
import net.toshayo.waterframes.WaterFramesPlugin;
import org.objectweb.asm.*;

public class LibrariesDowngradingTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (basicClass == null) {
            return null;
        }
        if(transformedName.equals("org.watermedia.api.render.memory.MemoryAlloc")) {
            ClassReader classReader = new ClassReader(basicClass);
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            classReader.accept(new MemoryAllocVisitor(transformedName, classWriter), ClassReader.EXPAND_FRAMES);
            return classWriter.toByteArray();
        } else if (transformedName.equals("org.watermedia.core.tools.ArgTool")) {
            ClassReader classReader = new ClassReader(basicClass);
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            classReader.accept(new ArgToolFixingVisitor(transformedName, classWriter), ClassReader.EXPAND_FRAMES);
            return classWriter.toByteArray();
        } else if (transformedName.startsWith("org.watermedia") ||
                transformedName.startsWith("org.watermedia.videolan4j.discovery")) {
            ClassReader classReader = new ClassReader(basicClass);
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            classReader.accept(new Log4JDowngradingVisitor(transformedName, classWriter), ClassReader.EXPAND_FRAMES);
            if(transformedName.equals("org.watermedia.api.image.ImageFetch")) {
                classReader = new ClassReader(classWriter.toByteArray());
                classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                classReader.accept(new ImageFetchCachingDisablingVisitor(transformedName, classWriter), ClassReader.EXPAND_FRAMES);
            }
            return classWriter.toByteArray();
        }
        return basicClass;
    }

    private static class Log4JDowngradingVisitor extends ClassVisitor {
        private final String transformedName;

        public Log4JDowngradingVisitor(String transformedName, ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
            this.transformedName = transformedName;
        }

        @Override
        public MethodVisitor visitMethod(int access, String methodName, String desc, String signature, String[] exceptions) {
            MethodVisitor methodVisitor = cv.visitMethod(access, methodName, desc, signature, exceptions);
            return new MethodVisitor(Opcodes.ASM5, methodVisitor) {
                @Override
                public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
                    boolean isReplacableMethod = name.equals("info") || name.equals("warn") || name.equals("error") || name.equals("debug");
                    if (opcode == Opcodes.INVOKEINTERFACE &&
                            owner.equals("org/apache/logging/log4j/Logger") && isReplacableMethod &&
                            desc.startsWith("(Lorg/apache/logging/log4j/Marker;Ljava/lang/String;Ljava/lang/Object;")) {
                        WaterFramesPlugin.LOGGER.info("Patching {}.{} in {}.{}{}", "Logger", name, transformedName, methodName, desc);
                        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "net/toshayo/waterframes/PluginUtils", name, "(Lorg/apache/logging/log4j/Logger;" + desc.substring(1), false);
                    } else if (opcode == Opcodes.INVOKEINTERFACE &&
                            owner.equals("org/apache/logging/log4j/Logger") && isReplacableMethod &&
                            desc.startsWith("(Ljava/lang/String;Ljava/lang/Object;")) {
                        WaterFramesPlugin.LOGGER.info("Patching {}.{} in {}.{}{}", "Logger", name, transformedName, methodName, desc);
                        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "net/toshayo/waterframes/PluginUtils", name, "(Lorg/apache/logging/log4j/Logger;" + desc.substring(1), false);
                    } else {
                        super.visitMethodInsn(opcode, owner, name, desc, itf);
                    }
                }
            };
        }
    }

    private static class ImageFetchCachingDisablingVisitor extends ClassVisitor {
        private final String transformedName;

        public ImageFetchCachingDisablingVisitor(String transformedName, ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
            this.transformedName = transformedName;
        }

        @Override
        public MethodVisitor visitMethod(int access, String methodName, String desc, String signature, String[] exceptions) {
            MethodVisitor methodVisitor = cv.visitMethod(access, methodName, desc, signature, exceptions);
            if(methodName.equals("load") && desc.equals("(Ljava/lang/String;Ljava/net/URL;)[B")) {
                return new MethodVisitor(Opcodes.ASM5, methodVisitor) {
                    @Override
                    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
                        if (opcode == Opcodes.INVOKEVIRTUAL && owner.equals("java/net/URLConnection") && name.equals("setDefaultUseCaches")) {
                            WaterFramesPlugin.LOGGER.info("Patching {}.{} in {}.{}{}", owner, name, transformedName, methodName, desc);
                            mv.visitInsn(Opcodes.POP);
                            mv.visitInsn(Opcodes.ICONST_1);
                        }
                        super.visitMethodInsn(opcode, owner, name, desc, itf);
                    }
                };
            }
            return methodVisitor;
        }
    }

    private static class MemoryAllocVisitor extends ClassVisitor {
        private final String transformedName;

        public MemoryAllocVisitor(String transformedName, ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
            this.transformedName = transformedName;
        }

        @Override
        public MethodVisitor visitMethod(int access, String methodName, String desc, String signature, String[] exceptions) {
            if (methodName.equals("create") || methodName.equals("resize") || methodName.equals("free")) {
                MethodVisitor methodVisitor = cv.visitMethod(access, methodName, desc, signature, exceptions);
                return new MethodVisitor(Opcodes.ASM5, methodVisitor) {
                    @Override
                    public void visitCode() {
                        WaterFramesPlugin.LOGGER.info("Patching {}.{}{}", transformedName, methodName, desc);
                        switch (methodName) {
                            case "create":
                                mv.visitVarInsn(Opcodes.ILOAD, 0);
                                break;
                            case "resize":
                                mv.visitVarInsn(Opcodes.ALOAD, 0);
                                mv.visitVarInsn(Opcodes.ILOAD, 1);
                                break;
                            case "free":
                                mv.visitVarInsn(Opcodes.ALOAD, 0);
                                break;
                        }
                        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "net/toshayo/waterframes/PluginUtils", "MemoryAlloc_" + methodName, desc, false);
                        if (methodName.equals("free")) {
                            mv.visitInsn(Opcodes.RETURN);
                        } else {
                            mv.visitInsn(Opcodes.ARETURN);
                        }
                    }
                };
            }
            return super.visitMethod(access, methodName, desc, signature, exceptions);
        }
    }

    private static class ArgToolFixingVisitor extends ClassVisitor {
        private final String transformedName;

        public ArgToolFixingVisitor(String transformedName, ClassVisitor cv) {
            super(Opcodes.ASM5, cv);

            this.transformedName = transformedName;
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            String[] fixedInterfaces = new String[interfaces.length];
            for(int i = 0; i < interfaces.length; i++) {
                if(interfaces[i].equals("org/apache/logging/log4j/util/Supplier")) {
                    fixedInterfaces[i] = "java/util/function/Supplier";
                } else {
                    fixedInterfaces[i] = interfaces[i];
                }
            }
            WaterFramesPlugin.LOGGER.info("Patching {} {}", transformedName, signature);
            super.visit(version, access, name, signature.replace("org/apache/logging/log4j/util/Supplier", "java/util/function/Supplier"), superName, fixedInterfaces);
        }
    }
}
