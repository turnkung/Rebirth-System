package me.araidkub.customspawnpoint.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TEST implements Listener {

    @EventHandler
    public void onPlayerClickss(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.LEFT_CLICK_AIR) {
            ItemStack nc = new ItemStack(Material.DRIED_KELP);
            ItemMeta ncmeta = nc.getItemMeta();
            ncmeta.setDisplayName(ChatColor.AQUA + "Magical " + ChatColor.YELLOW + "Necronomicon");
            ncmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
            ArrayList<String> lore1 = new ArrayList<String>();
            lore1.add(ChatColor.GRAY + "คัมภีร์เวทมนตร์โบราณ  สามารถใช้ชุบชีวิตคนตายได้");
            lore1.add(ChatColor.GRAY + "การใช้ไอเท็มนี้จำเป็นต้องอยู่ในช่องเก็บของ เวลาตาย");
            lore1.add(ChatColor.GRAY + "เมื่อตาย ไอเท็มในตัวจะไม่ตก");
            lore1.add(ChatColor.GRAY + "คลิ๊กขวาที่ไอเท็มจะทำการฟื้นคืนชีพ และจะกลับไปตำแหน่งที่ตายล่าสุด");
            lore1.add(ChatColor.RED + "คำเตือน!!");
            lore1.add(ChatColor.GRAY + "นี่ไม่ใช่ของกินเล่น ห้ามกิน แต่มันก็กินได้นั่นแหละ แต่ถ้ากินแล้วแอดไม่รู้ไม่ชี้นะ");
            ncmeta.setLore(lore1);
            nc.setItemMeta(ncmeta);
            player.getInventory().addItem(nc);
        }
    }
}
