package me.araidkub.customspawnpoint.Utils;

import me.araidkub.customspawnpoint.CustomSpawnPoint;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class RebirthUtils implements Listener {

    private CustomSpawnPoint customSpawnPoint;

    private HashMap<UUID, Long> cooldown = new HashMap<>();

    public RebirthUtils(CustomSpawnPoint customSpawnPoint) {
        this.customSpawnPoint = customSpawnPoint;
    }

    public void checkSouls(Player player) {
        int souls = customSpawnPoint.getSoul().getInt(player.getName() + ".Soul");
        if (souls < 3) {
            player.sendMessage(ChatColor.RED + "จำนวน " + ChatColor.AQUA + "วิญญาณ " + ChatColor.RED + "ไม่เพียงพอ");
        } else {
            takeSoul(player, 3);
        }
    }

    public void countdown(Player player) {
        if (cooldown.containsKey(player.getUniqueId()) && cooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
            long secondsleft = cooldown.get(player.getUniqueId()) - System.currentTimeMillis();
            player.sendMessage(ChatColor.RED + "รอคืนชีพภายใน " + secondsleft/1000 + " วินาที");
        } else {
            player.sendMessage(ChatColor.AQUA + "รอคืนชีพใหม่ ภายใน 60 วินาที");
            cooldown.put(player.getUniqueId(), System.currentTimeMillis() + (60 * 1000));

            new BukkitRunnable() {
                int time = 60;
                @Override
                public void run() {
                    if (time != 0) {
                        time --;
                        if (time == 0) {
                            reSpawn(player);
                            player.sendMessage(ChatColor.AQUA + "ฟื้นคืนชีพแล้ว");
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            this.cancel();
                        }
                    }
                }
            }.runTaskTimer(customSpawnPoint, 0, 20);
        }
    }

    public void takeSoul(Player player, int i) {
        customSpawnPoint.getSoul().set(player.getName() + ".Soul", customSpawnPoint.getSoul().getInt(player.getName() + ".Soul") - i);
        customSpawnPoint.saveSoul();
        reSpawn(player);
        player.sendMessage(ChatColor.RED + "ใช้ " + ChatColor.AQUA + "วิญญาณ " + ChatColor.RED + "จำนวน " + ChatColor.YELLOW + "3 " + ChatColor.RED + "ดวงในการคืนชีพแล้ว");
    }

    public void reSpawn(Player player) {
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

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!event.getInventory().equals(RebirthMenu.rbmenu)) return;
        if (event.getCurrentItem().getType() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "คืนชีพทันที")) {
            checkSouls(player);
            event.setCancelled(true);
            player.closeInventory();
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("รอเวลาคืนชีพ")) {
            countdown(player);
            event.setCancelled(true);
            player.closeInventory();
        }
    }
}
