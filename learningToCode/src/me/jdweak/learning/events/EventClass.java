package me.jdweak.learning.events;

import java.util.ArrayList;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.jdweak.learning.factions.FactionManager;
import me.jdweak.learning.factions.items.ChooseFactionBook;

public class EventClass implements Event{
	public static ArrayList<String> allPlayers = new ArrayList<String>();
	public static ArrayList<Chunk> currentChunks = new ArrayList<Chunk>();
	@EventHandler
	public void playerJoined(PlayerJoinEvent event) {
		if(!(allPlayers.contains(event.getPlayer().getName()))) {
			allPlayers.add(event.getPlayer().getName());
			currentChunks.add(null);
			ChooseFactionBook.giveChooseFactionBook(event.getPlayer());
		}
	}
	
	@EventHandler
	public void playerMoved(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if(!(player.getLocation().getChunk().equals(currentChunks.get(allPlayers.indexOf(player.getName()))))){
			if(!FactionManager.getInstance().getChunkInfo(currentChunks.get(allPlayers.indexOf(player.getName()))).equals(FactionManager.getInstance().getChunkInfo(player.getLocation().getChunk()))) {
				player.sendMessage(FactionManager.getInstance().getChunkInfo(player));
			}
			currentChunks.set(allPlayers.indexOf(player.getName()), player.getLocation().getChunk());
		}
	}
	
	@EventHandler
	public void event(AreaEffectCloudApplyEvent event) {
		event.setCancelled(true);
		
	}
	@EventHandler
	public void spawnEvent(CreatureSpawnEvent event) {
		if(event.getEntityType() == EntityType.CREEPER) {
			if(Math.random() * 100 > 85) {
				Creeper x = (Creeper) event.getEntity();
				x.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 90000000, (int)(Math.random() * 3)));
				x.setPowered(true);
				x.setExplosionRadius((int)(Math.random() * 50));
				x.setMaxFuseTicks((int)(Math.random() * 100 ));
			}
			
			if(FactionManager.getInstance().spawnChunks.contains(event.getLocation().getChunk())) {
				event.setCancelled(true);
			}
	}
	}
	
	
	@EventHandler
	public void blockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (event.getBlockPlaced().getType() == Material.SPAWNER) {

            CreatureSpawner cs = (CreatureSpawner) event.getBlock().getState();

            ItemStack is = event.getItemInHand();
            ItemMeta meta = is.getItemMeta();

            if (!meta.hasLore()) {
                return;
            }

            String name = meta.getLore().toString();
            name = name.replace("[", "");
            name = name.replace("]", "");
            cs.setCreatureTypeByName(name);
            cs.update();
            
		}
	}
	
	@EventHandler
	public void playerBreakEvent(BlockBreakEvent event) {
		Player player = event.getPlayer();
		@SuppressWarnings("deprecation")
		ItemStack item = player.getItemInHand();
		Block block = event.getBlock();
		if(block.getType() == Material.SPAWNER) {
			if(item.containsEnchantment(Enchantment.SILK_TOUCH)){
				World world = player.getWorld();
				CreatureSpawner cs = (CreatureSpawner)block.getState();
				ItemStack spawner = new ItemStack(Material.LEGACY_MOB_SPAWNER);
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(cs.getCreatureTypeName());
				ItemMeta meta = spawner.getItemMeta();
				meta.setLore(lore);
				spawner.setItemMeta(meta);
				
				world.dropItem(block.getLocation(), spawner);
			}
		}
		
	}
	/*
	@EventHandler
	public void eggThrow(PlayerEggThrowEvent event) {
		event.getPlayer().damage(20.0);
	}
	
	
	@EventHandler
	public void playerMoved(PlayerMoveEvent event, boolean x) {
		if(x == true) {
			event.setCancelled(true);
		}
		Player player = event.getPlayer();
		player.getInventory().addItem((new ItemStack(Material.EGG, 1)));
		//Inventory inventory = player.getInventory();
		
		
	}
	
	
	@EventHandler
	public void playerHealed(EntityRegainHealthEvent event) {
		if(event.getEntity() instanceof Player) {
			if((event.getRegainReason().compareTo(RegainReason.EATING) != 0) && (event.getRegainReason().compareTo(RegainReason.MAGIC) != 0) && (event.getRegainReason().compareTo(RegainReason.MAGIC_REGEN) != 0)) {
				event.setCancelled(true);
			}
		}
		
	}
	*/

}
