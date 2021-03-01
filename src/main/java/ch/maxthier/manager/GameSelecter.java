package ch.maxthier.manager;

import ch.maxthier.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class GameSelecter implements Listener {

    private static Inventory inv;

    public static void build(String title) {
        inv = Bukkit.createInventory(null, 1 * 9, title);
        inv.setItem(0, new ItemBuilder(Material.CRAFTING_TABLE, 1).setName("§aTicTacToe").build());
    }

    public static Inventory getInv() {
        return inv;
    }

    @EventHandler
    public void HandleInventoryCLick(InventoryClickEvent ev) {
        Player p = (Player) ev.getWhoClicked();
        if (!GameManager.selection.containsKey(p.getName())) {
            return;
        }
        ev.setCancelled(true);
        Player target = Bukkit.getPlayer(GameManager.selection.get(p.getName()));
        if (target == null) {
            return;
        }
        switch (ev.getCurrentItem().getType()) {
            case CRAFTING_TABLE:
                p.closeInventory();
                p.sendMessage("§aYou have been challanged by §6" + target.getName() + " §ato a §6TicTacToe §agame!");
                target.sendMessage("§aYou have been challanged to a TicTacToe game by§6" + p.getName() + "§a! To accept the challenge just type §6/challenge accept " + p.getName() + "§a!");
                GameManager.challenge.put(p.getName(), target.getName());
                GameManager.challengegame.put(p.getName(), Games.TICTACTOE);
                GameManager.selection.remove(p.getName());
                break;
        }
    }

    @EventHandler
    public void HandleInventoryClose(InventoryCloseEvent ev) {
        Player p = (Player) ev.getPlayer();
        if (GameManager.selection.containsKey(p.getName())) {
            GameManager.selection.remove(p.getName());
        }
    }
}