package me.koldskal.kACT;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import lombok.Getter;
import me.koldskal.kACT.command.MainCommand;
import me.koldskal.kACT.configuration.ConfigManager;
import me.koldskal.kACT.listeners.CommandBlockListener;
import me.koldskal.kACT.listeners.PacketListener;
//import me.koldskal.kACT.managers.LicenseManager;
import me.koldskal.kACT.metrics.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Getter
    private static Main instance;
    @Getter
    private ProtocolManager protocolManager;
    @Getter
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        sendConsoleMessage(
                "§7―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――",
                "§7                     Copyright Koldskal 2025 ©",
                "§7    ██╗  ██╗ █████╗ ██╗     ██████╗  ██████╗██╗  ██╗ █████╗ ██╗",
                "§7    ██║ ██╔╝██╔══██╗██║     ██╔══██╗██╔════╝██║ ██╔╝██╔══██╗██║",
                "§7    █████═╝ ██║  ██║██║     ██║  ██║╚█████╗ █████═╝ ███████║██║",
                "§7    ██╔═██╗ ██║  ██║██║     ██║  ██║ ╚═══██╗██╔═██╗ ██╔══██║██║",
                "§7    ██║ ╚██╗╚█████╔╝███████╗██████╔╝██████╔╝██║ ╚██╗██║  ██║███████╗",
                "§7    ╚═╝  ╚═╝ ╚════╝ ╚══════╝╚═════╝ ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝",
                "§7 ",
                "§7§o             kAntiCommandTab | Plugin by: _Koldskal",
                "§7―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――"
        );

        // Initialize ProtocolLib if available
        if (Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")) {
            protocolManager = ProtocolLibrary.getProtocolManager();
        } else {
            getLogger().warning("[kACT] ProtocolLib not found! TAB-complete protection is disabled.");
        }

        // Initialize configuration
        configManager = new ConfigManager(this);

        // Register event listeners
        Bukkit.getPluginManager().registerEvents(new CommandBlockListener(this), this);
        if (protocolManager != null) {
            new PacketListener(this);
        }

        // Register commands
        PluginCommand kactCommand = getCommand("kact");
        if (kactCommand != null) {
            kactCommand.setExecutor(new MainCommand(this));
        } else {
            getLogger().severe("[kACT] ERROR: The command 'kact' is missing in plugin.yml!");
        }

        // Initialize bStats
        new Metrics(this, 25005);
    }

    private void sendConsoleMessage(String... messages) {
        Bukkit.getConsoleSender().sendMessage(messages);
    }
}