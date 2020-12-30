package com.dustdev.systemkillsrank;

import com.dustdev.systemkillsrank.api.ConfigAPI;
import com.dustdev.systemkillsrank.cache.Data;
import com.dustdev.systemkillsrank.cache.Ranks;
import com.dustdev.systemkillsrank.cache.Recompensas;
import com.dustdev.systemkillsrank.commands.KillsCommand;
import com.dustdev.systemkillsrank.listeners.KillsInventoryListener;
import com.dustdev.systemkillsrank.listeners.PlayerDeathListener;
import com.dustdev.systemkillsrank.listeners.PlayerLoginListener;
import com.dustdev.systemkillsrank.runnable.TopKillsRunnable;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SystemKillsRank extends JavaPlugin {

    public static Recompensas recompensas;
    public static Ranks ranks;
    public static Data jogadores;
    public ConfigAPI c;
    public ConfigAPI data;
    public ConfigAPI rank;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§2[SystemKillsRank] plugin ativado com sucesso.");
        c = new ConfigAPI("config.yml", this);
        data = new ConfigAPI("data.yml", this);
        rank = new ConfigAPI("rank.yml", this);

        c.saveDefaultConfig();
        data.saveDefaultConfig();
        rank.saveDefaultConfig();

        recompensas = new Recompensas();
        ranks = new Ranks();
        jogadores = new Data();

        recompensas.loadRecompensas();
        ranks.loadRanks();
        jogadores.load();

        getCommand("kills").setExecutor(new KillsCommand());

        Bukkit.getPluginManager().registerEvents(new KillsInventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLoginListener(), this);

        new TopKillsRunnable().runTaskTimer(this, 40L, 20L*60*2);
    }

    @Override
    public void onDisable() {
        jogadores.save();
        ranks.save();
    }

    public static SystemKillsRank get() {
        return JavaPlugin.getPlugin(SystemKillsRank.class);
    }
}
