package me.koldskal.kACT.menu;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.koldskal.kACT.configuration.ConfigManager;
import me.koldskal.kACT.utils.Colorize;
import me.koldskal.kACT.utils.item.ItemBuilder;
import me.koldskal.kACT.utils.messaging.Symbols;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MainMenu {

    private static ConfigManager config;

    public static void open(Player player) {
        Gui gui = Gui.gui().rows(5).title(Component.text("» kAntiCommandTab Settings")).disableAllInteractions().create();

        // Add Gui Fill
        gui.getFiller().fillTop(new GuiItem((new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 1).name(" ")).build()));
        gui.getFiller().fillBottom(new GuiItem((new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).name(" ")).build()));

        // Menu Information
        gui.setItem(1, 5, dev.triumphteam.gui.builder.item.ItemBuilder.from(Material.SIGN).name(Component.text(Colorize.colorize("&8" + Symbols.ARROW_RIGHT.getSymbol()  + " &6&lkAntiCommandTab &f&lSettings " + "&8" + Symbols.ARROW_LEFT.getSymbol() ))).lore(Component.text(Colorize.colorize(" &8" + Symbols.CIRCLE.getSymbol() + " &7&oBrug '/kact reload' for direkte reload.."))).asGuiItem());

        // Reload Item
        gui.setItem(3, 5, dev.triumphteam.gui.builder.item.ItemBuilder.from(Material.REDSTONE_COMPARATOR).name(Component.text(Colorize.colorize("&8" + Symbols.ARROW_RIGHT.getSymbol() + " &c&lConfig &f&lReload"))).lore(Component.text(Colorize.colorize(" &8" + Symbols.CIRCLE.getSymbol() + " &7&oKlik for at reload configuration!"))).glow(true).asGuiItem(
                event -> { Bukkit.dispatchCommand(player, "kact reload"); player.closeInventory(); } ));

        gui.open(player);
    }
}
