package me.jdweak.learning.factions;

import java.util.ArrayList;

import org.bukkit.Chunk;
import org.bukkit.Location;

public class Faction {
	
	private String owner;
	private ArrayList<String> members = new ArrayList<String>();
	private int claimStrength = 0;
	private String name = "";
	private ArrayList<PlayerLeavedListener> playerLeavedListeners = new ArrayList<PlayerLeavedListener>();
	public ArrayList<Chunk> claims = new ArrayList<Chunk>();
	private Location factionHome;

	public Faction(String name) {
		this.owner = "None";
		this.name = name;
		updateClaimStrength();
	}
	
	public Faction(String owner, String name) {
		this.owner = owner;
		this.name = name;
		members.add(owner);
		updateClaimStrength();
	}
	
	public Location getFactionHome() {
		return factionHome;
	}

	public void setFactionHome(Location factionHome) {
		this.factionHome = factionHome;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwner() {
		return owner;
	}
	
	public void addClaim(Chunk chunk) {
		if(claimStrength > 1 && !claims.contains(chunk)) {
		claims.add(chunk);
		updateClaimStrength();
	}
	}
	
	public void removeClaim(Chunk chunk) {
		claims.remove(chunk);
		updateClaimStrength();
	}
	
	public void updateClaimStrength() {
		claimStrength = members.size() * 10 - claims.size() * 2;
	}
	
	public void addPlayerLeavedListener(PlayerLeavedListener listener) {
		playerLeavedListeners.add(listener);
	}
	
	private void firePlayerLeavedListener() {
		for(PlayerLeavedListener listener : playerLeavedListeners) {
			listener.playerLeaved(this);
		}
	}
	
	public void addMember(String player) {
		members.add(player);
		updateClaimStrength();
	}
	
	public void removePlayer(String player) {
		members.remove(members.indexOf(player));
		firePlayerLeavedListener();
		updateClaimStrength();
		
	}
	
	public void setClaimStrength(int x) {
		claimStrength = x;
	}
	
	public ArrayList<String> getPlayers() {
		return(members);
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
	
	public int getClaimStrenth() {
		return claimStrength;
	}

}
