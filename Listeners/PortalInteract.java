package me.araidkub.customspawnpoint.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class PortalInteract implements Listener {

    private HashMap<UUID, Long> cooldown = new HashMap<>();

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (player.getInventory().getItemInMainHand().getItemMeta() == null) {
            return;
        }
        if (item.getType().equals(Material.EMERALD) && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (item.getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Access Portal")) {
                    if (cooldown.containsKey(player.getUniqueId()) && cooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                        long secondsleft = cooldown.get(player.getUniqueId()) - System.currentTimeMillis();
                        player.sendMessage(ChatColor.RED + "กรุณารออีก " + secondsleft/1000 + " วินาที" + ChatColor.RED + " เพื่อที่จะใช้งาน " + ChatColor.DARK_PURPLE + "Access Portal " + ChatColor.RED + "ได้อีกครั้ง");
                    } else {
                        player.chat("/warpsss");
                        cooldown.put(player.getUniqueId(), System.currentTimeMillis() + (60 * 1000));
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }
}
