package eu.skyphantom.skypvp.utils.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ItemBuilder extends ItemStack {

    public ItemBuilder() {
        super(Material.AIR, 1);
    }

    public ItemBuilder(Material type) {
        super(type);
    }

    public ItemBuilder(Material type, int amount) {
        super(type, amount);
    }

    public ItemBuilder(Material type, int amount, short damage) {
        super(type, amount, damage);
    }

    public ItemBuilder(Material type, int amount, short damage, byte data) {
        super(type, amount, damage, data);
    }

    public ItemBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public ItemBuilder setMaterial(Material material) {
        setType(material);
        return this;
    }

    public ItemBuilder setDataId(int id) {
        setDurability((short) id);
        return this;
    }

    public ItemBuilder setName(String name) {
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(name);
        setItemMeta(meta);
        return this;
    }

    public ItemBuilder amount(int amount) {
        setAmount(amount);
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        ItemMeta meta = getItemMeta();
        meta.setLore(lore);
        setItemMeta(meta);
        return this;
    }

    public ItemBuilder durability(int i) {
        this.setDurability((short) i);
        return this;
    }

    public ItemBuilder lore(String... lore) {
        ItemMeta meta = getItemMeta();
        meta.setLore(Arrays.asList(lore));
        setItemMeta(meta);
        return this;
    }

    public ItemBuilder addToLore(String... lore) {
        ItemMeta meta = getItemMeta();
        List<String> loreList = meta.getLore();
        Collections.addAll(loreList, lore);
        meta.setLore(loreList);
        setItemMeta(meta);
        return this;
    }

    public ItemBuilder addToLore(List<String> lore) {
        ItemMeta meta = getItemMeta();
        List<String> loreList = meta.getLore();
        loreList.addAll((lore));
        meta.setLore(loreList);
        setItemMeta(meta);
        return this;
    }

    public ItemBuilder glow(boolean value) {
        ItemMeta meta = getItemMeta();
        if (value) {
            meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            addFlag(ItemFlag.HIDE_ENCHANTS);
        } else {
            meta.removeEnchant(Enchantment.ARROW_INFINITE);
            removeFlag(ItemFlag.HIDE_ENCHANTS);
        }
        setItemMeta(meta);
        return this;
    }

    public boolean isGlow() {
        ItemMeta meta = getItemMeta();
        return meta.getItemFlags().contains(ItemFlag.HIDE_ENCHANTS);
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level) {
        ItemMeta itemMeta = getItemMeta();
        itemMeta.addEnchant(enchantment, level, false);
        setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder removeEnchant(Enchantment enchantment) {
        ItemMeta itemMeta = getItemMeta();
        itemMeta.removeEnchant(enchantment);
        setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        ItemMeta meta = getItemMeta();
        meta.spigot().setUnbreakable(unbreakable);
        setItemMeta(meta);
        return this;
    }

    public ItemBuilder addFlag(ItemFlag flag) {
        ItemMeta meta = getItemMeta();
        meta.addItemFlags(flag);
        setItemMeta(meta);
        return this;
    }

    public ItemBuilder removeFlag(ItemFlag flag) {
        ItemMeta meta = getItemMeta();
        meta.removeItemFlags(flag);
        setItemMeta(meta);
        return this;
    }

    public ItemBuilder skullOwner(String playerName) {
        SkullMeta meta = (SkullMeta) getItemMeta();
        meta.setOwner(playerName);
        setItemMeta(meta);
        return this;
    }

    public ItemBuilder addSkullValue(String value) {
        UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
        Bukkit.getUnsafe().modifyItemStack(this, "{display:SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}");
        return this;
    }

    public boolean isUnbreakable() {
        return getItemMeta().spigot().isUnbreakable();
    }

}