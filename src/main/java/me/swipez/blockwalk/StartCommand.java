package me.swipez.blockwalk;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {
    Blockwalk plugin;

    public StartCommand(Blockwalk plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (sender.hasPermission("blockwalk.toggle")){
                if (args.length == 1){
                    if (args[0].equals("start")){
                        plugin.gamestarted = true;
                        Bukkit.broadcastMessage(ChatColor.GREEN+"Block Walk challenge has started!");
                        for (Player others : Bukkit.getOnlinePlayers()){
                            plugin.storedloc.put(others.getUniqueId(), others.getWorld().getBlockAt(others.getLocation()).getLocation());
                        }
                    }
                    else if (args[0].equals("stop")){
                        plugin.gamestarted = false;
                        Bukkit.broadcastMessage(ChatColor.GREEN+"Block Walk challenge has ended!");
                        for (Player others : Bukkit.getOnlinePlayers()){
                            plugin.storedloc.remove(others);
                        }
                    }
                    else {
                        p.sendMessage(ChatColor.RED+"/blockwalk <start/stop>");
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED+"/blockwalk <start/stop>");
                }
            }
        }
        else{
            sender.sendMessage(ChatColor.RED+"This command is for players only!");
        }

        return true;
    }
}
