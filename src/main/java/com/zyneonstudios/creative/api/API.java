package com.zyneonstudios.creative.api;

import com.zyneonstudios.creative.Main;
import com.zyneonstudios.creative.listeners.PlayerCommandListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommandYamlParser;

import java.util.ArrayList;
import java.util.Collection;

public class API {

    public static Collection<String> commands = new ArrayList<>();


    public static void initCommandList() {
        for(Command all: PluginCommandYamlParser.parse(Main.getInstance())) {
            commands.add(all.getName().toLowerCase());
            for(String aliases:all.getAliases()) {
                commands.add(aliases.toLowerCase());
            }
        }
        for(Command all: PluginCommandYamlParser.parse(Bukkit.getPluginManager().getPlugin("FastAsyncWorldEdit"))) {
            commands.add(all.getName().toLowerCase());
            for(String aliases:all.getAliases()) {
                commands.add(aliases.toLowerCase());
            }
        }
        for(Command all: PluginCommandYamlParser.parse(Bukkit.getPluginManager().getPlugin("PlotSquared"))) {
            commands.add(all.getName().toLowerCase());
            for(String aliases:all.getAliases()) {
                commands.add(aliases.toLowerCase());
            }
        }
        commands.add("party");
        commands.add("give");
        commands.add("sit");
        commands.add("crawl");
        commands.add("lay");
        PlayerCommandListener.initBlocked();
    }

}