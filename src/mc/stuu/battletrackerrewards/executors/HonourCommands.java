package mc.stuu.battletrackerrewards.executors;

import mc.stuu.battletrackerrewards.BTRConfigValues;
import mc.stuu.battletrackerrewards.config.BTRPlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HonourCommands implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String player, String[] args) {
		sender.sendMessage("CurrencyHelp");
		if (cmd.getName().equalsIgnoreCase("honour")) {
			sender.sendMessage("Understood: command honour");
			if (args.length == 0) {
				sender.sendMessage("player == null check");
				honourCheck(sender);
				return true;
			} else if (args[0].equalsIgnoreCase("help")) {
				if (args[0].equalsIgnoreCase("help")) {
					sender.sendMessage("CurrencyHelp Understood help");
					if (sender instanceof Player) {
						sender.sendMessage("CurrencyHelp Understoof instance of");
						onHonourHelp(sender);
						return true;
					} else {
						sender.sendMessage("You must be a player to use this command");
					}
				}
			} else if (args[0].equalsIgnoreCase("set")) {
				// TODO
			} else if (args[0].equalsIgnoreCase("add")) {
				// TODO
			} else if (args[0].equalsIgnoreCase("remove")) {
				// TODO
			} else if (args[0].equalsIgnoreCase("jmessage")) {
				// TODO
			} else if (args[0].equalsIgnoreCase("configset")) {
				// TODO
			} else if (args[0].equalsIgnoreCase("configfind")) {
				// TODO
			} else {
				honourCheckOther(sender, args[0]);
				return true;
			}
		}

		return false;
	}

	private void honourCheck(CommandSender sender) {
		sender.sendMessage("honourCheck Method called");
		String senderName = sender.getName();
		long currencyValue = BTRPlayerConfig.getConfigFor(senderName).getCurrency();
		sender.sendMessage(ChatColor.DARK_RED + "You have " + BTRConfigValues.formatCurrency(currencyValue));
	}

	private void honourCheckOther(CommandSender sender, String player) {
		long currencyValue = BTRPlayerConfig.getConfigFor(player).getCurrency();

		if (currencyValue == 0) {
			sender.sendMessage(ChatColor.DARK_RED + "The player you searched for hasn't joined the server");
		} else {
			sender.sendMessage(ChatColor.DARK_RED + player + " has " + BTRConfigValues.formatCurrency(currencyValue));
		}
	}

	private void onHonourHelp(CommandSender sender) {
		if (sender.hasPermission("tracker.admin")) {
			sender.sendMessage("Honour Commands:");
			sender.sendMessage("./honour - Displays your own honour");
			sender.sendMessage("./honour <player> - Displays player's honour");
			sender.sendMessage("./honour <set|remove|add> <player> <amount> - Sets players honour, gives them honour or takes honour.");
			sender.sendMessage("./honour <configset|configfind> <valuename> (<value>) - Finds value for valuename in config, or sets value for valuename");
		} else if (!sender.hasPermission("tracker.admin")) {
			sender.sendMessage("Honour Commands:");
			sender.sendMessage("./honour - Displays your honour");
			sender.sendMessage("./honour <player> - Displays player's honour");
		}
	}
}
