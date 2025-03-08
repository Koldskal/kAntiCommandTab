package me.koldskal.kACT.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.FieldAccessException;
import me.koldskal.kACT.Main;
import me.koldskal.kACT.configuration.ConfigManager;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class PacketListener {

    private final Main plugin;
    private final ConfigManager config;

    public PacketListener(Main plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfigManager();

        if (config.isTabCompleteProtectionEnabled()) {
            setupTabCompleteProtection();
        }
    }

    private void setupTabCompleteProtection() {
        if (plugin.getProtocolManager() == null) {
            plugin.getLogger().warning("[kACT] ProtocolLib blev ikke fundet! TAB-complete beskyttelse er deaktiveret.");
            return;
        }

        plugin.getProtocolManager().addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL, PacketType.Play.Client.TAB_COMPLETE) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                if (!config.isTabCompleteProtectionEnabled()) return;

                try {
                    Player player = event.getPlayer();
                    if (player.isOp()) return;

                    PacketContainer packet = event.getPacket();
                    String message = packet.getSpecificModifier(String.class).readSafely(0);

                    if (message == null || message.isEmpty()) return;

                    message = message.toLowerCase();

                    for (String cmd : config.getBlockedCommands()) {
                        if (message.startsWith("/" + cmd.toLowerCase())) {
                            event.setCancelled(true);

                            if (config.shouldLogBlockedCommands()) {
                                plugin.getLogger().info("[kACT] Blokerede TAB-complete forsøg for: " + player.getName());
                            }
                            return;
                        }
                    }
                } catch (FieldAccessException e) {
                    if (config.isDebugModeEnabled()) {
                        plugin.getLogger().log(Level.SEVERE, "[kACT] Kunne ikke tilgå packet field!", e);
                    }
                }
            }
        });
    }
}
