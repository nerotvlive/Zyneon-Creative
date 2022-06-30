package com.zyneonstudios.creative.commands;

import com.zyneonstudios.api.utils.Strings;
import com.zyneonstudios.creative.Main;
import com.zyneonstudios.creative.utils.CreativeUser;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearchatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Clearchat")) {
            if(!(s instanceof Player p)) {
                for(Player all : Bukkit.getOnlinePlayers()) {
                    if(all.hasPermission("zyneon.team")) {
                        CreativeUser t = Main.creativeUsers.get(all.getUniqueId());
                        t.sendMessage("§7Der Chat wurde geleert, aber du kannst ihn noch sehen, weil du die Rechte dazu hast.");
                    } else {
                        for (int i = 0; i < 500; i++) {
                            all.sendMessage("");
                        }
                    }
                }
                Bukkit.getServer().getConsoleSender().sendMessage("§7Der Chat wurde geleert, aber du kannst ihn noch sehen, weil du die Rechte dazu hast.");
            } else {
                CreativeUser u = Main.creativeUsers.get(p.getUniqueId());
                if(p.hasPermission("zyneon.team")) {
                    p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG,100,100);
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        if(all.hasPermission("zyneon.team")) {
                            CreativeUser t = Main.creativeUsers.get(all.getUniqueId());
                            t.sendMessage("§7Der Chat wurde geleert, aber du kannst ihn noch sehen, weil du die Rechte dazu hast.");
                        } else {
                            for (int i = 0; i < 500; i++) {
                                all.sendMessage("");
                            }
                        }
                    }
                    Bukkit.getServer().getConsoleSender().sendMessage("§7Der Chat wurde geleert, aber du kannst ihn noch sehen, weil du die Rechte dazu hast.");
                } else {
                    u.sendErrorMessage(Strings.noPerms());
                }
            }
        }
        return false;
    }
}