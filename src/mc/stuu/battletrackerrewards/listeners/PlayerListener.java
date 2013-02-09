package mc.stuu.battletrackerrewards.listeners;

import mc.stuu.battletrackerrewards.BTRConstants;
import mc.stuu.battletrackerrewards.config.BTRPlayerConfig;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

	public Player player;
	public static String playerName;
	public static double playerCurrency;
	
	public void onPlayerJoinEvent(PlayerJoinEvent pje){
		player = pje.getPlayer();
		if(!player.hasPlayedBefore()){
			playerName = player.getName();
			BTRPlayerConfig.addPlayer(playerName);
		}else if(BTRConstants.currencyOnJoin == "yes"){
			playerName = player.getName();
			playerCurrency = BTRPlayerConfig.getCurrency(playerName);
			player.sendMessage("You have " + playerCurrency + " " + BTRConstants.currencyName);
		}
	}
}
