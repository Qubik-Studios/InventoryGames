package ch.maxthier.ticatactoe;

import ch.maxthier.manager.GameManager;
import ch.maxthier.manager.GameSelecter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class TTTListener implements Listener {
    @EventHandler
    public void HandleInventoryclick(InventoryClickEvent ev) {
        Player p = (Player) ev.getWhoClicked();
        TTTGame instance = GameManager.tttgames.get(p.getName());
        if(GameManager.tttinv.contains(p.getName())){
            ev.setCancelled(true);
        }
        if (GameManager.tttingame.contains(p.getName())) {
            if(instance == null){
                return;
            }
            if (!instance.finish) {
                if (instance.isactual(p)) {
                    if (instance.actual) {
                        if (instance.PlaceItem(true, ev.getSlot())) {
                            instance.afterRound();
                            return;
                        }
                    } else {
                        return;
                    }
                } else {
                    if (!instance.actual) {
                        if (instance.PlaceItem(false, ev.getSlot())) {
                            instance.afterRound();
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }

    @EventHandler
    public void HandleInventoryClose(InventoryCloseEvent ev) {
        Player p = (Player) ev.getPlayer();
        if (GameManager.tttingame.contains(p.getName())) {
            TTTGame instance = GameManager.tttgames.get(p.getName());
            if (!instance.finish) {
                GameManager.tttinv.remove(ev.getPlayer().getName());
                instance.stopGame();
            }
        }else{
            if(GameManager.tttinv.contains(ev.getPlayer().getName())){
                GameManager.tttinv.remove(ev.getPlayer().getName());
            }
        }
    }
}