package me.araidkub.customspawnpoint.Commands;

import me.araidkub.customspawnpoint.CustomSpawnPoint;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BuyMenuCommand implements CommandExecutor {

    private CustomSpawnPoint customSpawnPoint;

    public BuyMenuCommand (CustomSpawnPoint customSpawnPoint) {
        this.customSpawnPoint = customSpawnPoint;
    }

    public static Inventory BuyMenu = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Portal Access Buy");

    public BuyMenuCommand() {

        ItemStack buyp = new ItemStack(Material.EMERALD);
        ItemMeta buypneta = buyp.getItemMeta();
        buypneta.setDisplayName(ChatColor.DARK_PURPLE + "Access Portal");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "คลิ๊กเพื่อซื้อ " + ChatColor.DARK_PURPLE + "Access Portal");
        lore.add(ChatColor.GRAY + "ในราคา" + ChatColor.YELLOW + " 20or");
        lore.add(ChatColor.GRAY + "Click!");
        buypneta.setLore(lore);
        buyp.setItemMeta(buypneta);

        BuyMenu.setItem(4, buyp);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;
        player.openInventory(BuyMenu);
        return false;
    }
}
