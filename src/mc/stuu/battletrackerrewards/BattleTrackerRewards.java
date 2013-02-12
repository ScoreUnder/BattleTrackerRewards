package mc.stuu.battletrackerrewards;



import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import mc.stuu.battletrackerrewards.config.BTRConfig;
import mc.stuu.battletrackerrewards.executors.HonourAdmin;
import mc.stuu.battletrackerrewards.executors.HonourCommands;
import mc.stuu.battletrackerrewards.listeners.PlayerListener;
public class BattleTrackerRewards extends JavaPlugin {
	
	//TODO TEST
	//Bug fix - Config - Currently works, but requires a restart after first loading - Also change from flatfile to file per player :)
	
   
    public static BattleTrackerRewards plugin;
    
    
    public static YamlConfiguration playerConfig = new YamlConfiguration();
    
    
	public void onEnable() {
		
		saveDefaultConfig();
    	plugin = this;
    	
        BTRConfig.loadPluginConfig(plugin);
        
        getLogger().info("BattleTrackerRewards has been enabled.");
        
        getCommand("honouradmin").setExecutor(new HonourAdmin());
        getCommand("honour").setExecutor(new HonourCommands());
        
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(), this);
        
    }

    public void onDisable() {
        getLogger().info("BattleTrackerRewards has been disabled.");
        BTRConfig.savePluginConfig(plugin);
    }

    public BattleTrackerRewards returnPlugin(BattleTrackerRewards plugin2) {
        return plugin2;
    }
    
    public YamlConfiguration getPlayerConfig(){
    	return playerConfig;
    }
}