package mc.stuu.battletrackerrewards.listeners;

import java.io.File;

import mc.stuu.battletrackerrewards.BTRConstants;
import mc.stuu.battletrackerrewards.BattleTrackerRewards;
import mc.stuu.battletrackerrewards.config.BTRPlayerConfig;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

	public Player player;
	public String playerName;
	public double playerCurrency;
	
	public void onPlayerJoinEvent(PlayerJoinEvent pje){
		player = pje.getPlayer();
		File f = new File(BattleTrackerRewards.plugin.getDataFolder() + "/" + player.getName() + ".yml");
		if(!f.exists()){
			BTRPlayerConfig.createPlayerFile(player.getName());
		}else{
			playerName = player.getName();
			BTRPlayerConfig.loadConfig(playerName);
			if(BTRConstants.currencyOnJoin == "yes"){
				playerCurrency = BTRPlayerConfig.getCurrency(playerName);
				player.sendMessage("You have " + playerCurrency + " " + BTRConstants.currencyName);
			}	
		}	
	}
	
	public void onPlayerLeaveEvent(PlayerQuitEvent e){
		player = e.getPlayer();
		BTRPlayerConfig.saveConfig(player.getName());
	}
}
