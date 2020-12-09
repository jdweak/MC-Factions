package me.jdweak.learning.factions.factioncommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FactionUnclaimCommand extends FactionCommand{
	
	public FactionUnclaimCommand() {
		super.name = "unclaim";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] arg) {
		Player player = (Player)sender;
		super.factionManager.getPlayerFaction(player.getName()).removeClaim(player.getLocation().getChunk());
		player.sendMessage(ChatColor.GREEN + "Successfully unclaimed");
		return false;
	}

}
