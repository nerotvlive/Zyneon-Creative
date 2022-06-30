package com.zyneonstudios.creative.commands;

import com.zyneonstudios.api.utils.Strings;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(s instanceof Player p) {
            p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            p.playSound(p.getLocation(),Sound.ENTITY_CHICKEN_EGG,100,100);
            p.playSound(p.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,100,100);
        } else {
            s.sendMessage(Strings.needPlayer());
        }
        return false;
    }
}