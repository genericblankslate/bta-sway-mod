package jamdoggie.swaymod.mixininterfaces;

import net.minecraft.client.entity.player.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

public interface IPlayerMixin
{
	float _getRenderArmPitch();
	void _setRenderArmPitch(float pitch);

	float _getPrevRenderArmPitch();
	void _setPrevRenderArmPitch(float pitch);

	float _getRenderArmYaw();
	void _setRenderArmYaw(float yaw);

	float _getPrevRenderArmYaw();
	void _setPrevRenderArmYaw(float yaw);
}
