package me.koldskal.kACT.command;

import me.koldskal.kACT.Main;
import me.koldskal.kACT.configuration.ConfigManager;
import me.koldskal.kACT.menu.MainMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    private final ConfigManager config;

    public MainCommand(Main plugin) {
        this.config = plugin.getConfigManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!"kact".equalsIgnoreCase(cmd.getName())) return false;

        if (!sender.hasPermission("kact.admin")) {
            if (args.length <= 0) {
                MainMenu.open((Player) sender);
                return true;
            } else if (args.toString().equalsIgnoreCase("reload")) {
                sender.sendMessage(formatMessage(config.getNoPermissionMessage()));
                return true;
            }
        }
        config.reloadConfig();
        sender.sendMessage(formatMessage(config.getReloadMessage()));
        return true;
    }

    private String formatMessage(String message) {
        return (message != null) ? message.replace('&', '§') : "§c[Fejl] Besked mangler i config!";
    }
}
