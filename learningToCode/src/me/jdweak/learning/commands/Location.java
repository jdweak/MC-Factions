package me.jdweak.learning.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Location extends JdweakCommand{
	
	public String name = "Location";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 0) {
		Player player = (Player)sender;
		player.sendMessage(player.getLocation().toString());
		} else if(args.length == 1) {
			for(Player player : Bukkit.getOnlinePlayers()) {
				if(player.getName().equalsIgnoreCase(args[0])) {
					sender.sendMessage(player.getLocation().toString());
				}
			}
		}
		return false;
	}

}
