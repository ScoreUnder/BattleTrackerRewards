package mc.stuu.battletrackerrewards.config;

import java.io.File;

import mc.stuu.battletrackerrewards.BattleTrackerRewards;

import org.bukkit.configuration.file.YamlConfiguration;

public class BTRConfig {
	static File cfile;
	public static YamlConfiguration config = new YamlConfiguration();
	
	public static void loadPluginConfig(){
		cfile = new File(BattleTrackerRewards.findPath().getDataFolder() + "/BTRConfig.yml");
		try{
			config.load(cfile);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void savePluginConfig(){
		cfile = new File(BattleTrackerRewards.findPath().getDataFolder() + "/BTRConfig.yml");
		try{
			config.save(cfile);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String getConfigString(String path){
		return config.getString(path);
	}	
	
	public static int getConfigInt(String path){
		return config.getInt(path);
	}
	
	public static void setConfigString(String path, String string){
		config.set(path, string );
	}
	public static void setConfigInt(String path, int i){
		config.set(path, i );
	}
	
}
