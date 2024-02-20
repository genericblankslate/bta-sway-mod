package jamdoggie.swaymod.mixin;

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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = WorldRenderer.class, remap = false)
public class WorldRendererMixin
{
	@Shadow
	private Minecraft mc;

	@Inject(method = "setupViewBobbing(F)V", at = @At("HEAD"), cancellable = true)
	private void renderHand(float partialTick, CallbackInfo ci)
	{
		if (this.mc.activeCamera instanceof EntityCamera && ((EntityCamera)this.mc.activeCamera).entity instanceof EntityPlayer)
		{
			EntityPlayer entityplayer = (EntityPlayer)((EntityCamera)this.mc.activeCamera).entity;

			float distWalkedDelta = entityplayer.walkDist - entityplayer.walkDistO;
			float f4 = -(entityplayer.walkDist + distWalkedDelta * partialTick);
			float f5 = entityplayer.field_775_e + (entityplayer.field_774_f - entityplayer.field_775_e) * partialTick;
			float f6 = entityplayer.prevCameraPitch + (entityplayer.cameraPitch - entityplayer.prevCameraPitch) * partialTick;

			if (entityplayer.isSkating)
			{
				f4 *= 0.25F;
				f6 *= 0.25F;
			}

			GL11.glTranslatef(
				MathHelper.sin(f4 * (float)Math.PI) * f5 * 0.5F,
				-(float)Math.abs(Math.cos(f4 * (float)Math.PI) * f5),
					0.0F);

			GL11.glRotatef(MathHelper.sin(f4 * (float)Math.PI) * f5 * 3.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(Math.abs(MathHelper.cos(f4 * (float)Math.PI - 0.2F) * f5) * 5.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(f6, 1.0F, 0.0F, 0.0F);
		}

		ci.cancel();
	}
}
