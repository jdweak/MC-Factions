package me.jdweak.learning.factions.factioncommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class FactionInfoCommand extends FactionCommand{
	public FactionInfoCommand() {
		super.name = "factionInfo";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] arg) {
		sender.sendMessage(super.factionManager.returnFactionNames());
		return false;
	}

}
