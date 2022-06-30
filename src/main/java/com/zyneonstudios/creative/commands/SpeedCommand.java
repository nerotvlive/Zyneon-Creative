package com.zyneonstudios.creative.commands;

import com.zyneonstudios.api.utils.Strings;
import com.zyneonstudios.creative.Main;
import com.zyneonstudios.creative.utils.CreativeUser;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpeedCommand implements CommandExecutor {

    private void sendSyntax(CommandSender s) {
        if(s instanceof Player p) {
            CreativeUser u = Main.creativeUsers.get(p.getUniqueId());
            u.sendErrorMessage("§4Fehler: §c/speed [0-9/d/default] §7(Spieler)");
        } else {
            s.sendMessage("§4Fehler: §c/speed [0-9/d/default] [Spieler]");
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender s, Command cmd, @NotNull String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Speed")) {
            if(s instanceof Player p) {
                CreativeUser u = Main.creativeUsers.get(p.getUniqueId());
                if(p.hasPermission("zyneon.team.speed")) {
                    if(args.length == 0) {
                        sendSyntax(p);
                    } else if(args.length == 1) {
                        if(isSpeedCompatible(args[0])) {
                            if (p.isFlying()) {
                                String Speed = "0." + args[0];
                                p.setFlySpeed(Float.parseFloat(Speed));
                                u.sendMessage("Du hast deine §eFluggeschwindigkeit§7 auf §e" + args[0] + "§7 gesetzt!");
                            } else {
                                String Speed = "0." + args[0];
                                p.setWalkSpeed(Float.parseFloat(Speed));
                                u.sendMessage("Du hast deine §eLaufgeschwindigkeit§7 auf §e" + args[0] + "§7 gesetzt!");
                            }
                        } else if(args[0].equalsIgnoreCase("d")||args[0].equalsIgnoreCase("default")) {
                            if (p.isFlying()) {
                                String Speed = "0.1";
                                p.setFlySpeed(Float.parseFloat(Speed));
                                u.sendMessage("Du hast deine §eFluggeschwindigkeit§7 auf §eStandard§7 gesetzt!");
                            } else {
                                String Speed = "0.2";
                                p.setWalkSpeed(Float.parseFloat(Speed));
                                u.sendMessage("Du hast deine §eLaufgeschwindigkeit§7 auf §eStandard§7 gesetzt!");
                            }
                        } else {
                            u.sendErrorMessage("§cDas ist keine gültige Zahl!");
                        }
                    } else {
                        if(p.hasPermission("zyneon.team.speed.other")) {
                            if (Bukkit.getPlayer(args[1]) != null) {
                                if (isSpeedCompatible(args[0])) {
                                    Player t = Bukkit.getPlayer(args[1]);
                                    assert t != null;
                                    if (t.isFlying()) {
                                        String Speed = "0."+args[0];
                                        t.setFlySpeed(Float.parseFloat(Speed));
                                        u.sendMessage("Du hast die §eFluggeschwindigkeit§7 von §a" + t.getName() + "§7auf §e" + args[0] + "§7 gesetzt!");
                                    } else {
                                        String Speed = "0."+args[0];
                                        t.setWalkSpeed(Float.parseFloat(Speed));
                                        u.sendMessage("Du hast die §eLaufgeschwindigkeit§7 von §a" + t.getName() + "§7auf §e" + args[0] + "§7 gesetzt!");
                                    }
                                } else if(args[0].equalsIgnoreCase("d")||args[0].equalsIgnoreCase("default")) {
                                    Player t = Bukkit.getPlayer(args[1]);
                                    assert t != null;
                                    if (t.isFlying()) {
                                        String Speed = "0.1";
                                        t.setFlySpeed(Float.parseFloat(Speed));
                                        u.sendMessage("Du hast die §eFluggeschwindigkeit§7 von §a" + t.getName() + "§7auf §eStandard§7 gesetzt!");
                                    } else {
                                        String Speed = "0.2";
                                        t.setWalkSpeed(Float.parseFloat(Speed));
                                        u.sendMessage("Du hast die §eLaufgeschwindigkeit§7 von §a" + t.getName() + "§7auf §eStandard§7 gesetzt!");
                                    }
                                } else {
                                    u.sendErrorMessage("§cDas ist keine gültige Zahl!");
                                }
                            } else {
                                u.sendErrorMessage(Strings.playerNotFound());
                            }
                        } else {
                            u.sendErrorMessage(Strings.noPerms());
                        }
                    }
                } else {
                    u.sendErrorMessage(Strings.noPerms());
                }
            } else {
                if(args.length == 0) {
                    sendSyntax(s);
                } else if(args.length == 1) {
                    sendSyntax(s);
                } else {
                    if(Bukkit.getPlayer(args[1])!=null) {
                        if(isSpeedCompatible(args[0])) {
                            Player t = Bukkit.getPlayer(args[1]);
                            assert t != null;
                            if(t.isFlying()) {
                                String Speed = "0."+args[0];
                                t.setFlySpeed(Float.parseFloat(Speed));
                                s.sendMessage(Strings.prefix()+"Du hast die §eFluggeschwindigkeit§7 von §a"+t.getName()+"§7auf §e"+args[0]+"§7 gesetzt!");
                            } else {
                                String Speed = "0."+args[0];
                                t.setWalkSpeed(Float.parseFloat(Speed));
                                s.sendMessage(Strings.prefix()+"Du hast die §eLaufgeschwindigkeit§7 von §a"+t.getName()+"§7auf §e"+args[0]+"§7 gesetzt!");
                            }
                        } else if(args[0].equalsIgnoreCase("d")||args[0].equalsIgnoreCase("default")) {
                            Player t = Bukkit.getPlayer(args[1]);
                            assert t != null;
                            if (t.isFlying()) {
                                String Speed = "0.1";
                                t.setFlySpeed(Float.parseFloat(Speed));
                                s.sendMessage(Strings.prefix()+"Du hast die §eFluggeschwindigkeit§7 von §a" + t.getName() + "§7auf §eStandard§7 gesetzt!");
                            } else {
                                String Speed = "0.2";
                                t.setWalkSpeed(Float.parseFloat(Speed));
                                s.sendMessage(Strings.prefix()+"Du hast die §eLaufgeschwindigkeit§7 von §a" + t.getName() + "§7auf §eStandard§7 gesetzt!");
                            }
                        } else {
                            s.sendMessage("§cDas ist keine gültige Zahl!");
                        }
                    } else {
                        s.sendMessage(Strings.playerNotFound());
                    }
                }
            }
        }
        return false;
    }

    private boolean isSpeedCompatible(String Check) {
        boolean isPart;
        if (Check == null) {
            isPart = false;
        } else {
            try {
                double d = Double.parseDouble(Check);
                isPart = d < 10;
            } catch (NumberFormatException nfe) {
                isPart = false;
            }
        }
        if(isPart) {
            int i = Integer.parseInt(Check);
            return i >= 0 && i <= 9;
        } else {
            return false;
        }
    }
}