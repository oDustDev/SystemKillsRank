package com.dustdev.systemkillsrank.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.dustdev.systemkillsrank.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ScrollerInventory{

    public ArrayList<Inventory> pages = new ArrayList<Inventory>();
    public UUID id;
    public int currpage = 0;
    public int atual = 1;
    public static HashMap<UUID, ScrollerInventory> users = new HashMap<UUID, ScrollerInventory>();
    //Running this will open a paged inventory for the specified player, with the items in the arraylist specified.
    public ScrollerInventory(ArrayList<ItemStack> items, String name, Player p){
        this.id = UUID.randomUUID();
//create new blank page
        Inventory page = getBlankPage(name);
        atual++;
        //According to the items in the arraylist, add items to the ScrollerInventory

        int slot = 0;
        int a = -1;
        for(int b = 0;b < 1000; b++){
            if (a +1  == items.size()) {
                break;
            }
            if(slot == 35){
                pages.add(page);
                page = getBlankPage(name);
                atual++;
                slot = 0;
                int i = slot;
                if (i < 9 || i == 17 || i == 26 || i == 35 || i == 44 || i == 53 || i % 9 == 0) continue;
                a++;
                page.setItem(slot,items.get(a));
            }else{
                slot++;
                int i = slot;
                if (i < 9 || i == 17 || i == 26 || i == 35 || i == 44 || i == 53 || i % 9 == 0) continue;
                a++;
                page.setItem(slot,items.get(a));
            }
        }
        pages.add(page);
//open page 0 for the specified player
        p.openInventory(pages.get(currpage));
        users.put(p.getUniqueId(), this);
    }



    //This creates a blank page with the next and prev buttons
    private Inventory getBlankPage(String name){
        Inventory page = Bukkit.createInventory(null, 54, name);

        page.setItem(40, new ItemBuilder(Material.ARROW).setName("§aVoltar").toItemStack());
        return page;
    }
}