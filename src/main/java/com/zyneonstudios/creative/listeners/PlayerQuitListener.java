package com.zyneonstudios.creative.listeners;

import com.zyneonstudios.creative.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        Main.creativeUsers.remove(p.getUniqueId());
        e.setQuitMessage("§8« §c" + p.getName());
    }
}