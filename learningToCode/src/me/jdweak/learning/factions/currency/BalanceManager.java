package me.jdweak.learning.factions.currency;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class BalanceManager implements Listener{
	
	
	private static BalanceManager balanceManager;
	private BalanceManager() {
	}
	public static synchronized BalanceManager getInstance() {
		if(balanceManager == null) {
			balanceManager = new BalanceManager();
		}
		return balanceManager;
	}
	
	public ArrayList<Balance> balances = new ArrayList<Balance>();
	
	@EventHandler
	public void playerJoinEvent(PlayerJoinEvent event) {
		if(findPlayerBalance(event.getPlayer().getName()) != -1) {
			createBalance(event.getPlayer().getName());
		}
	}
	
	@EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if ((event.getEntity() instanceof Player)) {
            Player player = event.getEntity();
            Player killer = player.getKiller();
        }
    }
	
	public int findPlayerBalance(String player) {
		int index = 0;
		int x = -1;
		for(Balance balance : balances) {
			if(balance.getPlayer().equalsIgnoreCase(player)) {
				x = index;
			}
			index ++;
		}
		return x;
	}
	
	public void createBalance(String player) {
		Balance balance = new Balance();
		balance.setPlayer(player);
		balances.add(balance);
	}
	
	public boolean hasAmount(Balance balance, double x) {
		if(balance.getAmount() > x) {
			return true;
		} else {
			return false;
		}
	}

}
