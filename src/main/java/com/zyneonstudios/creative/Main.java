package com.zyneonstudios.creative;

import com.zyneonstudios.api.paper.Zyneon;
import com.zyneonstudios.api.paper.configuration.Config;
import com.zyneonstudios.api.utils.Strings;
import com.zyneonstudios.creative.commands.*;
import com.zyneonstudios.creative.listeners.PlayerChatListener;
import com.zyneonstudios.creative.listeners.PlayerCommandListener;
import com.zyneonstudios.creative.listeners.PlayerJoinListener;
import com.zyneonstudios.creative.listeners.PlayerQuitListener;
import com.zyneonstudios.creative.utils.Broadcaster;
import com.zyneonstudios.creative.utils.CreativeUser;
import com.zyneonstudios.creative.utils.Receiver;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {

    public static PluginManager PM = Bukkit.getPluginManager();
    public static HashMap<UUID, CreativeUser> creativeUsers = new HashMap<>();
    private static Main instance;
    private static Config config;

    @Override
    public void onLoad() {
        Strings.setPrefixWord("Creative");
        instance = this;
    }

    @Override
    public void onEnable() {
        Strings.setPrefixWord("Creative");
        config = new Config("plugins/Creative/config.yml");
        instance = this;
        Broadcaster.send();
        initConfig();
        initCommands();
        initListeners();
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(instance,"base:bungee",new Receiver());
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(instance,"base:spigot");
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(instance, "BungeeCord");
    }

    @Override
    public void onDisable() {
        Strings.setPrefixWord("Creative");
        Bukkit.getServer().getMessenger().unregisterIncomingPluginChannel(instance,"base:bungee",new Receiver());
        Bukkit.getServer().getMessenger().unregisterOutgoingPluginChannel(instance,"base:spigot");
        Bukkit.getServer().getMessenger().unregisterOutgoingPluginChannel(instance, "BungeeCord");
        config = null;
        PM = null;
        instance = null;
    }

    public static Config getConfiguration() {
        return config;
    }

    private static void initConfig()  {
        config.checkEntry("Core.Tablist.Header","Header");
        config.checkEntry("Core.Tablist.Footer","Footer");
        config.saveConfig();
        config.reloadConfig();
    }

    private static void initListeners()  {
        Zyneon.getAPI().initListenerClass(PM,new PlayerChatListener(),instance);
        Zyneon.getAPI().initListenerClass(PM,new PlayerCommandListener(),instance);
        Zyneon.getAPI().initListenerClass(PM,new PlayerJoinListener(),instance);
        Zyneon.getAPI().initListenerClass(PM,new PlayerQuitListener(),instance);
    }

    private static void initCommands() {
        getCommand(new BroadcastCommand(),"Broadcast");
        getCommand(new ClearchatCommand(),"Clearchat");
        getCommand(new DayCommand(),"Day");
        getCommand(new GamemodeCommand(),"GameMode");
        getCommand(new NightCommand(),"Night");
        getCommand(new PingCommand(),"Ping");
        getCommand(new RainCommand(),"Rain");
        getCommand(new SpawnCommand(),"Spawn");
        getCommand(new SpeedCommand(),"Speed");
        getCommand(new StopCommand(),"SRL");
        getCommand(new SunCommand(),"Sun");
        getCommand(new TeleportCommand(),"Teleport");
        getCommand(new TellCommand(),"Tell");
        getCommand(new ThunderCommand(),"Thunder");
    }

    private static void getCommand(CommandExecutor executor, String name) {
        Bukkit.getConsoleSender().sendMessage(Strings.prefix()+"§7  §f-> §7Lade Command §e"+name+"§8...");
        instance.getCommand(name).setExecutor(executor);
    }

    public static void setPrefix(Player player) {
        String Name = player.getName();
        org.bukkit.scoreboard.Scoreboard Scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        if(Scoreboard.getTeam("03Spieler")==null) {
            Scoreboard.registerNewTeam("00000Team");
            Scoreboard.registerNewTeam("01Creator");
            Scoreboard.registerNewTeam("02Premium");
            Scoreboard.registerNewTeam("03Spieler");
            Scoreboard.getTeam("00000Team").setPrefix("§5Team §8● §f");
            Scoreboard.getTeam("01Creator").setPrefix("§5Creator §8● §f");
            Scoreboard.getTeam("02Premium").setPrefix("§dPremium §8● §f");
            Scoreboard.getTeam("03Spieler").setPrefix("§dUser §8● §f");
            Scoreboard.getTeam("00000Team").setCanSeeFriendlyInvisibles(false);
            Scoreboard.getTeam("01Creator").setCanSeeFriendlyInvisibles(false);
            Scoreboard.getTeam("02Premium").setCanSeeFriendlyInvisibles(false);
            Scoreboard.getTeam("03Spieler").setCanSeeFriendlyInvisibles(false);
            Scoreboard.getTeam("00000Team").setOption(org.bukkit.scoreboard.Team.Option.COLLISION_RULE, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
            Scoreboard.getTeam("01Creator").setOption(org.bukkit.scoreboard.Team.Option.COLLISION_RULE, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
            Scoreboard.getTeam("02Premium").setOption(org.bukkit.scoreboard.Team.Option.COLLISION_RULE, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
            Scoreboard.getTeam("03Spieler").setOption(org.bukkit.scoreboard.Team.Option.COLLISION_RULE, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
        }
        for(Player p:Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("zyneon.team")) {
                Scoreboard.getTeam("00000Team").addPlayer(p);
                p.setDisplayName(Scoreboard.getTeam("00000Team").getPrefix() + Name);
            } else if (p.hasPermission("zyneon.creator")) {
                Scoreboard.getTeam("01Creator").addPlayer(p);
                p.setDisplayName(Scoreboard.getTeam("01Creator").getPrefix() + Name);
            } else if (p.hasPermission("zyneon.premium")) {
                Scoreboard.getTeam("02Premium").addPlayer(p);
                p.setDisplayName(Scoreboard.getTeam("02Premium").getPrefix() + Name);
            } else {
                Scoreboard.getTeam("03Spieler").addPlayer(p);
                p.setDisplayName(Scoreboard.getTeam("03Spieler").getPrefix() + Name);
            }
        }
        player.setScoreboard(Scoreboard);
    }

    public static Main getInstance() {
        return instance;
    }
}
