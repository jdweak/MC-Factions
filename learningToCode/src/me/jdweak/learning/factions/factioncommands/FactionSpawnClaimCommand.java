package me.jdweak.learning.factions.factioncommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class FactionSpawnClaimCommand extends FactionCommand{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] args) {
		Player player = (Player)sender;
		if(player.hasPermission("op")) {
			super.factionManager.addSpawnChunk(player.getLocation().getChunk());
			player.sendMessage("chunk set as spawn chunk");
		} else {
			player.sendMessage(ChatColor.RED + "you do not have permission to execute this command");
		}
		return false;
	}

}
