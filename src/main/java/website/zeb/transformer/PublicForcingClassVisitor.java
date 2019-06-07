package website.zeb.transformer;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class PublicForcingClassVisitor extends ClassVisitor {

    public PublicForcingClassVisitor(ClassVisitor visitor) {
        super(Opcodes.ASM5, visitor);
    }

    private static int modAccess(int access) {
        if ((access & 0x7) != Opcodes.ACC_PRIVATE) {
            return (access & (~0x7)) | Opcodes.ACC_PUBLIC;
        } else {
            return access;
        }
    }

    @Override
    public void visit(final int version, final int access, final String name, final String signature,
            final String superName, final String[] interfaces) {
        super.visit(version, modAccess(access), name, signature, superName, interfaces);
    }

    @Override
    public void visitInnerClass(final String name, final String outerName, final String innerName, final int access) {
        super.visitInnerClass(name, outerName, innerName, modAccess(access));
    }

    @Override
    public FieldVisitor visitField(final int access, final String name, final String descriptor, final String signature,
            final Object value) {
        return super.visitField(modAccess(access), name, descriptor, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String descriptor,
            final String signature, final String[] exceptions) {
        return super.visitMethod(modAccess(access), name, descriptor, signature, exceptions);
    }

}