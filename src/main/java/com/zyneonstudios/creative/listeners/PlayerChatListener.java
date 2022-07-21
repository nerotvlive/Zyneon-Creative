package com.zyneonstudios.creative.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String Name;
        if(p.hasPermission("zyneon.team")) {
            Name = "§9Team §8● §f" + p.getName();
        } else if(p.hasPermission("zyneon.creator")) {
            Name = "§9Creator §8● §f" + p.getName();
        } else if(p.hasPermission("zyneon.premium")) {
            Name = "§9Premium §8● §f"+p.getName();
        } else {
            Name = "§9User §8● §f"+p.getName();
        }
        String MSG;
        if(p.hasPermission("zyneon.team")) {
            MSG = e.getMessage().replace("&","§");
        } else {
            MSG = e.getMessage();
        }
        MSG = MSG.replace("%","%%");
        e.setCancelled(true);
        Bukkit.broadcastMessage("%name%§8 » §7%msg%".replace("%name%", Name).replace("%msg%", MSG));
    }
}