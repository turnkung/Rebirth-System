package me.araidkub.customspawnpoint.Commands;

import me.araidkub.customspawnpoint.Listeners.Necronomicon;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        Player player = (Player) sender;
        if (Necronomicon.back.containsKey(player)) {
            for (int i = 0; i < player.getInventory().getSize(); i++) {
                ItemStack inventoryItem = player.getInventory().getItem(i);
                ItemStack rbitem = new ItemStack(Material.DRIED_KELP);
                ItemMeta rbitemmeta = rbitem.getItemMeta();
                rbitemmeta.setDisplayName(ChatColor.AQUA + "Magical " + ChatColor.YELLOW + "Necronomicon");
                rbitem.setItemMeta(rbitemmeta);
                if (inventoryItem != null && inventoryItem.getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Magical " + ChatColor.YELLOW + "Necronomicon")) {
                    inventoryItem.setAmount(inventoryItem.getAmount() - 1);
                    if (inventoryItem.getAmount() < 1) {
                        inventoryItem.setType(Material.AIR);
                    }
                    player.getInventory().setItem(i, inventoryItem);
                    player.teleport(Necronomicon.back.get(player.getName()));
                    Necronomicon.back.remove(player.getName());
                    break;
                }
                return false;
            }
        } else {
            player.sendMessage(ChatColor.RED + "คุณยังไม่ตายหนิ!!");
        }
        return false;
    }
}
