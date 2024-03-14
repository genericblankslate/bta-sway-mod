package jamdoggie.swaymod;

import io.github.prospector.modmenu.api.ModMenuApi;
import io.github.prospector.modmenu.util.TriConsumer;
import jamdoggie.swaymod.options.GuiOptionsPageSway;
import net.minecraft.client.gui.GuiScreen;

import java.util.function.Function;

public class SwayModMenuEntry implements ModMenuApi
{
	@Override
	public String getModId()
	{
		return SwayMod.MOD_ID;
	}

	@Override
	public Function<GuiScreen, ? extends GuiScreen> getConfigScreenFactory()
	{
		return (GuiOptionsPageSway::swayOptionsPage);
	}

	@Override
	public void attachCustomBadges(TriConsumer<String, Integer, Integer> consumer)
	{
		ModMenuApi.super.attachCustomBadges(consumer);
	}
}
