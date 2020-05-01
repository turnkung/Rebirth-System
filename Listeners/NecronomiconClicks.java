package me.araidkub.customspawnpoint.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class NecronomiconClicks implements Listener {

    @EventHandler
    public void onRightClickNC(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType() == null) return;
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Magical " + ChatColor.YELLOW + "Necronomicon")) {
                if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                    player.chat("/instrb");
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
