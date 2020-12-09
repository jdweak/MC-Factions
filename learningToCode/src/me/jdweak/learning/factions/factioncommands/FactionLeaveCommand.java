package me.jdweak.learning.factions.factioncommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FactionLeaveCommand extends FactionCommand{
	
	public FactionLeaveCommand() {
		super.name = "leaveFaction";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] arg) {
		Player player = (Player)sender;
		super.factionManager.removePlayerFromFaction(player.getName());
		player.sendMessage(ChatColor.GREEN + "you have left your faction");
		return false;
	}

}
