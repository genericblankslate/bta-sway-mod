package jamdoggie.swaymod.mixin;

import jamdoggie.swaymod.SwayMod;
import jamdoggie.swaymod.options.ISwayOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.option.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Minecraft.class, remap = false)
public class MinecraftMixin
{
	@Shadow
	public GameSettings gameSettings;

	@Inject(method = "startGame", at = @At(value = "TAIL"))
	private void startOfGameInit(CallbackInfo ci)
	{
		SwayMod.options = (ISwayOptions) gameSettings;
	}
}
