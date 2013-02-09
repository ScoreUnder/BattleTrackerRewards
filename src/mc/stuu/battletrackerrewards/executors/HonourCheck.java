package mc.stuu.battletrackerrewards.executors;

import mc.stuu.battletrackerrewards.BTRConstants;
import mc.stuu.battletrackerrewards.BattleTrackerConfig;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import com.alk.executors.CustomCommandExecutor;

public class HonourCheck extends CustomCommandExecutor {
	
	public String senderName;
	public String otherPlayer;
	public int currencyValue;
	
	@MCCommand(cmds={"honour"})
	public void honourCheck(CommandSender sender) {
		senderName = sender.getName();
		currencyValue = BattleTrackerConfig.getCurrency(senderName);
		sender.sendMessage(ChatColor.DARK_RED + "You have" + currencyValue + BTRConstants.currencyName );
	}
	
	@MCCommand(cmds={"honour"}, min=2, usage = "honour <player>")
	public void honourCheckOther(CommandSender sender, String Args[]) {
		if(Args.length > 1){
			sender.sendMessage(ChatColor.DARK_RED + "You have used too many arguements.");
			sender.sendMessage(ChatColor.DARK_RED + "Usage: /honour <player>");
		}
		
		if(Args.length == 1){
			otherPlayer = Args[0];
			currencyValue = BattleTrackerConfig.getCurrency(otherPlayer);
			sender.sendMessage(ChatColor.DARK_RED + otherPlayer + " has " + currencyValue + BTRConstants.currencyName );
		}
	}
	
}

