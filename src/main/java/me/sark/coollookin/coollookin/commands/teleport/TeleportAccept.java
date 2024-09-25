package me.sark.coollookin.coollookin.commands.teleport;

import me.sark.coollookin.coollookin.MessengerHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;



/*
    Class: TeleportAccept

    This class is the "tpa" command which takes in no arguments.
    Usage: /tpa

    First it gets the player that executed the command (which should be a target
    referred to in TeleportRequest). Then it checks whether the executor (target)
    has a pending request in the hashmap defined in TeleportManager. If the target has
    NO key in the hashmap then the target has no requests. Otherwise, the target has a
    pending teleport request from another player.

    Finally, we get the requester and send a message to them saying that they're being teleported
    and teleport them to the target.
 */


public class TeleportAccept implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        Player target, requester;

        /* Is the command executor the console? */
        if ( !(commandSender instanceof Player) )
        {
            System.out.println("[Teleport] The console cannot run this command!");
            return true;
        }

        /* Gets the command sender and stores it into target */
        target = ((Player) commandSender).getPlayer();

        /* Checking for null player */
        if (target == null)
        {
            Bukkit.getLogger().log(Level.SEVERE, "Target was null in when accepting teleport");
            return false;
        }

        /* Checking whether args are valid */
        if (args.length != 0)
        {
            MessengerHelper.sendMessageToPlayer(target, "&7[&cTeleport&7] &8»&7 Usage is /tpa");
            return true;
        }

        /* If the player doesn't have a request deny the command execution */
        if (!TeleportManager.hasRequest(target))
        {
            MessengerHelper.sendMessageToPlayer(target,"&7[&cTeleport&7] &8»&7 You don't have any requests yet.");
            return true;
        }

        /* Gets the player that wants to teleport to them */
        requester = TeleportManager.getRequester(target);

        /* Sending the messages */
        MessengerHelper.sendMessageToPlayer(target,"&7[&aTeleport&7] &8»&7 Teleporting to you...");
        MessengerHelper.sendMessageToPlayer(requester, "&7[&aTeleport&7] &8»&7 Teleporting...");


        /* Teleporting then removing the request */
        requester.teleport(target);
        TeleportManager.removeRequest(target);

        return true;
    }
}
