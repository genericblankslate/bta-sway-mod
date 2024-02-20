package jamdoggie.swaymod.mixin;

import jamdoggie.swaymod.mixininterfaces.IPlayerMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.client.render.ItemRenderer;
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

		float swayPitch = playerMixin._getPrevRenderArmPitch() + (playerMixin._getRenderArmPitch() - playerMixin._getPrevRenderArmPitch()) * partialTick;
		float swayYaw = playerMixin._getPrevRenderArmYaw() + (playerMixin._getRenderArmYaw() - playerMixin._getPrevRenderArmYaw()) * partialTick;

		GL11.glRotatef((entityPlayer.xRot - swayPitch) * 0.1f, 1.0f, 0.0f, 0.0f);
		GL11.glRotatef((entityPlayer.yRot - swayYaw) * 0.1f, 0.0f, 1.0f, 0.0f);
	}
}
