package me.sark.coollookin.coollookin;

import me.sark.coollookin.coollookin.commands.general.CurrentOnlinePlayers;

import me.sark.coollookin.coollookin.commands.general.Nick;
import me.sark.coollookin.coollookin.commands.homecommand.Home;
import me.sark.coollookin.coollookin.commands.homecommand.HomeDataManager;
import me.sark.coollookin.coollookin.commands.homecommand.SetHome;
import me.sark.coollookin.coollookin.commands.teleport.TeleportAccept;
import me.sark.coollookin.coollookin.commands.teleport.TeleportDeny;
import me.sark.coollookin.coollookin.commands.teleport.TeleportRequest;
import me.sark.coollookin.coollookin.events.ChatEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class CoolLookin extends JavaPlugin
{

    public static HomeDataManager homeData;

    @Override
    public void onEnable()
    {
        System.out.println("Started CoolLookin!");

        getServer().getPluginManager().registerEvents(new ChatEvents(), this);

        getServer().getPluginCommand("tpd").setExecutor(new TeleportDeny());
        getServer().getPluginCommand("tpr").setExecutor(new TeleportRequest());
        getServer().getPluginCommand("tpa").setExecutor(new TeleportAccept());

        getServer().getPluginCommand("currentonlineplayers").setExecutor(new CurrentOnlinePlayers());

        getServer().getPluginCommand("nick").setExecutor(new Nick());

        getServer().getPluginCommand("sethome").setExecutor(new SetHome());
        getServer().getPluginCommand("home").setExecutor(new Home());

        this.homeData = new HomeDataManager(this);
    }

    @Override
    public void onDisable()
    {
        System.out.println("Stopping CoolLookin!");
    }
}
