package jamdoggie.swaymod.mixin;

import jamdoggie.swaymod.SwayMod;
import jamdoggie.swaymod.mixininterfaces.IPlayerMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.client.render.ItemRenderer;
import net.minecraft.core.util.helper.MathHelper;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ItemRenderer.class, remap = false)
public class ItemRendererMixin
{
	@Shadow
	private Minecraft mc;

	@Inject(method = "renderItemInFirstPerson(F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;getLightBrightness(III)F"))
	private void renderItemInFirstPerson(float partialTick, CallbackInfo ci)
	{
		EntityPlayerSP entityPlayer = this.mc.thePlayer;
		IPlayerMixin playerMixin = (IPlayerMixin)entityPlayer;

		if (SwayMod.options == null)
			return;

		if (SwayMod.options.enableSway().value)
		{
			float multiplier = (SwayMod.options.swayMultiplier().value * 5f);

			float swayAmountPitch = (playerMixin._getRenderArmPitch() - playerMixin._getPrevRenderArmPitch());
			float swayAmountYaw = (playerMixin._getRenderArmYaw() - playerMixin._getPrevRenderArmYaw());

			float swayPitch = playerMixin._getPrevRenderArmPitch() + swayAmountPitch * partialTick;
			float swayYaw = playerMixin._getPrevRenderArmYaw() + swayAmountYaw * partialTick;

			GL11.glRotatef((entityPlayer.xRot - swayPitch) * (0.1f * multiplier), 1.0f, 0.0f, 0.0f);
			GL11.glRotatef((entityPlayer.yRot - swayYaw) * (0.1f * multiplier), 0.0f, 1.0f, 0.0f);


		}
	}
}
