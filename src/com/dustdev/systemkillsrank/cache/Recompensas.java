package com.dustdev.systemkillsrank.cache;

import com.dustdev.systemkillsrank.SystemKillsRank;
import com.dustdev.systemkillsrank.api.ConfigAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Recompensas {

    protected static HashMap<String, String> title = new HashMap<>();
    protected static HashMap<String, List> lore = new HashMap<>();
    protected static HashMap<String, List> commands = new HashMap<>();
    protected static HashMap<String, Integer> kills = new HashMap<>();

    public void loadRecompensas() {
        ConfigAPI c = SystemKillsRank.get().c;
        for(String key : c.getSection("Recompensas").getKeys(false)) {
            title.put(key, c.getString("Recompensas."+key+".title"));
            lore.put(key, c.getStringList("Recompensas."+key+".lore"));
            commands.put(key, c.getStringList("Recompensas."+key+".commands"));
            kills.put(key, c.getInt("Recompensas."+key+".kills"));
        }
        Bukkit.getServer().getConsoleSender().sendMessage("§a[SystemKillsRank] foram carregadas §f"+title.size()+"§a recompensas.");
    }

    public String getTitle(String key) {
        return title.get(key).replace("&", "§");
    }

    public List getLore(String key) {
        return coloredLore(lore.get(key));
    }

    public List getCommands(String key, Player p) {
        return player(lore.get(key), p);
    }

    public int getKills(String key) {
        return kills.get(key);
    }

    private static List<String> coloredLore(List<String> oldLore) {
        List<String> coloredLore = new ArrayList<String>();
        for (String s : oldLore) {
            coloredLore.add(s.replace("&", "§"));
        }
        return coloredLore;
    }

    private static List<String> player(List<String> oldLore, Player p) {
        List<String> coloredLore = new ArrayList<String>();
        for (String s : oldLore) {
            coloredLore.add(s.replace("{player}", p.getName()));
        }
        return coloredLore;
    }

}
