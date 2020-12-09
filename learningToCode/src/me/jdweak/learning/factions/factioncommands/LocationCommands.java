package me.jdweak.learning.factions.factioncommands;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.jdweak.learning.commands.JdweakCommand;
import me.jdweak.learning.events.EventClass;
import net.md_5.bungee.api.ChatColor;

public class LocationCommands extends JdweakCommand{
	public String cmd1 = "sethome";
	public String cmd2 = "home";

	ArrayList<Location> locations = new ArrayList<Location>();
	
	public LocationCommands() {
	for(int i = 0; i < 100; i ++) {
		locations.add(null);
	}
	}
	
	@Override
	public boolean onCommand( CommandSender sender,  Command command,  String label, String[] arg) {
		Player player = (Player)sender;
		if(command.getName().equalsIgnoreCase(cmd1)){
			locations.set(EventClass.allPlayers.indexOf(player.getName()), player.getLocation());
			player.sendMessage(ChatColor.GREEN + "Home set!");
		} else if(command.getName().equalsIgnoreCase(cmd2)) {
			System.out.println("if statement");
			try {
				System.out.println("try statement");
				teleportPlayer(player);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public void teleportPlayer(Player player) throws InterruptedException{
		int[] x = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		for(int i = 0; i < 10; i ++) {
	        try {
	        	player.sendMessage(ChatColor.YELLOW + "teleporting in " + x[i]);
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		}
		player.teleport(locations.get((EventClass.allPlayers.indexOf(player.getName()))));
	}

}
