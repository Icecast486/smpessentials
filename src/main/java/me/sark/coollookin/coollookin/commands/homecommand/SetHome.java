package me.sark.coollookin.coollookin.commands.homecommand;

import me.sark.coollookin.coollookin.CoolLookin;
import me.sark.coollookin.coollookin.MessengerHelper;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class SetHome implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        Player player;

        if (!(commandSender instanceof Player))
        {
            System.out.println("[Homes] The console cannot run this command!");
            return true;
        }

        player = ((Player) commandSender).getPlayer();

        if (player == null)
        {
            Bukkit.getLogger().log(Level.SEVERE, "Player was null while setting a home");
            return false;
        }

        /* Making sure the player only sets homes in the over world */
        if (!player.getWorld().getEnvironment().equals(World.Environment.NORMAL))
        {
            MessengerHelper.sendMessageToPlayer(player, "&7[&cHomes&7] &8»&7 You can only set homes in the overworld.");
            return true;
        }

        /* Added so I can implement multiple dimension homes */
        FileConfiguration homeConfig = CoolLookin.homeData.getConfig();
        String worldName = World.Environment.NORMAL.name();

        /* seeing if player not in the config file, if they're not add him */
        if (!homeConfig.contains("homes."+ worldName + "." + player.getUniqueId().toString()))
        {
            /* If player doesn't have XYZ cords, set it */
            homeConfig.set("homes." + worldName + "." + player.getUniqueId().toString() + ".X", player.getLocation().getX());
            homeConfig.set("homes." + worldName + "." + player.getUniqueId().toString() + ".Y", player.getLocation().getY());
            homeConfig.set("homes." + worldName + "." + player.getUniqueId().toString() + ".Z", player.getLocation().getZ());


            /* If player doesn't have Pitch or Yaw stored, set it */
            homeConfig.set("homes." + worldName + "." + player.getUniqueId().toString() + ".PITCH", player.getLocation().getPitch());
            homeConfig.set("homes." + worldName + "." + player.getUniqueId().toString() + ".YAW", player.getLocation().getYaw());

            MessengerHelper.sendMessageToPlayer(player, "&7[&aHomes&7] &8»&7 Added your first home at current location!");
            CoolLookin.homeData.saveConfig();

            return true;
        }

        /* Player is in the config past here */
        if (args.length != 1)
        {
            MessengerHelper.sendMessageToPlayer(player, "&7[&aHomes&7] &8»&7 Looks like you already have a home, do &e/sethome override&7 to replace it.");
            return true;
        }

        /* TODO add the check whether the user has X,Y,Z or Pitch and Yaw Stored */
        if (args[0].equals("override"))
        {
            /* Storing X, Y, Z */
            homeConfig.set("homes."+ worldName + "." + player.getUniqueId().toString() + ".X", player.getLocation().getX());
            homeConfig.set("homes."+ worldName + "." + player.getUniqueId().toString() + ".Y", player.getLocation().getY());
            homeConfig.set("homes."+ worldName + "." + player.getUniqueId().toString() + ".Z", player.getLocation().getZ());

            homeConfig.set("homes."+ worldName + "." + player.getUniqueId().toString() + ".PITCH", player.getLocation().getPitch());
            homeConfig.set("homes."+ worldName + "." + player.getUniqueId().toString() + ".YAW", player.getLocation().getYaw());

            MessengerHelper.sendMessageToPlayer(player, "&7[&aHomes&7] &8»&7 Overwrote your old home.");
            CoolLookin.homeData.saveConfig();
            return true;
        }

        MessengerHelper.sendMessageToPlayer(player, "&7[&cHomes&7] &8»&7 Usage is &e/sethome override.");

        return true;

    }
}
