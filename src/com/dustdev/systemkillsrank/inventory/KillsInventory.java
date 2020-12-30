package com.dustdev.systemkillsrank.inventory;

import com.dustdev.systemkillsrank.SystemKillsRank;
import com.dustdev.systemkillsrank.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class KillsInventory {

    public static void open(Player p) {
        Inventory inv = Bukkit.createInventory(p, 3*9, "Kills");

        inv.setItem(11, new ItemBuilder(Material.SKULL_ITEM)
                .setName(p.getName())
                .setSkullOwner(p.getName())
                .setDurability((short)3)
                .setLore(
                        "",
                        "§7Você matou: §f"+p.getStatistic(Statistic.PLAYER_KILLS)+"§a jogadores§7.",
                        "§7Rank: "+SystemKillsRank.ranks.getRank(p.getName()),
                        ""
                ).toItemStack());

        inv.setItem(13, new ItemBuilder(Material.CHEST)
                .setName("§aRecompensas")
                .setLore("§7Clique para gerenciar as recompensas").toItemStack());

        inv.setItem(15, new ItemBuilder(Material.BOOK)
                .setName("§aTOP")
                .setLore("§7Clique para ver os jogadores com mais abates.").toItemStack());

        p.openInventory(inv);
    }
}
