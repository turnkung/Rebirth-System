package me.araidkub.customspawnpoint.Commands;

import me.araidkub.customspawnpoint.CustomSpawnPoint;
import me.araidkub.customspawnpoint.Utils.RebirthMenu;
import me.araidkub.customspawnpoint.Utils.RebirthUtils;
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

public class OpenMenuCommand implements CommandExecutor {

    private CustomSpawnPoint customSpawnPoint;
    private RebirthUtils rebirthUtils;

    public OpenMenuCommand (CustomSpawnPoint customSpawnPoint) {
        this.customSpawnPoint = customSpawnPoint;
    }

    public static Inventory RebirthMenu = Bukkit.createInventory(null, 9, ChatColor.AQUA + "Rebirth Menu");

    private RebirthMenu rebirthMenu;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[]args) {
        Player player = (Player) sender;
        player.openInventory(rebirthMenu.rbmenu);
        return false;
    }
}
