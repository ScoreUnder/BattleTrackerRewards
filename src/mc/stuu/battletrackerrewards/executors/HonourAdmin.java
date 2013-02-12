package mc.stuu.battletrackerrewards.executors;

import mc.stuu.battletrackerrewards.config.BTRConfig;
import mc.stuu.battletrackerrewards.config.BTRPlayerConfig;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HonourAdmin implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String modifier, String[] args) {
		sender.sendMessage("CurrencyHelp");
		String player = args[0];
		
		if(sender.hasPermission("tracker.admin")){
			if(cmd.getName().equalsIgnoreCase("honouradmin")){
				if(modifier.equalsIgnoreCase("jmessage")){
					if(args[0].equalsIgnoreCase("yes")){
						honourMessage(sender, "yes");
					}else if(args[0].equalsIgnoreCase("no")){
						honourMessage(sender, "no");
					}
				}	
			}else if(modifier.equalsIgnoreCase("configset")){
				Integer num = Integer.parseInt(args[1]);
				honourConfig(sender, "set", args[0], num);
			}else if(modifier.equalsIgnoreCase("configfind")){
				honourConfig(sender, "find", args[0], 0);
			}else if(modifier.equalsIgnoreCase("add")){
				Integer num = Integer.parseInt(args[1]);
				addHonour(sender, player, num);
				return true;
	     	}else if(modifier.equalsIgnoreCase("remove")){
	     		Integer num = Integer.parseInt(args[1]);
	     		removeHonour(sender, player, num);
				return true;
			}else if(modifier.equalsIgnoreCase("set")){
				Integer num = Integer.parseInt(args[1]);
				setHonour(sender, player, num);
				return true;
			}else{
				sender.sendMessage(ChatColor.RED + "Invalid arguements");
				sender.sendMessage(ChatColor.RED + "Usage: /honour <set|remove|add> <player> <amount>");
			}		
				
		 
		}
				
				
		
			
	return false;
	}
	
	
	protected void honourMessage(CommandSender sender, String input){
		if(input.equalsIgnoreCase("yes")){
			BTRConfig.setConfigString("currencyOnJoin", input);
			sender.sendMessage(ChatColor.BLUE + "Players' honour will now be shown when they join.");
		}else if(input.equalsIgnoreCase("no")){
			BTRConfig.setConfigString("currencyOnJoin", input);
			sender.sendMessage(ChatColor.BLUE + "Players' honour will no longer be shown when they join.");
	
		}
	}
	
	
	protected void honourConfig(CommandSender sender, String setOrFind, String valueName, Integer value) {
		if(setOrFind.equalsIgnoreCase("set")){
			if(valueName.equalsIgnoreCase("maxCurrencyValue")){
				BTRConfig.setConfigInt(valueName, value);
				sender.sendMessage(ChatColor.BLUE + "Value " + valueName + " set to: " + value);
			}else if(valueName.equalsIgnoreCase("minimumCurrencyValye")){
				BTRConfig.setConfigInt(valueName, value);
				sender.sendMessage(ChatColor.BLUE + "Value " + valueName + " set to: " + value);
			}else if(valueName.equalsIgnoreCase("eloCurrencyMultiplier")){
				BTRConfig.setConfigInt(valueName, value);
				sender.sendMessage(ChatColor.BLUE + "Value " + valueName + " set to: " + value);
			}else if(valueName.equalsIgnoreCase("currencyChange")){
				BTRConfig.setConfigInt(valueName, value);
				sender.sendMessage(ChatColor.BLUE + "Value " + valueName + " set to: " + value);
			}else if(valueName.equalsIgnoreCase("killingSpree")){
				BTRConfig.setConfigInt(valueName, value);
				sender.sendMessage(ChatColor.BLUE + "Value " + valueName + " set to: " + value);
			}else if(valueName.equalsIgnoreCase("shutdownMultiplier")){
				BTRConfig.setConfigInt(valueName, value);
				sender.sendMessage(ChatColor.BLUE + "Value " + valueName + " set to: " + value);
			}else if(valueName.equalsIgnoreCase("shutdownMax")){
				BTRConfig.setConfigInt(valueName, value);
				sender.sendMessage(ChatColor.BLUE + "Value " + valueName + " set to: " + value);
			}else{
				sender.sendMessage(ChatColor.RED + "Incorrect usage, invalid valuename");
			}
		}else if(setOrFind.equalsIgnoreCase("find")){
			if(valueName.equalsIgnoreCase("maxCurrencyValue")){
				sender.sendMessage(ChatColor.BLUE + "Current value is:" + BTRConfig.getConfigInt(valueName));
			}else if(valueName.equalsIgnoreCase("minimumCurrencyValye")){
				sender.sendMessage(ChatColor.BLUE + "Current value is:" + BTRConfig.getConfigInt(valueName));
			}else if(valueName.equalsIgnoreCase("eloCurrencyMultiplier")){
				sender.sendMessage(ChatColor.BLUE + "Current value is:" + BTRConfig.getConfigInt(valueName));
			}else if(valueName.equalsIgnoreCase("currencyChange")){
				sender.sendMessage(ChatColor.BLUE + "Current value is:" + BTRConfig.getConfigInt(valueName));
			}else if(valueName.equalsIgnoreCase("killingSpree")){
				sender.sendMessage(ChatColor.BLUE + "Current value is:" + BTRConfig.getConfigInt(valueName));
			}else if(valueName.equalsIgnoreCase("shutdownMultiplier")){
				sender.sendMessage(ChatColor.BLUE + "Current value is:" + BTRConfig.getConfigInt(valueName));
			}else if(valueName.equalsIgnoreCase("shutdownMax")){
				sender.sendMessage(ChatColor.BLUE + "Current value is:" + BTRConfig.getConfigInt(valueName));
			}else{
				sender.sendMessage(ChatColor.RED + "Incorrect usage, invalid valuename");
			}

		}else{
			sender.sendMessage("Incorrect usage: honour <configset|configfind> <valuename> (<value>) ");
		}
	}

	private void addHonour(CommandSender sender, String player, Integer addCurrency) {
		if(!player.isEmpty() && addCurrency != null){
			Integer newCurrency = BTRPlayerConfig.getCurrency(player) + addCurrency;
			BTRPlayerConfig.updatePlayer(player, newCurrency);
			sender.sendMessage(ChatColor.RED + "Updated!");
		}else{
			sender.sendMessage(ChatColor.RED + "You did not use correct arguments, or the arguments were invalid.");
			sender.sendMessage(ChatColor.RED + "Usage: /honour <set|remove|add> <player> <amount>");
		}	
	}
	
	
	private void removeHonour(CommandSender sender, String player, Integer addCurrency) {
		if(!player.isEmpty() && addCurrency != null){
			Integer newCurrency = BTRPlayerConfig.getCurrency(player) - addCurrency;
			BTRPlayerConfig.updatePlayer(player, newCurrency);
			sender.sendMessage(ChatColor.RED + "Updated!");
		}else{
			sender.sendMessage(ChatColor.RED + "You did not use correct arguments, or the arguments were invalid.");
			sender.sendMessage(ChatColor.RED + "Usage: /honour <set|remove|add> <player> <amount>");
		}
	}
	
	
	private void setHonour(CommandSender sender, String player, Integer newCurrency) {
		if(!player.isEmpty() && newCurrency != null){
			BTRPlayerConfig.updatePlayer(player, newCurrency);
			sender.sendMessage(ChatColor.RED + "Updated!");
		}else{
			sender.sendMessage(ChatColor.RED + "You did not use correct arguments, or the arguments were invalid.");
			sender.sendMessage(ChatColor.RED + "Usage: /honour <set|remove|add> <player> <amount>");
		}
	}		
}

