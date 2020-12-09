package me.jdweak.learning.commands;
public class JdweakCommandFactory {
	public JdweakCommand createJdweakCommand(String command) {
		if(command.equals("CustomEnchant")) { 
			System.out.println("\ncommand 1 instantiated");
			return new CustomEnchant();
		} else if(command.equals("GiveCustomItemPrototype")) {
			System.out.println("command 2 instantiated");
			return new GiveCustomItemPrototype();
		} else if(command.equalsIgnoreCase("Location")){
			System.out.println("command 3 instantiated");
			return new Location();
		} else {
			return null;
		}
		
	}

}
