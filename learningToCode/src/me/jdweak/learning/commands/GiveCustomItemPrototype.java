package me.jdweak.learning.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.jdweak.learning.items.TeleportStaff;

public class GiveCustomItemPrototype extends JdweakCommand{
	
	public String name = "give_custom_item_prototype";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player)sender;
		if(player.hasPermission("op")) {
		TeleportStaff x = new TeleportStaff();
		x.giveTeleportStaff(player);
		System.out.print("inside giveitem");
		} else {
			player.sendMessage(ChatColor.RED + "you don't have permission to execute this command");
		}
		return false;
	}

}
