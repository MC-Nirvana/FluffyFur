package mod.maxbogomol.fluffy_fur.mixin.client;

import com.mojang.blaze3d.preprocessor.GlslPreprocessor;
import com.mojang.blaze3d.shaders.EffectProgram;
import mod.maxbogomol.fluffy_fur.client.shader.postprocess.FluffyFurGlslPreprocessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EffectProgram.class)
public class EffectProgramMixin {
    @ModifyArg(
            method = "compileShader",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/shaders/EffectProgram;compileShaderInternal(Lcom/mojang/blaze3d/shaders/Program$Type;Ljava/lang/String;Ljava/io/InputStream;Ljava/lang/String;Lcom/mojang/blaze3d/preprocessor/GlslPreprocessor;)I"
            ),
            index = 4
    )
    private static GlslPreprocessor fluffy_fur$useCustomPreprocessor(GlslPreprocessor org) {
        return FluffyFurGlslPreprocessor.PREPROCESSOR;
    }
}