package com.dustdev.systemkillsrank.inventory;

import com.dustdev.systemkillsrank.SystemKillsRank;
import com.dustdev.systemkillsrank.utils.ScrollerRecompensas;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class RecompensasInventory {

    public static void open(Player p) {
        List<ItemStack> items = new ArrayList<>();
        SystemKillsRank.get().c.getSection("Recompensas").getKeys(false).forEach(key -> {

            ItemStack item = new ItemStack(Material.EMERALD);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(SystemKillsRank.recompensas.getTitle(key));
            if(SystemKillsRank.jogadores.getCache(p.getName(), key)) {
                ArrayList<String> lore = new ArrayList<>();
                lore.add("§cVocê ja pegou esta recompensa.");
                meta.setLore(lore);
            } else {
                meta.setLore(SystemKillsRank.recompensas.getLore(key));
            }
            item.setItemMeta(meta);
            items.add(item);
        });
        ScrollerRecompensas scroller = new ScrollerRecompensas.ScrollerBuilder()
                .withSize(3*9)
                .withName("KillsRank recompensas ")
                .withItems(items)
                .withItemsSlots(11,12,13,14,15)
                .withArrowsSlots(9, 17)
                .build();

        scroller.open(p);
    }
}
