package com.zyneonstudios.creative.commands;

import com.zyneonstudios.api.Zyneon;
import com.zyneonstudios.api.utils.Strings;
import com.zyneonstudios.creative.Main;
import com.zyneonstudios.creative.utils.CreativeUser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("SRL")) {
            if(s.hasPermission("zyneon.leading")) {
                if(s instanceof Player p) {
                    CreativeUser u = Main.creativeUsers.get(p.getUniqueId());
                    u.sendMessage("§7Du hast den §aStopvorgang §7gestartet§8...");
                } else {
                    s.sendMessage(Strings.prefix()+"§7Du hast den §aStopvorgang §7gestartet§8...");
                }
                Zyneon.getZyneonServer().stopServer();
            } else {
                if(s instanceof Player p) {
                    CreativeUser u = Main.creativeUsers.get(p.getUniqueId());
                    u.sendErrorMessage(Strings.noPerms());
                } else {
                    s.sendMessage(Strings.noPerms());
                }
            }
        }
        return false;
    }
}
