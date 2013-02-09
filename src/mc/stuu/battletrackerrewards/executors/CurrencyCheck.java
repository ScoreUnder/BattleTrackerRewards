package mc.stuu.battletrackerrewards.executors;

import mc.stuu.battletrackerrewards.BTRConstants;
import mc.stuu.battletrackerrewards.config.BTRPlayerConfig;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import com.alk.executors.CustomCommandExecutor;

public class CurrencyCheck extends CustomCommandExecutor {
	
	public String senderName;
	public String otherPlayer;
	public int currencyValue;
	
	@MCCommand(cmds={"honour"})
	public void honourCheck(CommandSender sender) {
		senderName = sender.getName();
		currencyValue = BTRPlayerConfig.getCurrency(senderName);
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
			currencyValue = BTRPlayerConfig.getCurrency(otherPlayer);
			
			if(otherPlayer.isEmpty()){
				sender.sendMessage(ChatColor.DARK_RED + "The player you searched for hasn't joined the server");
			}else{
				sender.sendMessage(ChatColor.DARK_RED + otherPlayer + " has " + currencyValue + " " + BTRConstants.currencyName );
			}
		}
	}
}
