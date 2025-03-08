package me.koldskal.kACT.utils;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class Colorize {

    public static String colorize(String message) {
        if (message == null || message.isEmpty()) return "";
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> colorize(List<String> messages) {
        return messages.stream()
                .map(Colorize::colorize)
                .collect(Collectors.toList());
    }
}
