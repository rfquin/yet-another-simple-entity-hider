package com.yaseh;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface YasehConfig extends Config
{
	@ConfigItem(
			keyName = "doHideCompass",
			name = "Hide minimap compass",
			description = "Hides the camera compass located on the top left of the minimap"
	)
	default boolean doHideCompass()
	{
		return false;
	}
	@ConfigItem(
			keyName = "doHideXPButton",
			name = "Hide minimap XP button",
			description = "Hides the "
	)
	default boolean doHideXPButton()
	{
		return false;
	}

}
