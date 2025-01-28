package net.toshayo.waterframes.transformers;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.util.function.Function;


public class LibrariesDowngradingTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (basicClass == null) {
            return null;
        }
        if (transformedName.equals("org.watermedia.api.render.memory.MemoryAlloc")) {
            return visitClass(basicClass, classWriter -> new MemoryAllocVisitor(transformedName, classWriter));
        } else if (transformedName.startsWith("org.watermedia") ||
                transformedName.startsWith("org.watermedia.videolan4j.discovery")) {
            byte[] resultingClass = visitClass(basicClass, classWriter -> new Log4JDowngradingVisitor(transformedName, classWriter));
            if (transformedName.equals("org.watermedia.api.image.ImageFetch")) {
                resultingClass = visitClass(resultingClass, classWriter -> new ImageFetchCachingDisablingVisitor(transformedName, classWriter));
                resultingClass = visitClass(resultingClass, classWriter -> new ImageFetchMimeGuessingVisitor(transformedName, classWriter));
            }
            return resultingClass;
        }
        return basicClass;
    }

    private byte[] visitClass(byte[] basicClass, Function<ClassWriter, ClassVisitor> visitor) {
        ClassReader classReader = new ClassReader(basicClass);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

        classReader.accept(visitor.apply(classWriter), ClassReader.EXPAND_FRAMES);

        return classWriter.toByteArray();
    }
}
