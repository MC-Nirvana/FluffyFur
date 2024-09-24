package mod.maxbogomol.fluffy_fur.common.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;

import java.awt.*;

public abstract class ThreePositionColorClientPacket extends ClientPacket {
    protected final double x1;
    protected final double y1;
    protected final double z1;
    protected final double x2;
    protected final double y2;
    protected final double z2;
    protected final double x3;
    protected final double y3;
    protected final double z3;
    protected final float r;
    protected final float g;
    protected final float b;
    protected final float a;

    public ThreePositionColorClientPacket(double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, float r, float g, float b, float a) {
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
        this.x3 = x3;
        this.y3 = y3;
        this.z3 = z3;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public ThreePositionColorClientPacket(BlockPos pos, Vec3 vec1, Vec3 vec2, Color color) {
        this.x1 = pos.getX();
        this.y1 = pos.getY();
        this.z1 = pos.getZ();
        this.x2 = vec1.x();
        this.y2 = vec1.y();
        this.z2 = vec1.z();
        this.x3 = vec2.x();
        this.y3 = vec2.y();
        this.z3 = vec2.z();
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
        this.a = 1;
    }

    public ThreePositionColorClientPacket(Vec3 vec1, Vec3 vec2, Vec3 vec3, Color color) {
        this.x1 = vec1.x();
        this.y1 = vec1.y();
        this.z1 = vec1.z();
        this.x2 = vec2.x();
        this.y2 = vec2.y();
        this.z2 = vec2.z();
        this.x3 = vec3.x();
        this.y3 = vec3.y();
        this.z3 = vec3.z();
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
        this.a = 1;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeDouble(x1);
        buf.writeDouble(y1);
        buf.writeDouble(z1);
        buf.writeDouble(x2);
        buf.writeDouble(y2);
        buf.writeDouble(z2);
        buf.writeDouble(x3);
        buf.writeDouble(y3);
        buf.writeDouble(z3);
        buf.writeFloat(r);
        buf.writeFloat(g);
        buf.writeFloat(b);
        buf.writeFloat(a);
    }

    public static <T extends ThreePositionColorClientPacket> T decode(PacketProvider<T> provider, FriendlyByteBuf buf) {
        double x1 = buf.readDouble();
        double y1 = buf.readDouble();
        double z1 = buf.readDouble();
        double x2 = buf.readDouble();
        double y2 = buf.readDouble();
        double z2 = buf.readDouble();
        float r = buf.readFloat();
        float g = buf.readFloat();
        float b = buf.readFloat();
        float a = buf.readFloat();
        double x3 = buf.readDouble();
        double y3 = buf.readDouble();
        double z3 = buf.readDouble();
        return provider.getPacket(x1, y1, z1, x2, y2, z2, x3, y3, z3, r, g, b, a);
    }

    public interface PacketProvider<T extends ThreePositionColorClientPacket> {
        T getPacket(double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, float r, float g, float b, float a);
    }
}