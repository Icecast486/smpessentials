package me.sark.coollookin.coollookin.commands.teleport;

import me.sark.coollookin.coollookin.MessengerHelper;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;



/*
    Class: Teleport Request

    This Class is the "tpr" command. It takes in one argument which is the target
    the player wants to teleport to. Usage: /tpr <target>

    First it checks whether the target exists or if the target is the player that called
    the command. Then it adds the target into a requests hash map defined in TeleportManager.
    Finally, in the end, it sends a message to both the executor and target confirming the request has
    been sent.
 */



public class TeleportRequest implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        /* The executor of the command; the target the requester wants to teleport to */
        Player requester, target;

        /* Is the command executor the console? */
        if (!(commandSender instanceof Player))
        {
            System.out.println("[Teleport] The console cannot run this command!");
            return true;
        }

        /* Setting requester to the player that sent the command */
        requester = ((Player) commandSender).getPlayer();

        if (requester == null)
        {
            Bukkit.getLogger().log(Level.SEVERE, "Requester was null when sending teleport request");
            return false;
        }

        /* Checking whether args are valid */
        if (args.length != 1)
        {
            MessengerHelper.sendMessageToPlayer(requester, "&7[&cTeleport&7] &8»&7 Usage is /tpr &e<player>");
            return true;
        }

        /* Checking whether the target exists */
        if (Bukkit.getPlayer(args[0]) == null)
        {
            MessengerHelper.sendMessageToPlayer(requester,"&7[&cTeleport&7] &8»&7 That player does not exist!");
            return true;
        }

        /* Setting target to the argument passed in by the requester */
        target = Bukkit.getPlayer(args[0]);

        if (target == null)
        {
            Bukkit.getLogger().log(Level.SEVERE, "Target was null when receiving teleport request");
            return false;
        }

        /* Checking if the player wants to teleport to themselves */
        if (target.getUniqueId() == requester.getUniqueId())
        {
            MessengerHelper.sendMessageToPlayer(requester, "&7[&cTeleport&7] &8»&7 You cannot teleport to yourself!");
            return true;
        }

        /* Checking if the target has a pending request */
        if (TeleportManager.hasRequest(target))
        {
            MessengerHelper.sendMessageToPlayer(requester,"&7[&cTeleport&7] &8»&7 That player already has a pending request!");
            return true;
        }

        /* If the requester has a request pending remove the old one */
        if (TeleportManager.isRequesting(requester))
        {
            TeleportManager.removeRequestByRequester(requester);
            MessengerHelper.sendMessageToPlayer(requester,"&7[&aTeleport&7] &8»&7 Removed your previous request!");
        }

        /* Sending the messages */
        MessengerHelper.sendMessageToPlayer(requester, "&7[&aTeleport&7] &8»&7 Request sent to &e" + target.getPlayer().getDisplayName() + "!");
        MessengerHelper.sendMessageToPlayer(target, "&7[&aTeleport&7] &8»&7 A teleport request has been sent from &a" + requester.getDisplayName() + "&7 type &e/tpa&7 to accept the request.");

        /* Playing a sound to the requester and target */
        requester.playSound(requester, Sound.BLOCK_NOTE_BLOCK_PLING, 3.0F, 2.0F);
        target.playSound(target, Sound.BLOCK_NOTE_BLOCK_PLING, 3.0F, 2.0F);

        /* Puts the request in the hashmap (store the request) */
        TeleportManager.addRequest(target, requester);

        return true;
    }
}


