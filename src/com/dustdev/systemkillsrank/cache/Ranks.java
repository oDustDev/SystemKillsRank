package com.dustdev.systemkillsrank.cache;

import com.dustdev.systemkillsrank.SystemKillsRank;
import com.dustdev.systemkillsrank.api.ConfigAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ranks {

    protected static HashMap<String, String> prefix = new HashMap<>();
    protected static HashMap<String, Integer> kills = new HashMap<>();
    protected static HashMap<String, String> rank = new HashMap<>();


    public void loadRanks() {
        ConfigAPI c = SystemKillsRank.get().c;
        ConfigAPI crank = SystemKillsRank.get().rank;
        for(String key : c.getSection("Ranks").getKeys(false)) {
            prefix.put(key, c.getString("Ranks."+key+".prefix"));
            kills.put(key, c.getInt("Ranks."+key+".kills"));
        }
        for(String ran : crank.getSection("Jogadores").getKeys(false)) {
            rank.put(ran, crank.getString("Jogadores."+ran+".rank"));
        }
        Bukkit.getServer().getConsoleSender().sendMessage("§a[SystemKillsRank] foram carregados §f"+prefix.size()+"§a ranks.");
    }

    public void save() {
        for(String key : rank.keySet()) {
            ConfigAPI crank = SystemKillsRank.get().rank;
            crank.set("Jogadores."+key+".rank", rank.get(key));
            crank.saveConfig();
            Bukkit.getServer().getConsoleSender().sendMessage("§a[SystemKillsRank] foram salvos §f"+rank.size()+"§a jogadores.");
        }
    }

    public void addRank(String key) {
        if(!rank.containsKey(key)) {
           rank.put(key, "&cNenhum");
        }
    }
    public void setRank(String key, String rankp) {
        rank.put(key, rankp);
    }

    public String getRank(String key) {
        return rank.get(key).replace("&", "§");
    }

    public String getPrefix(String key) {
        return prefix.get(key).replace("&", "§");
    }

    public int getKills(String key) {
        return kills.get(key);
    }

}
