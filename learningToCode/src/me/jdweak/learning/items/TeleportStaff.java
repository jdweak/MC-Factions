package me.jdweak.learning.items;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class TeleportStaff implements Listener{
	
	public ItemStack getTeleportStaff() {
	ItemStack item = new ItemStack(Material.STICK);
	ItemMeta meta = item.getItemMeta();
	
	meta.setDisplayName(ChatColor.DARK_PURPLE + "Teleportation Staff");
	ArrayList<String> lore = new ArrayList<String>();
	lore.add(ChatColor.LIGHT_PURPLE + "Looks Pretty Cool");
	meta.setLore(lore);
	item.setItemMeta(meta);
	return item;
	}
	
	public void giveTeleportStaff(Player player) {
		player.getInventory().addItem(new TeleportStaff().getTeleportStaff());
	}
	
	@EventHandler
	public void teleport(PlayerInteractEvent event) {
		if(event.getPlayer().getInventory().getItemInMainHand().equals(new TeleportStaff().getTeleportStaff())) {
			Location location = event.getPlayer().getTargetBlock(null, 100).getLocation();
			location.setYaw(event.getPlayer().getLocation().getYaw());
			event.getPlayer().teleport(location);
			System.out.print("eventStatement");
		}
		
	}

}
