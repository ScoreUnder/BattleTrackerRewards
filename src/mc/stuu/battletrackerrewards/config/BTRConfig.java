package mc.stuu.battletrackerrewards.config;

import mc.stuu.battletrackerrewards.BattleTrackerRewards;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;


public class BTRConfig {
	public static FileConfiguration config;

	public static void loadPluginConfig(Plugin plugin) {
		plugin.reloadConfig();
		config = plugin.getConfig();
	}

	public static void savePluginConfig(Plugin plugin) {
		plugin.saveConfig();
	}

	public static String getConfigString(String path) {
		return config.getString("Values." + path);
	}

	public static int getConfigInt(String path) {
		return config.getInt("Values." + path);
	}

	public static void setConfigString(String path, String string) {
		config.set("Values." + path, string);
	}

	public static void setConfigInt(String path, int i) {
		config.set("Values." + path, i);
	}
}
