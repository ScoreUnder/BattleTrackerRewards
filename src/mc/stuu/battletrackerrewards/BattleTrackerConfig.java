package mc.stuu.battletrackerrewards;

import java.io.File;


import org.bukkit.configuration.file.YamlConfiguration;


public class BattleTrackerConfig {
	
	static File cfile;
	public static YamlConfiguration config = new YamlConfiguration();
	
	public static void loadConfig(){
		cfile = new File(BattleTrackerRewards.findPath().getDataFolder() + "/BattleTrackerRewards.yml");
		try{
			config.load(cfile);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void saveConfig(){
		cfile = new File(BattleTrackerRewards.findPath().getDataFolder() + "/BattleTrackerRewards.yml");
		try{
			config.save(cfile);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void addPlayer(String newPlayer){
		config.set(newPlayer, 0);
	}
	
	public static void updatePlayer(String updatedPlayer, double newCurrency){
		config.set(updatedPlayer, newCurrency);
	}
	
	public static int getCurrency(String player){
		return config.getInt(player);
	}
	
}
	

