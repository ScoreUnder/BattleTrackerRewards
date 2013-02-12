package mc.stuu.battletrackerrewards.executors;

import mc.stuu.battletrackerrewards.BTRConstants;
import mc.stuu.battletrackerrewards.config.BTRPlayerConfig;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class HonourCommands implements CommandExecutor {
	
	private String senderName;
	private String otherPlayer;
	private Integer currencyValue;
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String player, String[] args){
		sender.sendMessage("CurrencyHelp");
		if(cmd.getName().equalsIgnoreCase("honour")){
			sender.sendMessage("Understood: command honour");
			if(args.length == 0){
				sender.sendMessage("player == null check");
				honourCheck(sender);
				return true;
			}else if(!args[0].equalsIgnoreCase("help")){
				if(!args[0].equalsIgnoreCase("set")){
					if(!args[0].equalsIgnoreCase("add")){
						if(!args[0].equalsIgnoreCase("remove")){
							if(!args[0].equalsIgnoreCase("jmessage")){
								if(!args[0].equalsIgnoreCase("configset")){
									if(!args[0].equalsIgnoreCase("configfind")){
										honourCheckOther(sender, args[0]);
										return true;
									}
								}
							}
						}
					}
				}
			}else if(args[0].equalsIgnoreCase("help")){
				sender.sendMessage("CurrencyHelp Understood help");
				if(sender instanceof Player){
					sender.sendMessage("CurrencyHelp Understoof instance of");
					onHonourHelp(sender);
					return true;
				}else{
					sender.sendMessage("You must be a player to use this command");
				}
			}
		}
		
		
		return false;
	}
	
	
	private void honourCheck(CommandSender sender) {
		sender.sendMessage("honourCheck Method called");
		senderName = sender.getName();
		currencyValue = BTRPlayerConfig.getCurrency(senderName);
		sender.sendMessage(ChatColor.DARK_RED + "You have" + currencyValue + BTRConstants.currencyName );
	}
	
	
	private void honourCheckOther(CommandSender sender, String player) {
			
			otherPlayer = player;
			currencyValue = BTRPlayerConfig.getCurrency(otherPlayer);
			
			if(currencyValue == 00){
				sender.sendMessage(ChatColor.DARK_RED + "The player you searched for hasn't joined the server");
			}else{
				sender.sendMessage(ChatColor.DARK_RED + otherPlayer + " has " + currencyValue + " " + BTRConstants.currencyName );
			}
			
	}
	
	private void onHonourHelp(CommandSender sender){
		if(sender.hasPermission("tracker.admin")){
			sender.sendMessage("Honour Commands:");
			sender.sendMessage("./honour - Displays your own honour");
			sender.sendMessage("./honour <player> - Displays player's honour");
			sender.sendMessage("./honour <set|remove|add> <player> <amount> - Sets players honour, gives them honour or takes honour.");
			sender.sendMessage("./honour <configset|configfind> <valuename> (<value>) - Finds value for valuename in config, or sets value for valuename");
		
		}else if(!sender.hasPermission("tracker.admin")){
			sender.sendMessage("Honour Commands:");
			sender.sendMessage("./honour - Displays your honour");
			sender.sendMessage("./honour <player> - Displays player's honour");
		}
}
}
