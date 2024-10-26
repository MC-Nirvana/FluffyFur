package mod.maxbogomol.fluffy_fur.client.screenshake;

import mod.maxbogomol.fluffy_fur.config.FluffyFurClientConfig;
import net.minecraft.client.Camera;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;

public class ScreenshakeHandler {
    public static final ArrayList<ScreenshakeInstance> INSTANCES = new ArrayList<>();
    public static float intensityRotation;
    public static float intensityPosition;

    public static void cameraTick(Camera camera, RandomSource random) {
        if (intensityRotation >= 0) {
            float yawOffset = randomizeOffset(random, intensityRotation);
            float pitchOffset = randomizeOffset(random, intensityRotation);
            camera.setRotation(camera.getYRot() + yawOffset, camera.getXRot() + pitchOffset);
        }
        if (intensityPosition >= 0) {
            Vec3 pos = camera.getPosition();
            Vec3 posOffset = new Vec3(pos.x() + randomizeOffset(random, intensityPosition), pos.y() + randomizeOffset(random, intensityPosition), pos.z() + randomizeOffset(random, intensityPosition));
            camera.setPosition(posOffset.x(), posOffset.y(), posOffset.z());
        }
    }

    public static void clientTick(Camera camera, RandomSource random) {
        double intensity = FluffyFurClientConfig.SCREENSHAKE_INTENSITY.get();
        double rotationNormalize = 0;
        double positionNormalize = 0;
        double rotation = 0;
        double position = 0;
        for (ScreenshakeInstance instance : INSTANCES) {
            double update = instance.updateIntensity(camera, random);
            if (instance.isRotation) {
                if (instance.isNormalize) {
                    rotationNormalize = rotationNormalize + update;
                } else {
                    if (rotation < update) rotation = update;
                }
            }
            if (instance.isPosition) {
                if (instance.isNormalize) {
                    positionNormalize = positionNormalize + update;
                } else {
                    if (position < update) position = update;
                }
            }
        }
        double rotationSum = Math.min(rotationNormalize, intensity);
        double positionSum = Math.min(positionNormalize, intensity);
        rotation = rotation * intensity;
        position = position * intensity;

        intensityRotation = (float) Math.max(Math.pow(rotationSum, 3), rotation);
        intensityPosition = (float) Math.max(Math.pow(positionSum / 2, 3), position);
        INSTANCES.removeIf(i -> i.progress >= i.duration);
    }

    public static void addScreenshake(ScreenshakeInstance instance) {
        INSTANCES.add(instance);
    }

    public static float randomizeOffset(RandomSource random, float offset) {
        return Mth.nextFloat(random, -offset * 2, offset * 2);
    }
}
