package mc.stuu.battletrackerrewards.config;

import java.io.File;


import mc.stuu.battletrackerrewards.BattleTrackerRewards;

import org.bukkit.configuration.file.YamlConfiguration;


public class BTRPlayerConfig {
	
	static File cfile;
	public static YamlConfiguration config = new YamlConfiguration();
	
	public static void loadConfig(){
		cfile = new File(BattleTrackerRewards.findPath().getDataFolder() + "/BTRPlayers.yml");
		try{
			config.load(cfile);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void saveConfig(){
		cfile = new File(BattleTrackerRewards.findPath().getDataFolder() + "/BTRPlayers.yml");
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
	

