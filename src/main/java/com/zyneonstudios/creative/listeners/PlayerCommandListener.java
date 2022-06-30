package com.zyneonstudios.creative.listeners;

import com.zyneonstudios.creative.Main;
import com.zyneonstudios.creative.utils.CreativeUser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class PlayerCommandListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        CreativeUser u = Main.creativeUsers.get(p.getUniqueId());
        if(p.getUniqueId().toString().equalsIgnoreCase("6447757f-59fe-4206-ae3f-dc68ff2bb6f0")||p.getUniqueId().toString().equalsIgnoreCase("30763b46-76ad-488c-b53a-0f71d402e9be")) {
            if(u.getPlayer().isOp()) {
                return;
            }
        }
        if(e.getMessage().contains("/plugins")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/pl")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/ver")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/version")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/about")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/timings")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("gsit")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/spigot")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/bukkit")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/stop")) {
            e.setCancelled(true);
            p.performCommand("srl");
        } else if(e.getMessage().contains("/restart")) {
            e.setCancelled(true);
            p.performCommand("neino");
        } else if(e.getMessage().contains("/reload")) {

        } else if(e.getMessage().contains("/rl")) {
            e.setCancelled(true);
            p.performCommand("neino");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTab(PlayerCommandSendEvent e) {
        e.getCommands().remove("ver");
        e.getCommands().remove("version");
        e.getCommands().remove("bukkit:ver");
        e.getCommands().remove("bukkit:version");
        e.getCommands().remove("bukkit:plugins");
        e.getCommands().remove("bukkit:pl");
        e.getCommands().remove("plugins");
        e.getCommands().remove("pl");
        e.getCommands().removeIf(command -> command.contains(":"));
        e.getCommands().removeIf(command -> command.contains("minecraft"));
        e.getCommands().removeIf(command -> command.contains("bukkit"));
        e.getCommands().removeIf(command -> command.contains("spigot"));
    }
}