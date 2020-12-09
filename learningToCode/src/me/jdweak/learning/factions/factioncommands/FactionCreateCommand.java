package me.jdweak.learning.factions.factioncommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class FactionCreateCommand extends FactionCommand{
	public FactionCreateCommand() {
		super.name = "factionCreate";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] arg) {
		super.factionManager.createFaction(sender.getName(), arg[0]);
		return false;
	}

}
