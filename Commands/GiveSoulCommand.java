package me.araidkub.customspawnpoint.Commands;

import me.araidkub.customspawnpoint.CustomSpawnPoint;
import me.araidkub.customspawnpoint.Utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GiveSoulCommand implements CommandExecutor {

    private CustomSpawnPoint customSpawnPoint;

    private MessageUtil messageUtil;

    public GiveSoulCommand(CustomSpawnPoint customSpawnPoint) {
        this.customSpawnPoint = customSpawnPoint;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "You can't use this command.");
            return true;
        }
        if (args[0].equalsIgnoreCase("add")) {
            int amount = 0;
            try {
                amount = Integer.parseInt(args[2]);
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "Enter number only.");
                return true;
            }
            customSpawnPoint.getSoul().set(args[1] + ".Soul", customSpawnPoint.getSoul().getInt(args[1] + ".Soul") + amount);
            customSpawnPoint.saveSoul();
            sender.sendMessage(ChatColor.AQUA + "วิญญาณ" + ChatColor.GREEN + " จำนวน " + ChatColor.YELLOW + amount + ChatColor.GREEN + " ดวงได้ถูกเพิ่มให้กับ " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " แล้ว");
            Bukkit.getPlayer(args[1]).sendMessage(ChatColor.GREEN + "ได้รับ " + ChatColor.AQUA + "วิญญาณ " + ChatColor.GREEN + "จำนวน " + ChatColor.YELLOW + amount + ChatColor.GREEN + " ดวงแล้ว");
        } else if (args[0].equalsIgnoreCase("remove")) {
            int amount = 0;
            try {
                amount = Integer.parseInt(args[2]);
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "Enter number only.");
                return true;
            }
            customSpawnPoint.getSoul().set(args[1] + ".Soul", customSpawnPoint.getSoul().getInt(args[1] + ".Soul") - amount);
            customSpawnPoint.saveSoul();
            sender.sendMessage(ChatColor.AQUA + "วิญญาณ" + ChatColor.GREEN + " จำนวน " + ChatColor.YELLOW + amount + ChatColor.GREEN + " ดวงได้ถูกนำออกจาก " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " แล้ว");
        } else if (args[0].equalsIgnoreCase("set")) {
            int amount = 0;
            try {
                amount = Integer.parseInt(args[2]);
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "Enter number only.");
                return true;
            }
            customSpawnPoint.getSoul().set(args[1] + ".Soul", customSpawnPoint.getSoul().getInt(args[1] + ".Soul", amount));
            customSpawnPoint.saveSoul();
            sender.sendMessage(ChatColor.GREEN + "ทำการกำหนด" + ChatColor.AQUA + " วิญญาณ" + ChatColor.GREEN + " ของ" + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " ให้เป็น " + ChatColor.YELLOW + amount + ChatColor.GREEN + " ดวงแล้ว");
        } else if (args.length != 3) {
            sender.sendMessage(ChatColor.RED + "Too few argument.");
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "Incorrect argument.");
        }
        return false;
    }
}
