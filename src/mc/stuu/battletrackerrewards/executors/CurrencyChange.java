package mc.stuu.battletrackerrewards.executors;

import mc.stuu.battletrackerrewards.config.BTRPlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import com.alk.executors.CustomCommandExecutor;

public class CurrencyChange extends CustomCommandExecutor{
	
	
	@MCCommand(cmds={"addhonour"}, min = 3, usage = "addhonour <player> <amount>", perm = "tracker.admin")
	public void addHonour(CommandSender sender, String player, Integer addCurrency) {
		if(!player.isEmpty() && addCurrency != null){
			Integer newCurrency = BTRPlayerConfig.getCurrency(player) + addCurrency;
			BTRPlayerConfig.updatePlayer(player, newCurrency);
			sender.sendMessage(ChatColor.RED + "Updated!");
		}else{
			sender.sendMessage(ChatColor.RED + "Invalid arguments.");
			sender.sendMessage(ChatColor.RED + "Usage: /addhonour <player> <amount>");
		}
		
	}
	
	@MCCommand(cmds={"removehonour"}, min = 3, usage = "removehonour <player> <amount>", perm = "tracker.admin")
	public void removeHonour(CommandSender sender, String player, Integer addCurrency) {
		if(!player.isEmpty() && addCurrency != null){
			Integer newCurrency = BTRPlayerConfig.getCurrency(player) - addCurrency;
			BTRPlayerConfig.updatePlayer(player, newCurrency);
			sender.sendMessage(ChatColor.RED + "Updated!");
		}else{
			sender.sendMessage(ChatColor.RED + "Invalid arguments.");
			sender.sendMessage(ChatColor.RED + "Usage: /removehonour <player> <amount>");
		}
		
	}
	
	@MCCommand(cmds={"sethonour"}, min = 3, usage = "sethonour <player> <amount>", perm = "tracker.admin")
	public void setHonour(CommandSender sender, String player, Integer newCurrency) {
		if(!player.isEmpty() && newCurrency != null){
			BTRPlayerConfig.updatePlayer(player, newCurrency);
			sender.sendMessage(ChatColor.RED + "Updated!");
		}else{
			sender.sendMessage(ChatColor.RED + "Invalid arguments.");
			sender.sendMessage(ChatColor.RED + "Usage: /sethonour <player> <amount>");
		}
	}
}
