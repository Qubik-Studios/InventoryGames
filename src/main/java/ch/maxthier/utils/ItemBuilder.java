package ch.maxthier.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {

    private ItemStack item;
    private ItemMeta meta;

    public ItemBuilder(Material material, int amount, short SubID){
        item = new ItemStack(material, amount, SubID);
        meta = item.getItemMeta();
    }

    public ItemBuilder(Material material, int amount){
        this(material, amount, (short) 0);
    }

    public ItemBuilder setAmount(int amount){
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder setName(String name){
        meta.setDisplayName(name);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment ench, int Level, boolean ignore){
        meta.addEnchant(ench, Level, ignore);
        return this;
    }

    public ItemBuilder unbreakable(boolean unbreakable){
        meta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemStack build(){
        item.setItemMeta(meta);
        return item;
    }
}
