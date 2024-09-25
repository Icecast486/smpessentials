package me.sark.coollookin.coollookin.commands.teleport;

import me.sark.coollookin.coollookin.MessengerHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;



/*
    Class: TeleportDeny

    This class is the "tpd" command which is really simple and takes
    in no arguments. Usage: /tpd

    First it checks whether the sender (target) has a requests if target has no requests,
    exit the function because there are no requests to deny. Otherwise, remove the request
    from the hashmap in TeleportManager.

 */



public class TeleportDeny implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        Player target;

        if ( !(commandSender instanceof Player) )
        {
            System.out.println("[Teleport] The console cannot run this command!");
            return true;
        }

        /* Gets the person that sent the command */
        target = ((Player) commandSender).getPlayer();

        if (target == null)
        {
            Bukkit.getLogger().log(Level.SEVERE, "Target was null in when denying teleport");
            return false;
        }

        /* Checking whether args are valid */
        if (args.length != 0)
        {
            MessengerHelper.sendMessageToPlayer(target, "&7[&cTeleport&7] &8»&7 Usage is /tpd");
            return true;
        }


        if (!TeleportManager.hasRequest(target))
        {
            MessengerHelper.sendMessageToPlayer(target,"&7[&cTeleport&7] &8»&7 You don't have any requests!");
            return true;
        }

        TeleportManager.removeRequest(target);

        return false;
    }
}
