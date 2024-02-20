package jamdoggie.swaymod.mixin;

import jamdoggie.swaymod.mixininterfaces.IPlayerMixin;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityPlayerSP.class, remap = false)
public abstract class EntityPlayerSPMixin extends EntityPlayer implements IPlayerMixin
{
	public float renderArmYaw;
	public float renderArmPitch;
	public float prevRenderArmYaw;
	public float prevRenderArmPitch;

	public EntityPlayerSPMixin(World world)
	{
		super(world);
	}

	@Inject(method="updatePlayerActionState()V", at = @At(value = "HEAD"))
	private void updateEntityActionState(CallbackInfo ci)
	{
		prevRenderArmYaw = renderArmYaw;
		prevRenderArmPitch = renderArmPitch;
		renderArmPitch = (float)(renderArmPitch + (xRot - renderArmPitch) * 0.5D);
		renderArmYaw = (float)(renderArmYaw + (yRot - renderArmYaw) * 0.5D);
	}

	@Override
	public float _getRenderArmPitch()
	{
		return renderArmPitch;
	}

	@Override
	public void _setRenderArmPitch(float pitch)
	{
		renderArmPitch = pitch;
	}

	@Override
	public float _getPrevRenderArmPitch()
	{
		return prevRenderArmPitch;
	}

	@Override
	public void _setPrevRenderArmPitch(float pitch)
	{
		prevRenderArmPitch = pitch;
	}

	@Override
	public float _getRenderArmYaw()
	{
		return renderArmYaw;
	}

	@Override
	public void _setRenderArmYaw(float yaw)
	{
		renderArmYaw = yaw;
	}

	@Override
	public float _getPrevRenderArmYaw()
	{
		return prevRenderArmYaw;
	}

	@Override
	public void _setPrevRenderArmYaw(float yaw)
	{
		prevRenderArmYaw = yaw;
	}
}
