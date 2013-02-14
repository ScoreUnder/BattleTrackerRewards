package mc.stuu.battletrackerrewards;

import mc.stuu.battletrackerrewards.config.BTRConfig;
import mc.stuu.battletrackerrewards.events.MaxEloEvent;
import mc.stuu.battletrackerrewards.events.PlayerKillEvent;
import mc.stuu.battletrackerrewards.executors.HonourAdmin;
import mc.stuu.battletrackerrewards.executors.HonourCommands;
import mc.stuu.battletrackerrewards.listeners.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BattleTrackerRewards extends JavaPlugin {

	//TODO TEST
	//Bug fix - Config - Currently works, but requires a restart after first loading - Also change from flatfile to file per player :)

	private static BattleTrackerRewards plugin;

	public static BattleTrackerRewards getInstance() {
		return plugin;
	}

	public void onEnable() {
		plugin = this;
		saveDefaultConfig();

		BTRConfig.loadPluginConfig(this);
		BTRConfigValues.reloadConfig();

		getCommand("honouradmin").setExecutor(new HonourAdmin());
		getCommand("honour").setExecutor(new HonourCommands());

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerListener(), this);
		pm.registerEvents(new MaxEloEvent(), this);
		pm.registerEvents(new PlayerKillEvent(), this);
	}

	public void onDisable() {
		BTRConfig.savePluginConfig(this);
	}
}