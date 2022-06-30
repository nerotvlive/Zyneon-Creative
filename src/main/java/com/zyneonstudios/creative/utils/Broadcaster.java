package com.zyneonstudios.creative.utils;

import com.zyneonstudios.api.utils.Strings;
import com.zyneonstudios.creative.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import static org.bukkit.Bukkit.getServer;

public class Broadcaster {

    static ArrayList<String> Messages = new ArrayList<>();

    private static void saveDefaultConfig() {
        Main.getConfiguration().checkEntry("Core.Settings.Broadcasts.Enable",false);
        Main.getConfiguration().checkEntry("Core.Settings.Broadcasts.SecondInterval",10);
        Main.getConfiguration().checkEntry("Core.Strings.Broadcasts",Messages);
        Messages = (ArrayList<String>)Main.getConfiguration().getCFG().getList("Core.Strings.Broadcasts");
        Main.getConfiguration().checkEntry("Core.Actionbar.Message","test");
        Main.getConfiguration().saveConfig();
        Main.getConfiguration().reloadConfig();
    }

    public static void send() {
        saveDefaultConfig();
        sendActionbar(getServer().getScheduler());
        if(Main.getConfiguration().getCFG().getBoolean("Core.Settings.Broadcasts.Enable")) {
            startBroadcastTimer(getServer().getScheduler());
        }
    }

    private static void sendActionbar(BukkitScheduler scheduler) {
        int scheduleId = scheduler.scheduleSyncDelayedTask(Main.getInstance(), () -> {
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendActionBar(Main.getConfiguration().getCFG().getString("Core.Actionbar.Message"));
            }
            sendActionbar(scheduler);
        },10);
    }

    private static void startBroadcastTimer(BukkitScheduler scheduler) {
        int scheduleId = scheduler.scheduleSyncDelayedTask(Main.getInstance(), () -> {
            Integer size = Messages.size();
            Integer random = ThreadLocalRandom.current().nextInt(0,size);
            Bukkit.broadcastMessage(Strings.prefix()+Messages.get(random).replace("&","§"));
            startBroadcastTimer(scheduler);
        }, Main.getConfiguration().getCFG().getLong("Core.Settings.Broadcasts.SecondInterval")*20);
    }
}