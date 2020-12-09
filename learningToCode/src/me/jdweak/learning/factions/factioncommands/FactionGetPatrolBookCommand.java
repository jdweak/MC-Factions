package me.jdweak.learning.factions.factioncommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jdweak.learning.factions.items.ChooseFactionBook;

public class FactionGetPatrolBookCommand extends FactionCommand{

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		Player player = (Player)arg0;
		if(player.hasPermission("op")) {
		ChooseFactionBook chooseFactionBook = new ChooseFactionBook();
		player.getInventory().addItem(chooseFactionBook.getChooseFactionBook());
		} else {
			player.sendMessage(ChatColor.RED + "you don't have permission to execute this command");
		}
		return false;
	}

}
