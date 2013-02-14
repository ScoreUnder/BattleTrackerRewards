package mc.stuu.battletrackerrewards.executors;

import com.google.common.collect.ImmutableSet;
import mc.stuu.battletrackerrewards.BTRConfigValues;
import mc.stuu.battletrackerrewards.config.BTRConfig;
import mc.stuu.battletrackerrewards.config.BTRPlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Set;

public class HonourAdmin implements CommandExecutor {
	private static final Set<String> configIntValues = ImmutableSet.of(
			"maxCurrencyValue", "minimumCurrencyValue", "eloCurrencyMultiplier", "currencyChange", "killingSpree",
			"shutdownMultiplier", "shutdownMax"
	);

	public boolean onCommand(CommandSender sender, Command cmd, String modifier, String[] args) {
		sender.sendMessage("CurrencyHelp");
		if (args.length == 0) {
			sendHelpMessage(sender);
			return true;
		}
		String player = args[0];

		if (sender.hasPermission("tracker.admin")) {
			if (cmd.getName().equalsIgnoreCase("honouradmin")) {
				if (modifier.equalsIgnoreCase("jmessage")) {
					if (args[0].equalsIgnoreCase("yes")) {
						honourMessage(sender, "yes");
					} else if (args[0].equalsIgnoreCase("no")) {
						honourMessage(sender, "no");
					} else {
						sendHelpMessage(sender);
					}
				}
				return true;
			} else if (modifier.equalsIgnoreCase("configset")) {
				try {
					int num = Integer.parseInt(args[1]);
					honourConfig(sender, "set", args[0], num);
				} catch (NumberFormatException ex) {
					sendHelpMessage(sender);
				}
				return true;
			} else if (modifier.equalsIgnoreCase("configfind")) {
				honourConfig(sender, "find", args[0], 0);
				return true;
			} else if (modifier.equalsIgnoreCase("add")) {
				setHonour(sender, player, args[1], Operation.ADD);
				return true;
			} else if (modifier.equalsIgnoreCase("remove")) {
				setHonour(sender, player, args[1], Operation.REMOVE);
				return true;
			} else if (modifier.equalsIgnoreCase("set")) {
				setHonour(sender, player, args[1], Operation.SET);
				return true;
			} else {
				sendHelpMessage(sender);
				return true;
			}
		}

		return false;
	}

	private void sendHelpMessage(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "Invalid arguments");
		sender.sendMessage(ChatColor.RED + "Usage: /honour <set|remove|add> <player> <amount>");
	}

	protected void honourMessage(CommandSender sender, String input) {
		if (input.equalsIgnoreCase("yes")) {
			BTRConfig.setConfigString("currencyOnJoin", input);
			sender.sendMessage(ChatColor.BLUE + "Players' honour will now be shown when they join.");
		} else if (input.equalsIgnoreCase("no")) {
			BTRConfig.setConfigString("currencyOnJoin", input);
			sender.sendMessage(ChatColor.BLUE + "Players' honour will no longer be shown when they join.");
		}
	}

	protected void honourConfig(CommandSender sender, String setOrFind, String valueName, int value) {
		if (setOrFind.equalsIgnoreCase("set")) {
			if (configIntValues.contains(valueName)) {
				BTRConfig.setConfigInt(valueName, value);
				sender.sendMessage(ChatColor.BLUE + "Value " + valueName + " set to: " + value);
			} else {
				sender.sendMessage(ChatColor.RED + "Incorrect usage, invalid value name");
			}
		} else if (setOrFind.equalsIgnoreCase("find")) {
			if (configIntValues.contains(valueName)) {
				sender.sendMessage(ChatColor.BLUE + "Current value is:" + BTRConfig.getConfigInt(valueName));
			} else {
				sender.sendMessage(ChatColor.RED + "Incorrect usage, invalid value name");
			}
		} else {
			sender.sendMessage("Incorrect usage: honour <configset|configfind> <valuename> (<value>) ");
		}
	}

	private void setHonour(CommandSender sender, String player, String newCurrencyStr, Operation operation) {
		long newCurrency;
		try {
			newCurrency = Integer.parseInt(newCurrencyStr);
		} catch (NumberFormatException ex) {
			sendHelpMessage(sender);
			return;
		}
		if (!player.isEmpty()) {
			BTRPlayerConfig config = BTRPlayerConfig.getConfigFor(player);
			config.setCurrency(operation.applyTo(config.getCurrency(), newCurrency));
			sender.sendMessage(ChatColor.RED + "Updated!" + player + " now has " + BTRConfigValues.formatCurrency(newCurrency));
		} else {
			sendHelpMessage(sender);
		}
	}

	// Couldn't think of a better way to coalesce the 3 almost identical methods...
	enum Operation {
		ADD, REMOVE, SET;
		long applyTo(long a, long b) {
			switch (this) {
				case ADD: return a + b;
				case REMOVE: return a - b;
				case SET: return b;
				default: throw new IllegalArgumentException();
			}
		}
	}
}

