package me.jdweak.learning.factions;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public final class FactionManager implements PlayerLeavedListener, Listener{
	
	private static FactionManager factionManager;
	private FactionManager() {
	}
	public static synchronized FactionManager getInstance() {
		if(factionManager == null) {
			factionManager = new FactionManager();
		}
		return factionManager;
	}
	
	public ArrayList<Faction> factions = new ArrayList<Faction>();
	public ArrayList<Chunk> spawnChunks = new ArrayList<Chunk>();
	
	public void createFaction(String creator, String name) {
		if(getPlayerFaction(creator) != null) {
		getPlayerFaction(creator).removePlayer(creator);
		}
		factions.add(new Faction(creator, name));
		factions.get(factions.size() -1).addPlayerLeavedListener(this);
		System.out.println(factions.get(factions.size() - 1).toString());
	}
	
	public void setFactionLeader(String faction, String name) {
		getFaction(faction).setOwner(name);
	}
	
	public void setFactionHome(String faction, Location location) {
		getFaction(faction).setFactionHome(location);
	}
	
	public void createPatrolFactions() {
		createFaction("", "Panther_Patrol");
		createFaction("", "Fox_Patrol");
		createFaction("", "Bat_Patrol");
		createFaction("", "Croc_Patrol");
		createFaction("", "temp");
		removeFaction("temp");
	}
	
	public void removePlayerFromFaction(String name) {
		getPlayerFaction(name).removePlayer(name);
	}
	
	public void addPlayerToFaction(String name, String factionName) {
		for(Faction faction : factions) {
			if(faction.getName().equalsIgnoreCase(factionName)) {
				faction.addMember(name);
			}
		}
	}
	
	public void removeFaction(String name) {
		for(Faction faction: factions) {
			if(faction.getName().equals(name)) {
				factions.remove(faction);
				return;
			}
		}
		System.out.println("invalid faction name");
	}
	
	public void playerLeaved(Faction faction) {
		/*
		if(faction.getPlayers().size() == 0) {
		factions.remove(faction);
		}
		*/
	}
	
	public String returnFactionNames() {
		String temp = ChatColor.BOLD + "Factions:\n";
		for(Faction faction : factions) {
			temp += ChatColor.AQUA + faction.getName() + ": ";
			temp += ChatColor.YELLOW + "Owner: " + faction.getOwner() + "// ";
			temp += "Members: " + faction.getPlayers();
			temp += ChatColor.RED +  " // Claim Strength: " + faction.getClaimStrenth() + "\n\n";
		}
		if(temp.equals("")) {
			temp = "no factions currently exist";
		}
		return temp;
	}
	
	
	public Faction getFaction(String name) {
		for(Faction faction : factions) {
			if(faction.getName().equalsIgnoreCase(name)) {
				return faction;
			}
		}
		return null;
	}
	
	public Faction getPlayerFaction(String player) {
		Faction faction = null;
		for(Faction fact : factions) {
			if(fact.getPlayers().contains(player)) {
				faction = fact;
			}
		}
		return faction;
	}
	public void claimChunk(Player player) {
		Faction claimer = null;
		Faction claimed = null;
		for(Faction fact : factions) {
			if(fact.getPlayers().contains(player.getName())) {
				claimer = fact;
			}
			if(fact.claims.contains(player.getLocation().getChunk())) {
				claimed = fact;
			}
		}
		if(claimer == null) {
			player.sendMessage(ChatColor.RED + "you must be part of a faction to claim chunks");
		}else if(claimed == null) {
			claimer.addClaim(player.getLocation().getChunk());
			player.sendMessage(ChatColor.GREEN + "sucessfully claimed");
		} else if(claimer.equals(claimed)){
			player.sendMessage(ChatColor.RED + "your faction already owns this chunk");
		} else {
			if(claimer.getClaimStrenth() > claimed.getClaimStrenth()) {
				claimed.removeClaim(player.getLocation().getChunk());
				claimer.addClaim(player.getLocation().getChunk());
				player.sendMessage(ChatColor.GREEN + "sucessfully over-claimed");
			} else {
				player.sendMessage(ChatColor.RED + "your faction does not have the claim strength to over-claim");
			}
		}
		
	}
	public void removeClaimedChunk(Faction faction, Chunk chunk) {
		for(Faction fact : factions) {
			if(fact.equals(faction)) {
				fact.removeClaim(chunk);
			}
		}
	}
	
	public String getChunkInfo(Player player) {
		Faction faction = null;
		for(Faction f : factions) {
			for(Chunk c : f.claims) {
				if(c.equals(player.getLocation().getChunk())) {
					faction = f;
				}
			}
		}
		if(faction != null) {
			return ChatColor.YELLOW + "claimed by " + faction.getName();
		}else if(spawnChunks.contains(player.getLocation().getChunk())){
			String temp = ChatColor.AQUA + "spawn";
			return temp;
		} else {
			return ChatColor.DARK_GREEN + "wilderness";
		}
	}
	
	public String getChunkInfo(Chunk chunk) {
		Faction faction = null;
		for(Faction f : factions) {
			for(Chunk c : f.claims) {
				if(c.equals(chunk)) {
					faction = f;
				}
			}
		}
		if(faction != null) {
			return ChatColor.YELLOW + "claimed by " + faction.getName();
		}else if(spawnChunks.contains(chunk)){
			String temp = ChatColor.AQUA + "spawn";
			return temp;
		} else {
			return ChatColor.DARK_GREEN + "wilderness";
		}
	}
	
	public void addSpawnChunk(Chunk chunk) {
		spawnChunks.add(chunk);
		System.out.println("added spawn chunk: " + spawnChunks);
	}
	
	public void removeSpawnChunk(Chunk chunk) {
		spawnChunks.remove(chunk);
	}
	
	public void teleportPlayerToFactionHome(Player player) throws InterruptedException{
		Faction faction = getPlayerFaction(player.getName());
		if(faction != null && faction.getFactionHome() != null) {
			int[] x = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
			for(int i = 0; i < 10; i ++) {
				player.sendMessage(ChatColor.YELLOW + "teleporting in " + x[i]);
	            Thread.sleep(1000);
			}
			player.teleport(faction.getFactionHome());
		} else if(faction == null){
			player.sendMessage(ChatColor.RED + "you must be part of a faction to use this command");
		} else if(faction.getFactionHome() == null) {
			player.sendMessage(ChatColor.RED + "your faction does not have a home set");
		}
	}
	

	
	@EventHandler
	public void playerInteractEvent(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();
		Chunk chunk = block.getLocation().getChunk();
		String player = event.getPlayer().getName();
		Faction faction = null;
		ArrayList<Material> okInteract = new ArrayList<Material>();
		okInteract.add(Material.AIR);
		okInteract.add(Material.CHEST);
		okInteract.add(Material.ENDER_CHEST);
		okInteract.add(Material.OAK_BUTTON);
		okInteract.add(Material.OAK_PRESSURE_PLATE);
		
		for(Faction f : factions) {
			for(Chunk c : f.claims) {
				if(c.equals(chunk)) {
					faction = f;
				}
			}
		}
		if(!(faction == null) && !(faction.getPlayers().contains(player)) && (!okInteract.contains(block.getType())) && ((HumanEntity)event.getPlayer()).getGameMode() != GameMode.CREATIVE) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED + "you may not interact with blocks here as it is claimed by " + faction.getName());
		}else if(spawnChunks.contains(chunk) && (!okInteract.contains(block.getType())) && ((HumanEntity)event.getPlayer()).getGameMode() != GameMode.CREATIVE) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED + "You may not break or place blocks in spawn ");
		}
		
	}
	


}
