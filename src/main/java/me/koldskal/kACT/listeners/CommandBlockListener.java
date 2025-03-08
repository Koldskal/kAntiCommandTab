package me.koldskal.kACT.listeners;

import me.koldskal.kACT.Main;
import me.koldskal.kACT.configuration.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import java.util.List;

public class CommandBlockListener implements Listener {

    private final ConfigManager config;

    public CommandBlockListener(Main plugin) {
        this.config = plugin.getConfigManager();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
        if (!config.isCommandBlockingEnabled()) return;

        Player player = event.getPlayer();
        String message = event.getMessage().toLowerCase();

        if (message.isEmpty()) return;

        List<String> blockedCommands = config.getBlockedCommands();

        if (!player.isOp()) {
            for (String blocked : blockedCommands) {
                if (message.startsWith("/" + blocked.toLowerCase())) {
                    player.sendMessage(config.getBlockedMessage());
                    event.setCancelled(true);

                    if (config.shouldLogBlockedCommands()) {
                        Main.getInstance().getLogger().info("[kACT] " + player.getName() + " forsøgte at bruge: " + message);
                    }
                    return;
                }
            }
        }
    }
}
