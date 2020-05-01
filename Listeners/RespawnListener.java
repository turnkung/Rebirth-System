package me.araidkub.customspawnpoint.Listeners;

import me.araidkub.customspawnpoint.CustomSpawnPoint;
import me.araidkub.customspawnpoint.Utils.MessageUtil;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.io.File;

public class RespawnListener implements Listener {

    private CustomSpawnPoint customSpawnPoint;

    private MessageUtil messageUtil;

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        File file = new File("plugins//CustomSpawnPoint//respawnpoint.yml");
        if (!file.exists()) {
            player.sendMessage(ChatColor.RED + "File doesn't exists.");
            return;
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

        event.setRespawnLocation(location);

        String message =  messageUtil.getMessage().getString("message");

        player.sendTitle( ChatColor.translateAlternateColorCodes('&', message), "", 80, 5, 60);
    }
}
