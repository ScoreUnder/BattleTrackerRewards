package mc.stuu.battletrackerrewards.config;

import java.io.File;
import java.io.IOException;

import mc.stuu.battletrackerrewards.BattleTrackerRewards;
import org.bukkit.configuration.file.YamlConfiguration;


public class BTRPlayerConfig extends BattleTrackerRewards{
	
	public static YamlConfiguration config = new YamlConfiguration();
	
	public static void createPlayerFile(String player){
		File f = new File(plugin.getDataFolder() + "/" + player + ".yml");
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void loadConfig(String player){
		File cfile = new File(plugin.getDataFolder() + "/" + player + ".yml");
		try{
			config.load(cfile);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void saveConfig(String player){
		File cfile = new File(plugin.getDataFolder() + "/" + player + ".yml");
		try{
			config.save(cfile);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void updatePlayer(String updatedPlayer, double newCurrency){
		File f = new File(plugin.getDataFolder() + "/" + updatedPlayer + ".yml");
		if(f.exists()){
			config.set("Honour", newCurrency);
		}
	}
	
	
	public static int getCurrency(String player){
		int i = 00;
		File f = new File(plugin.getDataFolder() + "/" + player + ".yml");
		if(f.exists()){
			return config.getInt("Honour");
			
		}
	return i;
	}	
	
}
	

