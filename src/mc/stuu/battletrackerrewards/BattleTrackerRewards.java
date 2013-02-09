package mc.stuu.battletrackerrewards;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import mc.stuu.battletrackerrewards.BattleTrackerConfig;
import mc.stuu.battletrackerrewards.listeners.PlayerListener;
public class BattleTrackerRewards extends JavaPlugin{
	
	static BattleTrackerRewards plugin;
	
	public void onEnable(){
		plugin = this;
		BattleTrackerConfig.loadConfig();
		getLogger().info("BattleTrackerRewards has been enabled.");
		
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerListener(), this);
	}
		
	
	
	
	public void onDisable(){
		getLogger().info("BattleTrackerRewards has been disabled.");
		BattleTrackerConfig.saveConfig();
		
	}
	
	public static BattleTrackerRewards findPath(){
		return plugin;
	}
	
	
	
	
	
	
	
	
}