package com.zyneonstudios.creative.utils;

import com.zyneonstudios.api.utils.user.User;
import com.zyneonstudios.creative.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CreativeUser extends User {

    public CreativeUser(UUID uuid) {
        super(uuid);
    }

    public void initUser() {
        if(getPlayer()!=null) {
            getPlayer().setPlayerListHeader(Main.getConfiguration().getCFG().getString("Core.Tablist.Header").replace("&", "§"));
            getPlayer().setPlayerListFooter(Main.getConfiguration().getCFG().getString("Core.Tablist.Footer").replace("&", "§"));
            for(Player all:Bukkit.getOnlinePlayers()) {
                Main.setPrefix(all);
            }
            getPlayer().setGameMode(GameMode.CREATIVE);
        }
    }
}
