package jamdoggie.swaymod.mixin;

import jamdoggie.swaymod.SwayMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.camera.EntityCamera;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.helper.MathHelper;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = WorldRenderer.class, remap = false)
public class WorldRendererMixin
{
	@Shadow
	private Minecraft mc;

	@Inject(method = "setupViewBobbing(F)V",
		at = @At(
			value = "INVOKE",
			target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V",
			shift = At.Shift.AFTER,
			ordinal = 1),
		locals = LocalCapture.CAPTURE_FAILHARD)
	private void renderHand(float partialTick, CallbackInfo ci, EntityPlayer entityplayer, float f1, float f2, float f3, float f4)
	{
		if (SwayMod.options != null && SwayMod.options.enableVerticalBobSway().value)
		{
			GL11.glRotatef(f4, 1.0F, 0.0F, 0.0F);
		}
	}
}
