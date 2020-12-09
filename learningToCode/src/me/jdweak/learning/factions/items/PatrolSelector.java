package me.jdweak.learning.factions.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class PatrolSelector {
	
	public ItemStack getPatrolSelector(String patrol) {
		if(patrol.equalsIgnoreCase("fox")) {
			ItemStack item = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.BLUE + "FOX PATROL");
			item.setItemMeta(meta);
			return item;
		} else if(patrol.equalsIgnoreCase("bat")) {
			ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GRAY + "BAT PATROL");
			item.setItemMeta(meta);
			return item;
		} else if(patrol.equalsIgnoreCase("croc")) {
			ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "CROC PATROL");
			item.setItemMeta(meta);
			return item;
		} else if(patrol.equalsIgnoreCase("panther")) {
			ItemStack item = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.DARK_PURPLE + "PANTHER PATROL");
			item.setItemMeta(meta);
			return item;
		}
		return null;
		
	}

}
