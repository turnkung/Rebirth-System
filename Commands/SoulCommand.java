package me.araidkub.customspawnpoint.Commands;

import me.araidkub.customspawnpoint.CustomSpawnPoint;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SoulCommand implements CommandExecutor {

    private CustomSpawnPoint customSpawnPoint;

    public SoulCommand(CustomSpawnPoint customSpawnPoint) {
        this.customSpawnPoint = customSpawnPoint;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;

        int souls = customSpawnPoint.getSoul().getInt(player.getName() + ".Soul");
        player.sendMessage(ChatColor.GREEN + "จำนวน " + ChatColor.AQUA + "วิญญาณ " + ChatColor.GREEN + "ที่คุณมีตอนนี้" + ChatColor.DARK_GREEN + ChatColor.BOLD + " > " + ChatColor.AQUA + souls);
        return false;
    }
}
