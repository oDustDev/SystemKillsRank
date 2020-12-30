package com.dustdev.systemkillsrank.listeners;

import com.dustdev.systemkillsrank.SystemKillsRank;
import com.dustdev.systemkillsrank.api.ConfigAPI;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void aoMorrer(PlayerDeathEvent e) {
        if(e.getEntity().getKiller() instanceof Player) {
            Player p = e.getEntity().getKiller();
            ConfigAPI c = SystemKillsRank.get().c;
            for (String key : c.getSection("Ranks").getKeys(false)) {
                if (p.getStatistic(Statistic.PLAYER_KILLS) >= SystemKillsRank.ranks.getKills(key)) {
                    SystemKillsRank.ranks.setRank(p.getName(), SystemKillsRank.ranks.getPrefix(key));
                    p.sendMessage("§aVocê upou para o rank " + SystemKillsRank.ranks.getPrefix(key));
                }
            }
        }
    }
}
