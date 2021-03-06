package me.araidkub.customspawnpoint;

import me.araidkub.customspawnpoint.Commands.*;
import me.araidkub.customspawnpoint.Listeners.*;
import me.araidkub.customspawnpoint.Utils.MessageUtil;
import me.araidkub.customspawnpoint.Utils.PortalUtil;
import me.araidkub.customspawnpoint.Utils.RebirthMenu;
import me.araidkub.customspawnpoint.Utils.RebirthUtils;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public class CustomSpawnPoint extends JavaPlugin {

    private File soulfile;
    private FileConfiguration soul;

    private RebirthUtils rebirthUtils;
    public MessageUtil messageUtil;

    private static CustomSpawnPoint instance;

    public CustomSpawnPoint() {
        instance = this;
    }

    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();
        registerSoulFiles();
        registerMessages();
        powerrecipe();
        ncsrecipe();

        rebirthUtils = new RebirthUtils(this);
        messageUtil = new MessageUtil(this);
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        getCommand("setspawnpoint").setExecutor(new SetSpawnPoint());
        getCommand("setrespawnpoint").setExecutor(new SetRespawnPoint());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("soul").setExecutor(new SoulCommand(this));
        getCommand("rbitem").setExecutor(new GiveReviveItem());
        getCommand("rbmenu").setExecutor(new OpenMenuCommand(this));
        getCommand("rbsoul").setExecutor(new GiveSoulCommand(this));
        getCommand("accportal").setExecutor(new GiveAccPortalCommand());
        getCommand("accbuyportal").setExecutor(new BuyMenuCommand());
        //getCommand("instrb").setExecutor(new BackCommand());
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new RespawnListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new RebirthUtils(this), this);
        getServer().getPluginManager().registerEvents(new VestalClick(), this);
        getServer().getPluginManager().registerEvents(new PortalUtil(this), this);
        getServer().getPluginManager().registerEvents(new PortalInteract(), this);
        getServer().getPluginManager().registerEvents(new RebirthMenu(), this);
        //getServer().getPluginManager().registerEvents(new Necronomicon(), this);
        //getServer().getPluginManager().registerEvents(new NecronomiconClicks(), this);
        //getServer().getPluginManager().registerEvents(new TEST(), this);
    }

    public void registerSoulFiles() {
        soulfile = new File(getDataFolder(), "soul.yml");
        soul = YamlConfiguration.loadConfiguration(soulfile);
        saveSoul();
    }

    public void registerMessages() {
        messageUtil.registerMessageFiles();
    }

    public static CustomSpawnPoint getPlugin() {
        return instance;
    }

    public void saveSoul() {
        try {
            soul.save(soulfile);
        } catch (Exception e) {

        }
    }

    public File getSoulfile() {
        return soulfile;
    }

    public FileConfiguration getSoul() {
        return soul;
    }

    public static CustomSpawnPoint instance() {
        return instance;
    }


    private void powerrecipe() {
        ItemStack miracle = new ItemStack(Material.HEART_OF_THE_SEA);
        ItemMeta miraclemeta = miracle.getItemMeta();
        miraclemeta.setDisplayName(ChatColor.GOLD + "Miracle");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "ส่วนประกอบในการทำ" + ChatColor.YELLOW + ChatColor.YELLOW + " NecronomiconClicks");
        miraclemeta.addEnchant(Enchantment.PROTECTION_FIRE, 1, true);
        miraclemeta.setLore(lore);
        miracle.setItemMeta(miraclemeta);

        NamespacedKey miraclekey = new NamespacedKey(this, "miracle_key");

        ShapedRecipe mrecipe = new ShapedRecipe(miraclekey, miracle);
        mrecipe.shape(
                "###",
                "###",
                "###");
        mrecipe.setIngredient('#', Material.SCUTE);
        Bukkit.getServer().addRecipe(mrecipe);
    }

    private void ncsrecipe() {
        ItemStack necronomicon = new ItemStack(Material.DRIED_KELP);
        ItemMeta necronomiconmeta = necronomicon.getItemMeta();
        necronomiconmeta.setDisplayName(ChatColor.GRAY + "Unmagical NecronomiconClicks");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "คัมภีร์เวทมนตร์โบราณ สามารถใช้ชุบชีวิตคนตายได้");
        lore.add(ChatColor.GRAY + "แต่ยังไม่ได้ลงเวทมนตร์ ผู้ที่มีอาชีพ " + ChatColor.YELLOW + "Vestal");
        lore.add(ChatColor.GRAY + "สามารถลงเวทมนตร์ได้โดยการ" + ChatColor.AQUA + " คลิ๊กขวา");
        necronomiconmeta.setLore(lore);
        necronomicon.setItemMeta(necronomiconmeta);

        ItemStack miracle = new ItemStack(Material.HEART_OF_THE_SEA);
        ItemMeta miraclemeta = miracle.getItemMeta();
        miraclemeta.setDisplayName(ChatColor.GOLD + "Miracle");
        ArrayList<String> mlore = new ArrayList<String>();
        mlore.add(ChatColor.GRAY + "ส่วนประกอบในการทำ" + ChatColor.YELLOW + ChatColor.YELLOW + " NecronomiconClicks");
        miraclemeta.addEnchant(Enchantment.PROTECTION_FIRE, 1, true);
        miraclemeta.setLore(mlore);
        miracle.setItemMeta(miraclemeta);

        NamespacedKey necronomiconkey = new NamespacedKey(this, "necronomicon_key");

        ShapedRecipe ncrecipe = new ShapedRecipe(necronomiconkey, necronomicon);
        ncrecipe.shape(
                "-$-",
                "#@#",
                "-*-");
        ncrecipe.setIngredient('@', new MaterialData(Material.HEART_OF_THE_SEA, miracle.getData().getData()));
        ncrecipe.setIngredient('#', Material.SCUTE);
        ncrecipe.setIngredient('$', Material.GOLD_BLOCK);
        ncrecipe.setIngredient('*', Material.DIAMOND);
        Bukkit.getServer().addRecipe(ncrecipe);
    }
}
