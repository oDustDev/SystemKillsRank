package com.dustdev.systemkillsrank.commands;

import com.dustdev.systemkillsrank.inventory.KillsInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            if(cmd.getName().equalsIgnoreCase("kills")) {
                Player p = (Player)sender;
                KillsInventory.open(p);
            }
        } else {
            sender.sendMessage("§cApenas jogadores este comando.");
        }
        return false;
    }
}
