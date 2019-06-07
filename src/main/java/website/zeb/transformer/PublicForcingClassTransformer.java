package website.zeb.transformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import net.minecraft.launchwrapper.IClassTransformer;

public class PublicForcingClassTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        ClassReader classReader = new ClassReader(basicClass);
        ClassWriter classWriter = new ClassWriter(0);
        classReader.accept(new PublicForcingClassVisitor(classWriter), 0);
		return classWriter.toByteArray();
    }
    
}