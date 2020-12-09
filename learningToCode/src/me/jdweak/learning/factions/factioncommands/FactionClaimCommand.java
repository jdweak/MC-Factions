package me.jdweak.learning.factions.factioncommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FactionClaimCommand extends FactionCommand{
	
	public FactionClaimCommand() {
		super.name = "claim";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] arg) {
		super.factionManager.claimChunk((Player)sender);
		return false;
	}
}
