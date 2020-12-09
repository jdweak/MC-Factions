package me.jdweak.learning.factions.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import me.jdweak.learning.Main;
import me.jdweak.learning.factions.FactionManager;
import net.md_5.bungee.api.ChatColor;

public class ChooseFactionBook implements Listener{
	PatrolSelector patrolSelector = new PatrolSelector();

public ItemStack getChooseFactionBook() {
	ItemStack item = new ItemStack(Material.BOOK);
	ItemMeta meta = item.getItemMeta();
	
	meta.setDisplayName(ChatColor.YELLOW + "CHOOSE YOUR PATROL");
	ArrayList<String> lore = new ArrayList<String>();
	lore.add(ChatColor.LIGHT_PURPLE + "RIGHT CLICK AND SELECT THE PATROL YOU ARE IN");
	meta.setLore(lore);
	item.setItemMeta(meta);
	return item;
	}
	
	public static void giveChooseFactionBook(Player player) {
		player.getInventory().addItem(new ChooseFactionBook().getChooseFactionBook());
	}
	
	@EventHandler
	public void onBookClicked(PlayerInteractEvent event) {
		if(event.getPlayer().getItemInHand().equals(getChooseFactionBook())){
		System.out.println("onBookCLickedFired");
		Plugin plugin = Main.getPlugin(Main.class);
		Inventory i = plugin.getServer().createInventory(null, 9, ChatColor.BLUE + "SELECT YOUR PATROL");
		i.setItem(2, patrolSelector.getPatrolSelector("fox"));
		i.setItem(3, patrolSelector.getPatrolSelector("bat"));
		i.setItem(5, patrolSelector.getPatrolSelector("croc"));
		i.setItem(6, patrolSelector.getPatrolSelector("panther"));
		event.getPlayer().openInventory(i);
		System.out.println("end of book clicked");
		}
	}
	
	@EventHandler
	public void inventoryClicked(InventoryClickEvent event) {
		System.out.println("inventoryClickedFired");
		Player player = (Player) event.getWhoClicked();
		if(event.getView().getTitle().equals(ChatColor.BLUE + "SELECT YOUR PATROL")) {
			if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "FOX PATROL")){
				FactionManager.getInstance().addPlayerToFaction(player.getName(), "Fox_Patrol");
				event.setCancelled(true);
				player.sendMessage(ChatColor.GREEN + "You have Sucessfully joined the Fox Patrol");
			} else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "BAT PATROL")){
				System.out.println("fired bat patrol");
				FactionManager.getInstance().addPlayerToFaction(player.getName(), "Bat_Patrol");
				event.setCancelled(true);
				player.sendMessage(ChatColor.GREEN + "You have Sucessfully joined the Bat Patrol");
			} else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "CROC PATROL")){
				FactionManager.getInstance().addPlayerToFaction(player.getName(), "Croc_Patrol");
				event.setCancelled(true);
				player.sendMessage(ChatColor.GREEN + "You have Sucessfully joined the Croc Patrol");
			} else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "PANTHER PATROL")){
				FactionManager.getInstance().addPlayerToFaction(player.getName(), "Panther_Patrol");
				event.setCancelled(true);
				player.sendMessage(ChatColor.GREEN + "You have Sucessfully joined the Panther Patrol");
			}
			player.closeInventory();
			player.getInventory().remove(getChooseFactionBook());
		}
	}
}