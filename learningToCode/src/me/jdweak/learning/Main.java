package me.jdweak.learning;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.jdweak.learning.commands.JdweakCommandFactory;
import me.jdweak.learning.events.EventFactory;
import me.jdweak.learning.factions.FactionManager;
import me.jdweak.learning.factions.factioncommands.FactionCommandFactory;
import me.jdweak.learning.factions.factioncommands.LocationCommands;
import me.jdweak.learning.factions.items.ChooseFactionBook;
import me.jdweak.learning.items.TeleportStaff;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin{
	
	//Factories/////////////////////////
	EventFactory eventFactory = new EventFactory();
	JdweakCommandFactory commandFactory = new JdweakCommandFactory();
	FactionCommandFactory factionCommandFactory = new FactionCommandFactory();
	
	
	//Command instantiation/////////////////////

	
	//Listeners//////////////////////////////
	Listener eventClass = eventFactory.createEvent("EventClass");
	
	public CommandExecutor getFactoryCommand(String className, String type) {
		CommandExecutor command = null;
		try {
			switch(type) {
				case("faction"):
					command = factionCommandFactory.createCommand(className);
					System.out.println(command);
					break;
				case("command"):
					command = commandFactory.createJdweakCommand(className);
					break;
				case("balance"):
					//balance command factory
					break;
				default: 
					command = null;
					System.out.println("DEFAULT THROWN");
					break;
			}
		} catch (InstantiationException e) {
			System.out.println(e.toString());
		} catch (IllegalAccessException e) {
			System.out.println(e.toString());
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}
		getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "\nInside getFactoryCommand method, returning " + command);
		return command;
	}
	
	
	
	public void onEnable() {

		FactionManager.getInstance().createPatrolFactions();
		
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "\n\n plugin enabled\n\n");

		//Events///////////////////////////////////////
		getServer().getPluginManager().registerEvents(eventClass, this);
		getServer().getPluginManager().registerEvents(new TeleportStaff(), this);
		getServer().getPluginManager().registerEvents(FactionManager.getInstance(), this);
		getServer().getPluginManager().registerEvents(new ChooseFactionBook(), this);
		
		
		//Commands/////////////////////////////////////
		getCommand("customEnchant").setExecutor(getFactoryCommand("CustomEnchant", "command"));
		getCommand("give_custom_item_prototype").setExecutor(getFactoryCommand("GiveCustomItemPrototype", "command"));
		getCommand("location").setExecutor(getFactoryCommand("Location", "command"));
		
		getCommand("chunkClaimInfo").setExecutor(getFactoryCommand("FactionChunkInfoCommand", "faction"));
		getCommand("claim").setExecutor(getFactoryCommand("FactionClaimCommand", "faction"));
		//getCommand("createFaction").setExecutor(getFactoryCommand("FactionCreateCommand", "faction"));
		getCommand("factionInfo").setExecutor(getFactoryCommand("FactionInfoCommand", "faction"));
		getCommand("leaveFaction").setExecutor(getFactoryCommand("FactionLeaveCommand", "faction"));
		getCommand("unclaim").setExecutor(getFactoryCommand("FactionUnclaimCommand", "faction"));
		getCommand("giveFactionBook").setExecutor(getFactoryCommand("FactionGetPatrolBookCommand", "faction"));
		getCommand("setFactionHome").setExecutor(getFactoryCommand("FactionSetHomeCommand", "faction"));
		getCommand("fHome").setExecutor(getFactoryCommand("FactionHomeCommand", "faction"));
		getCommand("setFactionOwner").setExecutor(getFactoryCommand("FactionSetOwnerCommand", "faction"));
		getCommand("setSpawnChunk").setExecutor(getFactoryCommand("FactionSpawnClaimCommand", "faction"));
		getCommand("removeSpawnChunk").setExecutor(getFactoryCommand("FactionRemoveSpawnClaim", "faction"));
		
		CommandExecutor locationCommands = new LocationCommands();

		getCommand("setHome").setExecutor(locationCommands);
		getCommand("home").setExecutor(locationCommands);
		
		

	}
	
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "\n\n plugin disabled\n\n");
		
	}

}
