package me.jdweak.learning.factions.factioncommands;

public class FactionCommandFactory {
	
	public FactionCommand createCommand(String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class<?> c = Class.forName("me.jdweak.learning.factions.factioncommands." + name);
		@SuppressWarnings("deprecation")
		FactionCommand command = (FactionCommand) c.newInstance();
		System.out.println("inside factory, returning " + command);
		return command;
	}

}
