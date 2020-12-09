package me.jdweak.learning.factions.factioncommands;

import org.bukkit.command.CommandExecutor;

import me.jdweak.learning.factions.FactionManager;

public abstract class FactionCommand implements CommandExecutor{
	public String name;
	
	FactionManager factionManager = FactionManager.getInstance();
	
	public void test() {
		System.out.println("testing dynamic class loading");
	}
}
