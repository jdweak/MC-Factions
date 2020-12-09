package me.jdweak.learning.factions.factioncommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class FactionSetHomeCommand extends FactionCommand{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2,
			String[] arg) {
		Player player = (Player)sender;
		if(super.factionManager.getPlayerFaction(player.getName()) == null) {
			player.sendMessage(ChatColor.RED + "you are not currently in a faction");
		}
		if(super.factionManager.getPlayerFaction(player.getName()).getOwner().equals(player.getName())){
			super.factionManager.setFactionHome(super.factionManager.getPlayerFaction(player.getName()).getName(), player.getLocation());
			player.sendMessage(ChatColor.AQUA + "Faction Home Set");
		} else {
			player.sendMessage(ChatColor.RED + "only a faction owner can set a faction's home");
		}
		return false;
	}
	

}
