package com.zyneonstudios.creative.commands;

import com.zyneonstudios.creative.Main;
import com.zyneonstudios.creative.utils.CreativeUser;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PingCommand implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender s, Command cmd, @NotNull String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Ping")) {
            if(!(s instanceof Player p)) {
                s.sendMessage("Du hast einen Ping von §a0ms§7!");
            } else {
                CreativeUser u = Main.creativeUsers.get(p.getUniqueId());
                if(p.getName().equalsIgnoreCase("ideallauch")) {
                    int Ping = getPing(p);
                    String ping;
                    if (Ping < 10) {
                        ping = "§5" + Ping;
                    } else if (Ping < 20) {
                        ping = "§d" + Ping;
                    } else if (Ping < 30) {
                        ping = "§e" + Ping;
                    } else if (Ping < 40) {
                        ping = "§6" + Ping;
                    } else if (Ping < 100) {
                        ping = "§c" + Ping;
                    } else {
                        ping = "§4" + Ping;
                    }
                    u.sendMessage("Du hast einen Ping von " + ping + "ms§7!");
                } else {
                    int Ping = getPing(p);
                    String ping;
                    if(Ping < 10) {
                        ping = "§a"+Ping;
                    } else if(Ping < 20) {
                        ping = "§2"+Ping;
                    } else if(Ping < 30) {
                        ping = "§e"+Ping;
                    } else if(Ping < 40) {
                        ping = "§6"+Ping;
                    } else if(Ping < 100) {
                        ping = "§c"+Ping;
                    } else {
                        ping = "§4"+Ping;
                    }
                    u.sendMessage("Du hast einen Ping von "+ping+"ms§7!");
                }
            }
        }
        return false;
    }

    private int getPing(Player player) {
        String v = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        if (!player.getClass().getName().equals("org.bukkit.craftbukkit." + v + ".entity.CraftPlayer")) {
            player = Bukkit.getPlayer(player.getUniqueId());
        }
        try {
            assert player != null;
            return player.getPing();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}