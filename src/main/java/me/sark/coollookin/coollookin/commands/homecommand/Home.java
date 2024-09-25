package me.sark.coollookin.coollookin.commands.homecommand;

import me.sark.coollookin.coollookin.CoolLookin;
import me.sark.coollookin.coollookin.MessengerHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

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

        if (player == null)
        {
            Bukkit.getLogger().log(Level.SEVERE, "Player using home command is null");
            return false;
        }

        worldName = World.Environment.NORMAL.name();

        if (!CoolLookin.homeData.getConfig().contains("homes."+ worldName + "." + player.getUniqueId().toString()))
        {
            MessengerHelper.sendMessageToPlayer
                    (player,"&7[&cHomes&7] &8»&7 You don't have any homes, you can set one by doing &e/sethome&7.");
            return true;
        }

        /* Getting the cords form the figgy */
        double hX,hY,hZ;
        hX = CoolLookin.homeData.getConfig().getDouble("homes."+ worldName + "." + player.getUniqueId().toString() + ".X");
        hY = CoolLookin.homeData.getConfig().getDouble("homes."+ worldName + "." + player.getUniqueId().toString() + ".Y");
        hZ = CoolLookin.homeData.getConfig().getDouble("homes."+ worldName + "." + player.getUniqueId().toString() + ".Z");

        /* Sending message and teleporting player */
        MessengerHelper.sendMessageToPlayer
                (player,"&7[&aHomes&7] &8»&7 Teleporting to home!");
        Location newLocation = new Location(Bukkit.getWorld("world"), hX,hY,hZ);
        player.teleport(newLocation);


        return true;
    }
}
