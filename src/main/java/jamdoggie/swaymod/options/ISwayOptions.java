package jamdoggie.swaymod.options;

import net.minecraft.client.option.BooleanOption;
import net.minecraft.client.option.FloatOption;

public interface ISwayOptions
{
	public BooleanOption enableSway();
	public BooleanOption enableVerticalBobSway();
	public FloatOption swayMultiplier();
}
