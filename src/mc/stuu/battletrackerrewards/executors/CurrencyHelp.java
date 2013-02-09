package mc.stuu.battletrackerrewards.executors;

import org.bukkit.command.CommandSender;
import com.alk.executors.CustomCommandExecutor.MCCommand;

public class CurrencyHelp {

	@MCCommand(cmds={"honour"}, min = 2, usage = "honour help")
		public void onHonourHelp(CommandSender sender){
			if(sender.hasPermission("tracker.admin")){
				sender.sendMessage("Honour Commands:");
				sender.sendMessage("./honour");
				sender.sendMessage("./honour <player>");
				sender.sendMessage("./sethonour <player> <amount>");
				sender.sendMessage("./addhonour <player> <amount>");
				sender.sendMessage("./removehonour <player> <amount>");
			
			}else if(!sender.hasPermission("tracker.admin")){
				sender.sendMessage("Honour Commands:");
				sender.sendMessage("./honour");
				sender.sendMessage("./honour <player>");
			}
	}
	
	
	
}
