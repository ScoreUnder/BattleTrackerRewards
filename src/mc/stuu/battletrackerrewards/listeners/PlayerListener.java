package mc.stuu.battletrackerrewards.listeners;

import mc.stuu.battletrackerrewards.BattleTrackerConfig;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

	public Player player;
	public static String playerName;
	
	public void onPlayerJoinEvent(PlayerJoinEvent pje){
		player = pje.getPlayer();
		if(!player.hasPlayedBefore()){
			playerName = player.getName();
			BattleTrackerConfig.addPlayer(playerName);
		}
	}
}
