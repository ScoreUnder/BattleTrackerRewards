package mc.stuu.battletrackerrewards.listeners;

import mc.stuu.battletrackerrewards.BTRConfigValues;
import mc.stuu.battletrackerrewards.config.BTRPlayerConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent pje) {
		Player player = pje.getPlayer();
		BTRPlayerConfig config = BTRPlayerConfig.getConfigFor(player.getName());
		if (BTRConfigValues.currencyOnJoin) {
			player.sendMessage("You have " + BTRConfigValues.formatCurrency(config.getCurrency()));
		}
	}

	@EventHandler
	public void onPlayerLeaveEvent(PlayerQuitEvent e) {
		BTRPlayerConfig.getConfigFor(e.getPlayer().getName()).unload();
	}
}
