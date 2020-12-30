package com.dustdev.systemkillsrank.listeners;

import com.dustdev.systemkillsrank.SystemKillsRank;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener implements Listener {

    @EventHandler
    public void aoClicar(PlayerLoginEvent e) {
        SystemKillsRank.ranks.addRank(e.getPlayer().getName());
        SystemKillsRank.jogadores.addCache(e.getPlayer().getName());
    }
}
