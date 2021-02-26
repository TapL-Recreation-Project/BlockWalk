package me.swipez.blockwalk;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class WalkListen implements Listener {
    Blockwalk plugin;

    public WalkListen(Blockwalk plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        if (plugin.gamestarted){
            Player p = e.getPlayer();
            Block b = p.getWorld().getBlockAt(p.getLocation());
            if (plugin.storedloc.get(p.getUniqueId()).getX() != b.getX() || plugin.storedloc.get(p.getUniqueId()).getY() != b.getY() || plugin.storedloc.get(p.getUniqueId()).getZ() != b.getZ()){
                Block underblock = p.getWorld().getBlockAt(p.getLocation().subtract(0,1,0));
                if (underblock.getType() != Material.AIR && underblock.isLiquid() == false && underblock.getType() != Material.FROSTED_ICE){
                    ItemStack underblockitem = new ItemStack(underblock.getType(), 1);
                    if (underblock.getType() != Material.CAVE_AIR && underblock.getType() != Material.VOID_AIR){
                        if (p.getInventory().firstEmpty() == -1){
                            Location loc = p.getLocation();
                            p.getWorld().dropItemNaturally(loc, underblockitem);
                        }
                        else {
                            p.getInventory().addItem(underblockitem);
                        }
                    }
                }
            }
            plugin.storedloc.put(p.getUniqueId(), b.getLocation());
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if (plugin.gamestarted){
            Player p = e.getPlayer();
            if (!plugin.storedloc.containsKey(p.getUniqueId())){
                plugin.storedloc.put(p.getUniqueId(), p.getWorld().getBlockAt(p.getLocation()).getLocation());
            }
        }

    }
}
