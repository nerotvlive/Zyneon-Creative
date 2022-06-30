package com.zyneonstudios.creative.listeners;

import com.zyneonstudios.creative.Main;
import com.zyneonstudios.creative.utils.CreativeUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.setOp(false);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"deop "+p.getName());
        e.setJoinMessage(null);
        Main.creativeUsers.remove(p.getUniqueId());
        Main.creativeUsers.put(p.getUniqueId(),new CreativeUser(p.getUniqueId()));
        CreativeUser u = Main.creativeUsers.get(p.getUniqueId());
        u.initUser();
        for(Player all: Bukkit.getOnlinePlayers()) {
            if(all.getUniqueId()!=p.getUniqueId()) {
                all.sendMessage("§8» §a"+p.getName());
            }
        }
    }
}