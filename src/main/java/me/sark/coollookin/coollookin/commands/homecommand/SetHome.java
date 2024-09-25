package me.sark.coollookin.coollookin.commands.homecommand;

import me.sark.coollookin.coollookin.CoolLookin;
import me.sark.coollookin.coollookin.MessengerHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHome implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        Player player;
        String worldName;

        if (!(commandSender instanceof Player))
        {
            System.out.println("[Set-Home] The console cannot run this command!");
            return true;
        }

        player = ((Player) commandSender).getPlayer();

        if (!player.isOp())
        {
            MessengerHelper.sendMessageToPlayer(player, "&7[&cHomes&7] &8»&7 Command not implemented yet lol.");
            return true;
        }

        /* TODO all dimensions say world is "world" */
        worldName = Bukkit.getWorld("world").getName();


        MessengerHelper.sendMessageToPlayer(player, worldName);


        if (!worldName.equals("world"))
        {
            MessengerHelper.sendMessageToPlayer(player, "&7[&cHomes&7] &8»&7 You can only place homes in the overworld!");
            return true;
        }

        /* seeing if player not in the config file, if they're not add him */
        if (!CoolLookin.homeData.getConfig().contains("homes." + worldName + "." + player.getUniqueId().toString()))
        {
            /* Storing X, Y, Z */
            CoolLookin.homeData.getConfig().set
                    ("homes." + worldName + "." + player.getUniqueId().toString() + ".X", player.getLocation().getX());
            CoolLookin.homeData.getConfig().set
                    ("homes." + worldName + "." + player.getUniqueId().toString() + ".Y", player.getLocation().getY());
            CoolLookin.homeData.getConfig().set
                    ("homes." + worldName + "." + player.getUniqueId().toString() + ".Z", player.getLocation().getZ());

            MessengerHelper.sendMessageToPlayer(player, "&7[&aHomes&7] &8»&7 Added your first home at current location!");
            CoolLookin.homeData.saveConfig();

            return true;
        }

        if (args.length != 1)
        {
            MessengerHelper.sendMessageToPlayer
                    (player, "&7[&aSet&7-&aHome&7] &8»&7 Looks like you already have a home, do &e/sethome override&7 to replace it.");
            return true;
        }

        /* Player is in the config past here */
        if (args[0].equals("override"))
        {
            /* Storing X, Y, Z */
            CoolLookin.homeData.getConfig().set
                    ("homes." + worldName + "." + player.getUniqueId().toString() + ".X", player.getLocation().getX());
            CoolLookin.homeData.getConfig().set
                    ("homes." + worldName + "." + player.getUniqueId().toString() + ".Y", player.getLocation().getY());
            CoolLookin.homeData.getConfig().set
                    ("homes." + worldName + "." + player.getUniqueId().toString() + ".Z", player.getLocation().getZ());

            MessengerHelper.sendMessageToPlayer(player, "&7[&aHomes&7] &8»&7 Overwrote your old home.");
            CoolLookin.homeData.saveConfig();

            return true;
        }
        else
        {
            MessengerHelper.sendMessageToPlayer(player, "&7[&cHomes&7] &8»&7 Usage is &e/sethome override.");
            return true;
        }

    }
}
