package me.jdweak.learning.factions.factioncommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class FactionSetOwnerCommand extends FactionCommand{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		if(arg0.hasPermission("op")) {
			super.factionManager.setFactionLeader(arg3[0], arg3[1]);
			Bukkit.getServer().broadcastMessage(ChatColor.AQUA + arg3[1] + " `is now the leader of the " + arg3[0]);
		}else {
			arg0.sendMessage(ChatColor.RED + "you don't have permission to execute this command");
		}
		return false;
	}

}
