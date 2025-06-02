package com.yaseh;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.Optional;

@Slf4j
@PluginDescriptor(
	name = "Example"
)
public class YasehPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private YasehConfig config;

	@Override
	protected void startUp() throws Exception
	{
		update("doHideCompass", config.doHideCompass());
		update("doHideXPButton", config.doHideCompass());
	}

	@Override
	protected void shutDown() throws Exception
	{
		update("doHideCompass", false);
		update("doHideXPButton", false);
	}


	@Provides
	YasehConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(YasehConfig.class);
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged e) {
		String key = e.getKey();
		update(key, getChangedConfigValue(key));
	}

	private void update(String id, boolean doHide) {
		getInterfaceObjectWidget(id).ifPresent(widget -> widget.setHidden(doHide));
	}

	private boolean getChangedConfigValue(String e) {
		return switch (e) {
			case "doHideCompass" -> config.doHideCompass();
			case "doHideXPButton" -> config.doHideXPButton();
			default -> throw new IllegalArgumentException("Illegal argument: No id found");
		};
	}

	private Optional<Widget> getInterfaceObjectWidget(String obj) {
		return switch (obj) {
			case "doHideCompass" -> Optional.ofNullable(client.getWidget(InterfaceID.ToplevelOsrsStretch.MAP_MINIMAP_GRAPHIC9));
			case "doHideXPButton" -> Optional.ofNullable(client.getWidget(InterfaceID.ToplevelOsrsStretch.COMPASSCLICK));
            default -> Optional.empty();
        };
	}
}
