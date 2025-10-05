package bikerboys.clearview.mixin.client;

import bikerboys.clearview.ClearviewConfig;
import com.mojang.blaze3d.buffers.Std140Builder;
import net.minecraft.client.render.fog.FogRenderer;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.ByteBuffer;

@Debug(export = true)
@Mixin(value = FogRenderer.class, priority = 10000)
public class FogRendererMixin {
    @Shadow private static boolean fogEnabled;
    @Inject(method = "applyFog(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V", at = @At(value = "HEAD"), cancellable = true)
    private void alwaysSpectatorFog(ByteBuffer buffer, int bufPos, Vector4f fogColor, float environmentalStart, float environmentalEnd, float renderDistanceStart, float renderDistanceEnd, float skyEnd, float cloudEnd, CallbackInfo ci) {
       if (ClearviewConfig.FogDisabled) {
           buffer.position(bufPos);
           Std140Builder.intoBuffer(buffer)
                   .putVec4(fogColor)
                   .putFloat(ClearviewConfig.fogStart)
                   .putFloat(ClearviewConfig.fogEnd)
                   .putFloat(ClearviewConfig.fogStart)
                   .putFloat(ClearviewConfig.fogEnd)
                   .putFloat(skyEnd)
                   .putFloat(cloudEnd);
           ci.cancel();
       }
    }
}