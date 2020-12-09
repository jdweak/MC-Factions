package me.jdweak.learning.factions.factioncommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FactionHomeCommand extends FactionCommand{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] arg) {
		Player player = (Player)sender;
		try {
			super.factionManager.teleportPlayerToFactionHome(player);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
