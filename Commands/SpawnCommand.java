package me.araidkub.customspawnpoint.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;

        if (!(sender instanceof Player)) {
            System.out.print(ChatColor.RED + "Only player can use this command.");
            return true;
        }

        File file = new File("plugins//CustomSpawnPoint//spawnpoint.yml");
        if (!file.exists()) {
            player.sendMessage(ChatColor.RED + "File doesn't exists.");
            return true;
        }

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        Location location = player.getLocation();

        double x = cfg.getDouble("x");
        double y = cfg.getDouble("y");
        double z = cfg.getDouble("z");
        float yaw = (float) cfg.getDouble("yaw");
        float pitch = (float) cfg.getDouble("pitch");
        String worldname = cfg.getString("world");

        World world = Bukkit.getWorld(worldname);

        location.setX(x);
        location.setY(y);
        location.setZ(z);
        location.setYaw(yaw);
        location.setPitch(pitch);
        location.setWorld(world);

        player.teleport(location);

        return false;
    }
}
