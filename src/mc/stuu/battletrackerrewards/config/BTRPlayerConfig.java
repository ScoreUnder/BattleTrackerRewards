package mc.stuu.battletrackerrewards.config;

import mc.stuu.battletrackerrewards.BattleTrackerRewards;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class BTRPlayerConfig {
	private static Map<String, BTRPlayerConfig> configs = new HashMap<String, BTRPlayerConfig>();
	private final YamlConfiguration config;
	private final File configFile;
	private final String player;

	private BTRPlayerConfig(String player, File folder) {
		if (player == null) throw new IllegalArgumentException("No player name passed to BTRPlayerConfig");
		this.player = player;
		this.configFile = new File(folder, player + ".yml");
		this.config = YamlConfiguration.loadConfiguration(configFile);
	}

	public static BTRPlayerConfig getConfigFor(String player) {
		BTRPlayerConfig config = configs.get(player);
		if (config == null) {
			config = new BTRPlayerConfig(player, BattleTrackerRewards.getInstance().getDataFolder());
			configs.put(player, config);
		}
		return config;
	}

	public void save() {
		try {
			config.save(configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void unload() {
		save();
		configs.remove(player);
	}

	public void setCurrency(long newCurrency) {
		config.set("Honour", newCurrency);
	}

	public long getCurrency() {
		return config.getLong("Honour", 0L);
	}
}
	

