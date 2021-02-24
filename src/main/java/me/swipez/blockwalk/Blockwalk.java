package me.swipez.blockwalk;

import me.swipez.blockwalk.bstats.Metrics;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Blockwalk extends JavaPlugin {
    boolean gamestarted = false;
    HashMap<UUID, Location> storedloc = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new WalkListen(this), this);
        getCommand("blockwalk").setExecutor(new StartCommand(this));
        getCommand("blockwalk").setTabCompleter(new CommandComplete());
        new Metrics(this, 10462);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
