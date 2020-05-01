package me.araidkub.customspawnpoint.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GiveReviveItem implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;
        if (!player.isOp()) {
            player.sendMessage(ChatColor.RED + "You can't use this command.");
            return true;
        } else {
            ItemStack revive = new ItemStack(Material.BLUE_ORCHID);
            ItemMeta reviveMeta = revive.getItemMeta();
            reviveMeta.setDisplayName(ChatColor.AQUA + "Blue Orchid");
            reviveMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
            ArrayList<String> reviveLore = new ArrayList<String>();
            reviveLore.add(ChatColor.GRAY + "ดอกไม้แห่งชีวิต เป็นไอเท็มที่สามารถทำให้ฟื้นคืนชีพโดยไม่เสีย " + ChatColor.AQUA + "วิญญาณ" + ChatColor.GRAY + " ได้");
            reviveLore.add(ChatColor.GRAY + "พกติดตัวไว้ เมื่อตายจะฟื้นคืนชีพทรงจุดที่ตายได้ และไอเท็มจะไม่ดรอป รวมถึง ลดการเสีย " + ChatColor.GOLD + "เลเวล" + ChatColor.GRAY + " ได้อีกด้วย");
            reviveMeta.setLore(reviveLore);
            revive.setItemMeta(reviveMeta);
            ((Player) sender).getInventory().addItem(revive);
        }
        return false;
    }
}
