package me.araidkub.customspawnpoint.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class RebirthMenu implements Listener {

    public static Inventory rbmenu = Bukkit.createInventory(null, 9, ChatColor.AQUA + "Rebirth Menu");

    private RebirthUtils rebirthUtils;

    public RebirthMenu() {
        ItemStack rebirth = new ItemStack(Material.BLUE_ORCHID);
        ItemMeta rebirthmeta = rebirth.getItemMeta();
        rebirthmeta.setDisplayName(ChatColor.AQUA + "คืนชีพทันที");
        ArrayList<String> rebithlore = new ArrayList<>();
        rebithlore.add(ChatColor.GRAY + "การคืนชีพทันทีต้องใช้" + ChatColor.AQUA + " วิญญาณ " + ChatColor.GRAY + "จำนวน" + ChatColor.YELLOW + " 3 " + ChatColor.GRAY + "ดวง");
        rebithlore.add(ChatColor.DARK_GRAY + "( " + ChatColor.GRAY + "คลิ๊กเพื่อทำการคืนชีพทันที" + ChatColor.DARK_GRAY + " )");
        rebirthmeta.setLore(rebithlore);
        rebirth.setItemMeta(rebirthmeta);
        rbmenu.setItem(2, rebirth);

        ItemStack rbtimer = new ItemStack((Material.SUNFLOWER));
        ItemMeta rbtimermeta = rbtimer.getItemMeta();
        rbtimermeta.setDisplayName(ChatColor.YELLOW + "รอเวลาคืนชีพ");
        ArrayList<String> rbtimerlore = new ArrayList<>();
        rbtimerlore.add(ChatColor.GRAY + "รอเวลาคืนชีพ โดยรอเวลา" + ChatColor.YELLOW + " 60 " + ChatColor.GRAY + "วินาที");
        rbtimerlore.add(ChatColor.DARK_GRAY + "( " + ChatColor.GRAY + "คลิ๊กเพื่อทำการรอเวลาคืนชีพ" + ChatColor.DARK_GRAY + " )");
        rbtimermeta.setLore(rbtimerlore);
        rbtimer.setItemMeta(rbtimermeta);
        rbmenu.setItem(6, rbtimer);
    }
}
