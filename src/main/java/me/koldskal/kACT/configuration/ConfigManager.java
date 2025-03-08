package me.koldskal.kACT.configuration;

import me.koldskal.kACT.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Collections;
import java.util.List;

public class ConfigManager {

    private FileConfiguration config;
    private final Main plugin;

    public ConfigManager(Main plugin) {
        this.plugin = plugin;
        reloadConfig();
    }

    public void reloadConfig() {
        plugin.reloadConfig();
        this.config = plugin.getConfig();
    }

    public List<String> getBlockedCommands() {
        return (config != null) ? config.getStringList("blocked-commands") : Collections.emptyList();
    }

    public boolean isTabCompleteProtectionEnabled() {
        return (config != null) && config.getBoolean("tab-complete-protection", true);
    }

    public boolean isCommandBlockingEnabled() {
        return (config != null) && config.getBoolean("command-blocking", true);
    }

    public boolean shouldLogBlockedCommands() {
        return (config != null) && config.getBoolean("log-blocked-commands", true);
    }

    public boolean isDebugModeEnabled() {
        return (config != null) && config.getBoolean("debug-mode", false);
    }

    public String getBlockedMessage() {
        return formatMessage(config != null ? config.getString("blocked-message", "&8[&c&l!&8] &7Dette har du ikke adgang til.") : "&cFejl: Besked ikke fundet!");
    }

    public String getReloadMessage() {
        return formatMessage(config != null ? config.getString("reload-message", "&8[&a&l!&8] &cKonfigurationen er blevet genindlæst!") : "&cFejl: Besked ikke fundet!");
    }

    public String getNoPermissionMessage() {
        return formatMessage(config != null ? config.getString("no-permission-message", "&8[&c&l!&8] &cDu har ikke tilladelse til dette.") : "&cFejl: Besked ikke fundet!");
    }

    private String formatMessage(String message) {
        return message.replace("&", "§");
    }
}
