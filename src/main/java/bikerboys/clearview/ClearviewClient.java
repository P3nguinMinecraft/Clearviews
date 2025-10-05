package bikerboys.clearview;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClearviewClient implements ClientModInitializer {
	public static final String MOD_ID = "clearview";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final KeyBinding.Category CATEGORY = new KeyBinding.Category(Identifier.of("clearview"));

    private static KeyBinding fog;
	private static KeyBinding portal;
	private static KeyBinding spyglass;
	private static KeyBinding darkness;
	private static KeyBinding blindness;
	private static KeyBinding nausea;

	@Override
	public void onInitializeClient() {
		MidnightConfig.init("clearview", ClearviewConfig.class);

		darkness = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.clearview.darkness",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_UNKNOWN,
				CATEGORY)
		);

		blindness = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.clearview.blindness",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_UNKNOWN,
				CATEGORY)
		);

		nausea = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.clearview.nausea",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_UNKNOWN,
				CATEGORY)
		);

		fog = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.clearview.fog",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_UNKNOWN,
				CATEGORY)
		);

		spyglass = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.clearview.spyglass",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_UNKNOWN,
				CATEGORY)
		);

		portal = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.clearview.portal",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_UNKNOWN,
				CATEGORY)
		);

		ClientTickEvents.END_CLIENT_TICK.register(minecraftClient -> {
			if (minecraftClient.player != null) {
				StatusEffectInstance darknessEffect = minecraftClient.player.getStatusEffect(StatusEffects.DARKNESS);
				StatusEffectInstance blindnessEffect = minecraftClient.player.getStatusEffect(StatusEffects.BLINDNESS);
				StatusEffectInstance nauseaEffect = minecraftClient.player.getStatusEffect(StatusEffects.NAUSEA);

				if (darknessEffect != null && ClearviewConfig.DarknessEffect) {
					minecraftClient.player.removeStatusEffect(StatusEffects.DARKNESS);
				}
				if (blindnessEffect != null && ClearviewConfig.BlindnessEffect) {
					minecraftClient.player.removeStatusEffect(StatusEffects.BLINDNESS);
				}
				if (nauseaEffect != null && ClearviewConfig.NauseaEffect) {
					minecraftClient.player.removeStatusEffect(StatusEffects.NAUSEA);
				}

				if (darkness.wasPressed()) {
					ClearviewConfig.DarknessEffect = !ClearviewConfig.DarknessEffect;
					ClearviewConfig.write(MOD_ID);
					minecraftClient.player.sendMessage(Text.of("Remove darkness is now " + ClearviewConfig.DarknessEffect), true);
				}
				if (blindness.wasPressed()) {
					ClearviewConfig.BlindnessEffect = !ClearviewConfig.BlindnessEffect;
					ClearviewConfig.write(MOD_ID);
					minecraftClient.player.sendMessage(Text.of("Remove blindness is now " + ClearviewConfig.BlindnessEffect), true);
				}
				if (nausea.wasPressed()) {
					ClearviewConfig.NauseaEffect = !ClearviewConfig.NauseaEffect;
					ClearviewConfig.write(MOD_ID);
					minecraftClient.player.sendMessage(Text.of("Remove nausea is now " + ClearviewConfig.NauseaEffect), true);
				}
				if (fog.wasPressed()){
					ClearviewConfig.FogDisabled = !ClearviewConfig.FogDisabled;
					ClearviewConfig.write(MOD_ID);
					minecraftClient.player.sendMessage(Text.of("Remove fog is now " + ClearviewConfig.FogDisabled), true);
				}

				if (portal.wasPressed()){
					ClearviewConfig.PortalOverlayDisabled = !ClearviewConfig.PortalOverlayDisabled;
					ClearviewConfig.write(MOD_ID);
					minecraftClient.player.sendMessage(Text.of("Remove portal overlay is now " + ClearviewConfig.PortalOverlayDisabled), true);
				}
				if (spyglass.wasPressed()){
					ClearviewConfig.RemoveSpyglassBorder = !ClearviewConfig.RemoveSpyglassBorder;
					ClearviewConfig.write(MOD_ID);
					minecraftClient.player.sendMessage(Text.of("Remove spyglass border is now " + ClearviewConfig.RemoveSpyglassBorder), true);
				}
			}
		});
	}
}