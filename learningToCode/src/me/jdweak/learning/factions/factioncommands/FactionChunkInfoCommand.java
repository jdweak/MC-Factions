package me.jdweak.learning.factions.factioncommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FactionChunkInfoCommand extends FactionCommand{
	
	public FactionChunkInfoCommand() {
		super.name = "chunkInfo";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] arg) {
		Player player = (Player)sender;
		player.sendMessage(super.factionManager.getChunkInfo(player));
		return false;
	}

}
