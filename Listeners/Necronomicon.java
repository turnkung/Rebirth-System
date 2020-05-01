package me.araidkub.customspawnpoint.Listeners;

import me.araidkub.customspawnpoint.CustomSpawnPoint;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.UUID;

public class Necronomicon implements Listener {

    private CustomSpawnPoint customSpawnPoint;

    public static HashMap<String, Location> back = new HashMap<>();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            ItemStack inventoryItem = player.getInventory().getItem(i);
            ItemStack rbitem = new ItemStack(Material.DRIED_KELP);
            ItemMeta rbitemmeta = rbitem.getItemMeta();
            rbitemmeta.setDisplayName(ChatColor.AQUA + "Magical " + ChatColor.YELLOW + "Necronomicon");
            rbitem.setItemMeta(rbitemmeta);
            if (inventoryItem != null && inventoryItem.getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Magical " + ChatColor.YELLOW + "Necronomicon")) {
                event.setKeepInventory(true);
                event.setKeepLevel(true);
                back.put(player.getName(), player.getLocation());
                break;
            }
        }
    }
}
