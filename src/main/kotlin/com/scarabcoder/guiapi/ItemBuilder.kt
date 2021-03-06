package com.scarabcoder.guiapi

import org.bukkit.Bukkit
import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.inventory.meta.SkullMeta

class ItemBuilder (var type: Material) {

    var name: String? = null
    var lore: ArrayList<String> = ArrayList()
    var amount: Int = 1
    var enchantEffect = false
    var color: DyeColor = DyeColor.WHITE
    var data: Int = 0
    var skullOwner: String? = null

    val itemStack: ItemStack
    get() {
        val stack = ItemStack(type, amount)
        val im = stack.itemMeta
        im.displayName = name
        im.lore = lore
        when(type) {
            Material.WOOL, Material.GLASS, Material.STAINED_GLASS_PANE, Material.STAINED_CLAY -> stack.durability = color.woolData.toShort()
            Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS -> {
                im as LeatherArmorMeta
                im.color = color.color
            }
            Material.SKULL_ITEM -> {
                if(skullOwner != null) {
                    stack.durability = 3
                    im as SkullMeta
                    im.owningPlayer = Bukkit.getPlayer(skullOwner!!)
                }
            }
            else -> {
                stack.durability = data.toShort()
            }
        }
        if(enchantEffect) {
            im.addItemFlags(ItemFlag.HIDE_ENCHANTS)
            im.addEnchant(Enchantment.LUCK, 1, true)
        }
        stack.itemMeta = im
        return stack
    }


}