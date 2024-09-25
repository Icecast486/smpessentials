package me.sark.coollookin.coollookin.commands.homecommand;

import me.sark.coollookin.coollookin.CoolLookin;
import me.sark.coollookin.coollookin.MessengerHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        Player player;
        String worldName;

        if (!(commandSender instanceof Player))
        {
            System.out.println("[Home] The console cannot run this command!");
            return true;
        }

        player = ((Player) commandSender).getPlayer();

        if (!player.isOp())
        {
            MessengerHelper.sendMessageToPlayer(player,"&7[&cHome&7] &8»&7 Command not implemented yet lol.");
            return true;
        }

        worldName = player.getWorld().getName();

        if (!CoolLookin.homeData.getConfig().contains("homes." + worldName + "." + player.getUniqueId().toString()))
        {
            MessengerHelper.sendMessageToPlayer
                    (player,"&7[&cHome&7] &8»&7 You don't have any homes, you can set one by doing &e/sethome&7.");
            return true;
        }



        double hX,hY,hZ;
        hX = CoolLookin.homeData.getConfig().getDouble("homes." + worldName + "." + player.getUniqueId().toString() + ".X");
        hY = CoolLookin.homeData.getConfig().getDouble("homes." + worldName + "." + player.getUniqueId().toString() + ".Y");
        hZ = CoolLookin.homeData.getConfig().getDouble("homes." + worldName + "." + player.getUniqueId().toString() + ".Z");

        MessengerHelper.sendMessageToPlayer(player, "Home cords :: ( X:" + hX + ", Y:" + hY + ", Z:" + hZ + ")");
        Location newLocation = new Location(Bukkit.getWorld("world"), hX,hY,hZ);
        player.teleport(newLocation);

        return true;
    }
}
