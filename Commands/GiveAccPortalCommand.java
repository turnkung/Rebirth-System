package me.araidkub.customspawnpoint.Commands;

import me.araidkub.customspawnpoint.Utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GiveAccPortalCommand implements CommandExecutor {

    private MessageUtil messageUtil;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "You can't use this command.");
            return true;
        }

        ItemStack accportal = new ItemStack(Material.EMERALD);
        ItemMeta accportalmeta = accportal.getItemMeta();
        accportalmeta.setDisplayName(ChatColor.DARK_PURPLE + "Access Portal");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "คลิ๊กขวา เพื่อเปิด " + ChatColor.DARK_PURPLE + "Warp portal");
        accportalmeta.setLore(lore);
        accportal.setItemMeta(accportalmeta);

        if (args[0].equalsIgnoreCase("give")) {
            Bukkit.getPlayer(args[1]).getInventory().addItem(accportal);
            sender.sendMessage(ChatColor.AQUA + "มอบไอเท็มแล้ว");
            Bukkit.getPlayer(args[1]).sendMessage(ChatColor.AQUA + "ได้รับไอเท็ม " + ChatColor.DARK_PURPLE + "Access Portal" + ChatColor.AQUA + " แล้ว");
        } else {
            sender.sendMessage(ChatColor.RED + "Incorrect argument.");
        }

        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Too few argument.");
        } else {
            sender.sendMessage(ChatColor.RED + "Incorrect argument.");
            return true;
        }
        return false;
    }
}
