package com.dustdev.systemkillsrank.inventory;

import com.dustdev.systemkillsrank.SystemKillsRank;
import com.dustdev.systemkillsrank.utils.Scroller;
import com.dustdev.systemkillsrank.utils.ScrollerInventory;
import com.dustdev.systemkillsrank.utils.ScrollerRecompensas;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class RankingInventory {

    public static void open(Player p) {
        ArrayList<ItemStack> items = new ArrayList<>();
        for(Map.Entry<Integer, Player> a : SystemKillsRank.jogadores.killsEntrySet()) {

            ItemStack item = new ItemStack(Material.SKULL_ITEM,1,(short)3);
            SkullMeta meta = (SkullMeta)item.getItemMeta();
            meta.setOwner(a.getValue().getPlayer().getName());
            meta.setDisplayName("§f#"+a.getKey()+"§e "+a.getValue().getName());
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("§fKills: §6"+a.getValue().getStatistic(Statistic.PLAYER_KILLS));
            lore.add("§fPosição: §6"+a.getKey());
            lore.add("");
            meta.setLore(lore);
            item.setItemMeta(meta);
            items.add(item);

        }
        Scroller scroller = new Scroller.ScrollerBuilder()
                .withSize(6*9)
                .withName("TOP Kills")
                .withItems(items)
                .withItemsSlots(10,11,12,13,14,15,16,21,22,23)
                .withArrowsSlots(18, 26)
                .build();

        scroller.open(p);
    }

}
