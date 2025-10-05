package bikerboys.clearview.mixin.client;

import bikerboys.clearview.ClearviewConfig;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {WaterFogModifier.class,
                LavaFogModifier.class,
                BlindnessEffectFogModifier.class,
                DarknessEffectFogModifier.class,
                AtmosphericFogModifier.class,
                DimensionOrBossFogModifier.class,
                PowderSnowFogModifier.class})
public class FogMixin {
    @Inject(method = "applyStartEndModifier", at = @At(value = "TAIL"))
    private void alwaysSpectatorFog(FogData data, Entity cameraEntity, BlockPos cameraPos, ClientWorld world, float viewDistance, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (ClearviewConfig.FogDisabled) {
            data.environmentalStart = ClearviewConfig.fogStart;
            data.environmentalEnd = ClearviewConfig.fogEnd;
            data.renderDistanceEnd = ClearviewConfig.fogEnd;
            data.renderDistanceStart = ClearviewConfig.fogStart;
        }
    }
}
