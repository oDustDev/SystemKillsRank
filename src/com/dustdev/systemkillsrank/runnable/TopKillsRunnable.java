package com.dustdev.systemkillsrank.runnable;

import com.dustdev.systemkillsrank.SystemKillsRank;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class TopKillsRunnable extends BukkitRunnable {
    @Override
    public void run() {
        HashMap<Player, Integer> top = new HashMap<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            top.put(p, p.getStatistic(Statistic.PLAYER_KILLS));
        }
        int i = 0;
        for (Map.Entry<Player, Integer> a : entriesSortedByValues(top)) {
            i++;
            if (i <= 10) {
                SystemKillsRank.jogadores.killsPut(i, a.getKey());
            }
        }
    }

    public static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
        SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
                int res = e2.getValue().compareTo(e1.getValue());
                return res != 0 ? res : 1;
            }
        });
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

}
