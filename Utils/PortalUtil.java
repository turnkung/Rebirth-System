package me.araidkub.customspawnpoint.Utils;

import me.araidkub.customspawnpoint.CustomSpawnPoint;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PortalUtil implements Listener {

    private CustomSpawnPoint customSpawnPoint;

    private MessageUtil messageUtil;

    public PortalUtil(CustomSpawnPoint customSpawnPoint) {
        this.customSpawnPoint = customSpawnPoint;
    }

    public void checks(Player player) {
        int souls = customSpawnPoint.getSoul().getInt(player.getName() + ".Soul");
        if (souls < 20) {
            player.sendMessage(ChatColor.RED + "จำนวน " + ChatColor.AQUA + "วิญญาณ " + ChatColor.RED + "ไม่เพียงพอ");
        } else {
            take(player, 20);
        }
    }

    public void take(Player player, int i) {
        ItemStack accportal = new ItemStack(Material.EMERALD);
        ItemMeta accportalmeta = accportal.getItemMeta();
        accportalmeta.setDisplayName(ChatColor.DARK_PURPLE + "Access Portal");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "คลิ๊กขวา เพื่อเปิด " + ChatColor.DARK_PURPLE + "Warp portal");
        accportalmeta.setLore(lore);
        accportal.setItemMeta(accportalmeta);
        customSpawnPoint.getSoul().set(player.getName() + ".Soul", customSpawnPoint.getSoul().getInt(player.getName() + ".Soul") - i);
        player.getInventory().addItem(accportal);
        Bukkit.getPlayer(player.getName()).sendMessage(ChatColor.AQUA + "ได้รัไอเท็ม " + ChatColor.DARK_PURPLE + "Access Portal" + ChatColor.AQUA + " แล้ว");
        customSpawnPoint.saveSoul();
    }

    @EventHandler
    public void onClickInv(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();

        if (!player.getInventory().getItemInMainHand().equals(ChatColor.DARK_PURPLE + "Portal Access Buy")) return;
        if (event.getCurrentItem().getItemMeta() == null) return;
        if (event.getCurrentItem().getType() == Material.AIR) return;

        if (item.getType().equals(Material.EMERALD) && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
            if (item.getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Access Portal")) {
                checks(player);
                event.setCancelled(true);
            } else {
                return;
            }
        } else {
            return;
        }
    }
}
