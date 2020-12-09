
package me.jdweak.learning.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CustomEnchant extends JdweakCommand{

	public String name = "test";
	
	@Override
	public boolean onCommand( CommandSender Sender,  Command command,  String label,
			 String[] args) {
		Player player = (Player) Sender;
		if(player.hasPermission("op")) {
		@SuppressWarnings("deprecation")
		ItemStack item = player.getItemInHand(); 
		for(Enchantment enchantment : Enchantment.values()) {
			String temp = enchantment.toString();
			temp = temp.substring(temp.indexOf(":") + 1, temp.indexOf(','));
			if(args[0].equalsIgnoreCase(temp)) {
				item.addUnsafeEnchantment(enchantment, Integer.parseInt(args[1]));
				return false;
			}
		}
		player.sendMessage("Invalid Syntax");
		}else {
			player.sendMessage(ChatColor.RED + "you don't have permission to execute this command");
		}
		return false;
	}
	

}
