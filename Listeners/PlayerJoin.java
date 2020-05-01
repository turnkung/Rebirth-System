package me.araidkub.customspawnpoint.Listeners;

import me.araidkub.customspawnpoint.CustomSpawnPoint;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class PlayerJoin implements Listener {

    private CustomSpawnPoint customSpawnPoint;

    public PlayerJoin(CustomSpawnPoint customSpawnPoint) {
        this.customSpawnPoint = customSpawnPoint;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!event.getPlayer().hasPlayedBefore()) {
            ItemStack pickaxe = new ItemStack(Material.STONE_PICKAXE);
            ItemStack axe = new ItemStack(Material.STONE_AXE);
            ItemStack hoe = new ItemStack(Material.STONE_HOE);
            ItemStack shovel = new ItemStack(Material.STONE_SHOVEL);
            ItemStack food = new ItemStack(Material.PUMPKIN_PIE, 64);
            player.getInventory().addItem(pickaxe);
            player.getInventory().addItem(axe);
            player.getInventory().addItem(hoe);
            player.getInventory().addItem(shovel);
            player.getInventory().addItem(food);

            File file = new File("plugins//CustomSpawnPoint//spawnpoint.yml");
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

            player.teleport(location);
        }

        if (!customSpawnPoint.getSoul().contains(player.getName())) {
            customSpawnPoint.getSoul().set(player.getName() + ".Soul", 24);
            customSpawnPoint.saveSoul();
        }
    }
}
