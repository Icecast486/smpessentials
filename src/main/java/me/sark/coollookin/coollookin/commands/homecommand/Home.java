package me.sark.coollookin.coollookin.commands.homecommand;

import me.sark.coollookin.coollookin.CoolLookin;
import me.sark.coollookin.coollookin.MessengerHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class Home implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        Player player;
        String worldName;
        FileConfiguration homeConfig = CoolLookin.homeData.getConfig();

        if (!(commandSender instanceof Player))
        {
            System.out.println("[Home] The console cannot run this command!");
            return true;
        }

        player = ((Player) commandSender).getPlayer();

        if (player == null)
        {
            Bukkit.getLogger().log(Level.SEVERE, "Player using home command is null");
            return false;
        }


        worldName = World.Environment.NORMAL.name();

        if (!homeConfig.contains("homes."+ worldName + "." + player.getUniqueId().toString()))
        {
            MessengerHelper.sendMessageToPlayer
                    (player,"&7[&cHomes&7] &8»&7 You don't have any homes, you can set one by doing &e/sethome&7.");
            return true;
        }

        /* Getting the cords form the figgy */
        double hX,hY,hZ;
        float hPitch = 0, hYaw = 0;

        hX = homeConfig.getDouble("homes."+ worldName + "." + player.getUniqueId().toString() + ".X");
        hY = homeConfig.getDouble("homes."+ worldName + "." + player.getUniqueId().toString() + ".Y");
        hZ = homeConfig.getDouble("homes."+ worldName + "." + player.getUniqueId().toString() + ".Z");

        /* If Pitch and Yaw are stored use them! */
        if (homeConfig.contains("homes."+ worldName + "." + player.getUniqueId().toString() + ".PITCH") &&
            homeConfig.contains("homes."+ worldName + "." + player.getUniqueId().toString() + ".YAW"))
        {
            hPitch = (float) homeConfig.getDouble("homes." + worldName + "." + player.getUniqueId().toString() + ".PITCH");
            hYaw = (float) homeConfig.getDouble("homes." + worldName + "." + player.getUniqueId().toString() + ".YAW");
        }

        /* Sending message and teleporting player */
        MessengerHelper.sendMessageToPlayer
                (player,"&7[&aHomes&7] &8»&7 Teleporting to home!");
        Location newLocation = new Location(Bukkit.getWorld("world"), hX,hY,hZ,hYaw,hPitch);
        player.teleport(newLocation);


        return true;
    }
}
