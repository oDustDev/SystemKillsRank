package com.dustdev.systemkillsrank.cache;

import com.dustdev.systemkillsrank.SystemKillsRank;
import com.dustdev.systemkillsrank.api.ConfigAPI;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;

public class Data {

    protected static HashMap<String, Boolean> cache = new HashMap<>();
    protected static HashMap<Integer, Player> kills = new HashMap<>();

    public void load() {
        ConfigAPI c = SystemKillsRank.get().data;
        for(String jogador : c.getSection("Jogadores").getKeys(false)) {
            for(String recompensa : c.getSection("Jogadores."+jogador).getKeys(false)) {
                cache.put(jogador+"."+recompensa, c.getBoolean("Jogadores." + jogador + "." + recompensa));
            }
        }
    }

    public void save() {
        for(String key : cache.keySet()) {
            ConfigAPI c = SystemKillsRank.get().data;
            c.set("Jogadores."+key, cache.get(key));
            c.saveConfig();
        }
    }

    public Boolean getCache(String jogador, String recompensa) {
        return cache.get(jogador+"."+recompensa);
    }

    public void setCache(String jogador, String recompensa, Boolean pegou) {
        cache.remove(jogador + "." + recompensa);
        cache.put(jogador + "." + recompensa, pegou);
    }

    public void addCache(String key) {
        ConfigAPI c = SystemKillsRank.get().c;
        if(!cache.containsKey(key)) {
            for(String recompensa : c.getSection("Recompensas").getKeys(false)) {
                cache.put(key+"."+recompensa, false);
            }
        }

   }

    public java.util.Set<java.util.Map.Entry<Integer, Player>> killsEntrySet() {
        return kills.entrySet();
    }

    public Player killsPut(int quantia, Player key) {
        return kills.put(quantia, key);
    }

    public Collection<Player> killsValues() {
        return kills.values();
    }

    public int killsSize() {
        return kills.size();
    }

}

