package net.questcraft;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class EventTesting implements Listener {
    HashMap<UUID, Integer> playerKills = new HashMap<>();
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block.getType() == Material.CHEST) {
            block.setType(Material.AIR);
            HashSet<ItemStack> items = this.dropableItems(playerKills.get(event.getPlayer().getUniqueId()));
            for (ItemStack item : items) {
                event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), item);
            }
        }
    }
    public HashSet<ItemStack> dropableItems(Integer playerKills) {
        HashSet set = new HashSet();
        if (playerKills >= 25) {

        } else if (playerKills >= 20) {
            //put stuff in set
        } else if (playerKills >= 15) {
            //put stuff in set
        } else if (playerKills >= 10) {
            //put stuff in set
        } else if (playerKills >= 5) {
            //put stuff in set
        }
        return set;
    }
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity entity = event.getEntity();
        if (damager instanceof Player && entity instanceof Player && ((Player) entity).getHealth() <= 0) {
            Integer damagerKills = playerKills.get(damager.getUniqueId());
            if (damagerKills != null) {
                playerKills.put(damager.getUniqueId(), damagerKills);
            } else {
                playerKills.put(damager.getUniqueId(), 1);
            }
        }
    }
}
