package mc.stuu.battletrackerrewards.config;

import java.io.File;

import mc.stuu.battletrackerrewards.BattleTrackerRewards;

import org.bukkit.configuration.file.YamlConfiguration;


public class BTRConfig {
	static File cfile;
	public static YamlConfiguration config = new YamlConfiguration();
	
	public static void loadPluginConfig(BattleTrackerRewards plugin){
		File cfile = new File(plugin.getDataFolder() + "/config.yml");
		try{
			config.load(cfile);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void savePluginConfig(BattleTrackerRewards plugin){
		
		try{
			config.save(cfile);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String getConfigString(String path){
		return config.getString("Values." + path);
	}	
	
	public static int getConfigInt(String path){
		return config.getInt("Values." + path);
	}
	
	public static void setConfigString(String path, String string){
		config.set("Values." + path, string );
	}
	public static void setConfigInt(String path, int i){
		config.set("Values." + path, i );
	}
	

}
