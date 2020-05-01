package me.araidkub.customspawnpoint.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SetSpawnPoint implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;

        if (!player.isOp()) {
            player.sendMessage(ChatColor.RED + "You can't use this command.");
            return true;
        }

        File folder = new File("plugins//CustomSpawnPoint");
        File file = new File("plugins//CustomSpawnPoint//spawnpoint.yml");

        if (!folder.exists()) {
            folder.mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                player.sendMessage(ChatColor.RED + "Folder Created.");
            }
        }

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        Location location = player.getLocation();

        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        float yaw = location.getYaw();
        float pitch = location.getPitch();
        String worldname = location.getWorld().getName();

        cfg.set("x", x);
        cfg.set("y", y);
        cfg.set("z", z);
        cfg.set("yaw", yaw);
        cfg.set("pitch", pitch);
        cfg.set("world", worldname);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.sendMessage(ChatColor.GREEN + "Spawn point set.");

        return false;
    }
}
