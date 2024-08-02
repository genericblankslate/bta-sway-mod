package jamdoggie.swaymod.options;
import jamdoggie.swaymod.mixininterfaces.IPlayerMixin;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.options.GuiOptions;
import net.minecraft.client.gui.options.components.BooleanOptionComponent;
import net.minecraft.client.gui.options.components.FloatOptionComponent;
import net.minecraft.client.gui.options.data.OptionsPage;
import net.minecraft.client.gui.options.data.OptionsPages;
import net.minecraft.client.option.GameSettings;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.util.GameStartEntrypoint;

public class GuiOptionsPageSway implements GameStartEntrypoint
{
	public static GameSettings gameSettings = ((Minecraft) FabricLoader.getInstance().getGameInstance()).gameSettings;
	public static ISwayOptions swayOptions = (ISwayOptions) gameSettings;
	public static final OptionsPage SwayOptionsPage = OptionsPages.register(new OptionsPage("swaymod.options.title", Item.paper.getDefaultStack())
		.withComponent(new BooleanOptionComponent(swayOptions.enableSway()))
		.withComponent(new BooleanOptionComponent(swayOptions.enableVerticalBobSway()))
		.withComponent(new FloatOptionComponent(swayOptions.swayMultiplier())));

	public static GuiOptions swayOptionsPage(GuiScreen parent)
	{
		return new GuiOptions(parent, SwayOptionsPage);
	}

	@Override
	public void beforeGameStart()
	{

	}

	@Override
	public void afterGameStart()
	{

	}
}
