package com.scarabcoder.guiapi

import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.LeatherArmorMeta

class ItemBuilder (var type: Material) {

    var name: String? = null
    var lore: List<String> = ArrayList()
    var amount: Int = 1
    var enchantEffect = false
    var color: DyeColor = DyeColor.WHITE

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
            else -> {}
        }
        stack.itemMeta = im
        return stack
    }


}