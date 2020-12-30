package com.dustdev.systemkillsrank.listeners;

import com.dustdev.systemkillsrank.SystemKillsRank;
import com.dustdev.systemkillsrank.commands.KillsCommand;
import com.dustdev.systemkillsrank.inventory.KillsInventory;
import com.dustdev.systemkillsrank.inventory.RankingInventory;
import com.dustdev.systemkillsrank.inventory.RecompensasInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class KillsInventoryListener implements Listener {

    @EventHandler
    public void aoClicar(InventoryClickEvent e) {
        if(e.getWhoClicked() instanceof Player) {
            Player p = (Player)e.getWhoClicked();
            if(e.getInventory().getName().equalsIgnoreCase("Kills")) {
                e.setCancelled(true);
                switch (e.getRawSlot()) {
                    case 13:
                        RecompensasInventory.open(p);
                        break;
                    case 15:
                        if(SystemKillsRank.jogadores.killsSize() < 1) {
                            p.sendMessage("§cAguarde o ranking de jogadores carregar.");
                            return;
                        }
                        RankingInventory.open(p);
                        break;
                }
            }
        }
    }
}
