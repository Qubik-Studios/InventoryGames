package ch.maxthier.ticatactoe;

import ch.maxthier.main.Main;
import ch.maxthier.manager.GameManager;
import ch.maxthier.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TTTGame<privat> {

    private final Player p1;
    private final Player p2;
    private final boolean start;
    private Random random;
    public Boolean actual;
    private Inventory inv;
    private ItemStack item1;
    private ItemStack item2;
    private int winner;
    public Boolean finish;
    public Boolean isFull;
    private int[] effect;

    public TTTGame(Player p1, Player p2) {
        FileConfiguration config = Main.getPlugin().getConfig();
        this.p1 = p1;
        this.p2 = p2;
        this.random = new Random();
        if (random.nextBoolean()) {
            this.start = true;
            this.actual = true;
            this.p2.sendMessage("§aYou start!");
            this.p1.sendMessage("§6" + p2.getName() + " §astarts!");
        } else {
            this.start = false;
            this.actual = false;
            this.p1.sendMessage("§aYou start!");
            this.p2.sendMessage("§6" + p1.getName() + " §astarts!");
        }
        this.finish = false;
        inv = Bukkit.createInventory(null, InventoryType.DISPENSER, "§aTicTacToe");
        item1 = new ItemBuilder(Material.getMaterial(config.getString("TicTacToe.Item1")), 1).setName("§2" + this.p1.getName()).build();
        item2 = new ItemBuilder(Material.getMaterial(config.getString("TicTacToe.Item2")), 1).setName("§1" + this.p2.getName()).build();
        openInventory();
    }

    public boolean checkWin() {
        if (checkLine() || checkPerpendicular() || checkX()) {
            return true;
        } else
            return false;
    }

    public boolean checkLine() {
        if (this.inv.getItem(0) != null && this.inv.getItem(1) != null && this.inv.getItem(2) != null) {
            if (this.inv.getItem(0).getType() == this.item1.getType() && this.inv.getItem(1).getType() == this.item1.getType() && this.inv.getItem(2).getType() == this.item1.getType()) {
                this.winner = 1;
                effect = new int[]{0, 1, 2};
                return true;
            }
            if (this.inv.getItem(0).getType() == this.item2.getType() && this.inv.getItem(1).getType() == this.item2.getType() && this.inv.getItem(2).getType() == this.item2.getType()) {
                this.winner = 2;
                effect = new int[]{0, 1, 2};
                return true;
            }
        }

        if (this.inv.getItem(3) != null && this.inv.getItem(4) != null && this.inv.getItem(5) != null) {
            if (this.inv.getItem(3).getType() == this.item1.getType() && this.inv.getItem(4).getType() == this.item1.getType() && this.inv.getItem(5).getType() == this.item1.getType()) {
                this.winner = 1;
                effect = new int[]{3, 4, 5};
                return true;
            }
            if (this.inv.getItem(3).getType() == this.item2.getType() && this.inv.getItem(4).getType() == this.item2.getType() && this.inv.getItem(5).getType() == this.item2.getType()) {
                this.winner = 2;
                effect = new int[]{3, 4, 5};
                return true;
            }
        }

        if (this.inv.getItem(6) != null && this.inv.getItem(7) != null && this.inv.getItem(8) != null) {
            if (this.inv.getItem(6).getType() == this.item1.getType() && this.inv.getItem(7).getType() == this.item1.getType() && this.inv.getItem(8).getType() == this.item1.getType()) {
                this.winner = 1;
                effect = new int[]{6, 7, 8};
                return true;
            }
            if (this.inv.getItem(6).getType() == this.item2.getType() && this.inv.getItem(7).getType() == this.item2.getType() && this.inv.getItem(8).getType() == this.item2.getType()) {
                this.winner = 2;
                effect = new int[]{6, 7, 8};
                return true;
            }
        }
        return false;
    }

    public boolean checkPerpendicular() {
        if (this.inv.getItem(0) != null && this.inv.getItem(3) != null && this.inv.getItem(6) != null) {
            if (this.inv.getItem(0).getType() == this.item1.getType() && this.inv.getItem(3).getType() == this.item1.getType() && this.inv.getItem(6).getType() == this.item1.getType()) {
                this.winner = 1;
                effect = new int[]{0, 3, 6};
                return true;
            }
            if (this.inv.getItem(0).getType() == this.item2.getType() && this.inv.getItem(3).getType() == this.item2.getType() && this.inv.getItem(6).getType() == this.item2.getType()) {
                this.winner = 2;
                effect = new int[]{0, 3, 6};
                return true;
            }
        }

        if (this.inv.getItem(1) != null && this.inv.getItem(4) != null && this.inv.getItem(7) != null) {
            if (this.inv.getItem(1).getType() == this.item1.getType() && this.inv.getItem(4).getType() == this.item1.getType() && this.inv.getItem(7).getType() == this.item1.getType()) {
                this.winner = 1;
                effect = new int[]{1, 4, 7};
                return true;
            }
            if (this.inv.getItem(1).getType() == this.item2.getType() && this.inv.getItem(4).getType() == this.item2.getType() && this.inv.getItem(7).getType() == this.item2.getType()) {
                this.winner = 2;
                effect = new int[]{1, 4, 7};
                return true;
            }
        }

        if (this.inv.getItem(2) != null && this.inv.getItem(5) != null && this.inv.getItem(8) != null) {
            if (this.inv.getItem(2).getType() == this.item1.getType() && this.inv.getItem(5).getType() == this.item1.getType() && this.inv.getItem(8).getType() == this.item1.getType()) {
                this.winner = 1;
                effect = new int[]{2, 5, 8};
                return true;
            }
            if (this.inv.getItem(2).getType() == this.item2.getType() && this.inv.getItem(5).getType() == this.item2.getType() && this.inv.getItem(8).getType() == this.item2.getType()) {
                this.winner = 2;
                effect = new int[]{2, 5, 8};
                return true;
            }
        }
        return false;
    }

    public boolean checkX() {
        if (this.inv.getItem(0) != null && this.inv.getItem(4) != null && this.inv.getItem(8) != null) {
            if (this.inv.getItem(0).getType() == this.item1.getType() && this.inv.getItem(4).getType() == this.item1.getType() && this.inv.getItem(8).getType() == this.item1.getType()) {
                this.winner = 1;
                effect = new int[]{0, 4, 8};
                return true;
            }
            if (this.inv.getItem(0).getType() == this.item2.getType() && this.inv.getItem(4).getType() == this.item2.getType() && this.inv.getItem(8).getType() == this.item2.getType()) {
                this.winner = 2;
                effect = new int[]{0, 4, 8};
                return true;
            }
        }

        if (this.inv.getItem(2) != null && this.inv.getItem(4) != null && this.inv.getItem(6) != null) {
            if (this.inv.getItem(2).getType() == this.item1.getType() && this.inv.getItem(4).getType() == this.item1.getType() && this.inv.getItem(6).getType() == this.item1.getType()) {
                this.winner = 1;
                effect = new int[]{2, 4, 6};
                return true;
            }

            if (this.inv.getItem(2).getType() == this.item2.getType() && this.inv.getItem(4).getType() == this.item2.getType() && this.inv.getItem(6).getType() == this.item2.getType()) {
                this.winner = 2;
                effect = new int[]{2, 4, 6};
                return true;
            }
        }
        return false;
    }

    public void SwitchTurn() {
        if (this.actual) {
            this.actual = false;
        } else {
            this.actual = true;
        }
    }

    public boolean PlaceItem(Boolean Player, int Slot) {
        if (this.inv.getItem(Slot) == null) {
            if (Player) {
                this.inv.setItem(Slot, this.item2);
                return true;
            } else {
                this.inv.setItem(Slot, this.item1);
                return true;
            }
        }
        return false;
    }

    public boolean IsInvFull() {
        for (int i = 0; i < 9; i++) {
            if (this.inv.getItem(i) == null) {
                return false;
            }
        }
        return true;
    }

    public void afterRound() {
        uptadeInventory();
        SwitchTurn();
        if (checkWin()) {
            GameEnd();
            return;
        }
        if (IsInvFull()) {
            this.winner = 0;
            GameEnd();
            return;
        }
    }

    public void GameEnd() {
        this.finish = true;
        makeeffect();
        if (this.winner == 0) {
            p1.sendMessage("§6No one has won!");
            p2.sendMessage("§6No one has won!");
        } else {
            if (this.winner == 1) {
                p1.sendMessage("§aYou have §lwon§r§a!");
                p2.sendMessage("§4You have  §llost§r§4!");
            } else {
                p2.sendMessage("§aYou have §lwon§r§a!");
                p1.sendMessage("§4You have §llost§r§4!");
            }
        }
        GameManager.tttingame.remove(this.p1.getName());
        GameManager.tttingame.remove(this.p2.getName());
        GameManager.tttgames.remove(this.p1.getName());
        GameManager.tttgames.remove(this.p2.getName());
    }

    private void makeeffect(){
        if(this.winner == 0){
            return;
        }
        for (int i : this.effect){
            ItemStack item = this.inv.getItem(i);
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
            item.setItemMeta(meta);
            this.inv.setItem(i, item);
        }
    }

    private void uptadeInventory() {
        this.p1.updateInventory();
        this.p2.updateInventory();
    }

    private void openInventory() {
        this.p1.openInventory(this.inv);
        this.p2.openInventory(this.inv);
        GameManager.tttinv.add(this.p1.getName());
        GameManager.tttinv.add(this.p2.getName());
    }

    public boolean isactual(Player p) {
        if (p.getName().equalsIgnoreCase(this.p1.getName())) {
            return false;
        }
        if (p.getName().equalsIgnoreCase(this.p2.getName())) {
            return true;
        }
        return false;
    }

    public void stopGame() {
        this.p1.sendMessage("§cThe game was canceled!");
        this.p2.sendMessage("§cThe game was canceled!");
        this.finish = true;
        GameManager.tttingame.remove(this.p1);
        GameManager.tttingame.remove(this.p2);
    }
}