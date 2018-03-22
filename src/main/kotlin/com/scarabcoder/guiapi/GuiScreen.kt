package com.scarabcoder.guiapi

import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import java.util.*

class GuiScreen(var title: String, val player: Player, val size: Int): Listener {

    init {
        Bukkit.getPluginManager().registerEvents(this, GuiAPI.thisPlugin)
    }

    private var ignoreClose = false


    val components = ArrayList<GuiComponent>()

    fun refresh() {
        val inv = Bukkit.createInventory(null, size * 9, title)
        components.sortBy { it !is GuiBox }
        components.forEach {
            var item = it.item.itemStack
            val craft = CraftItemStack.asNMSCopy(item)
            if(craft.hasTag()){
                craft.tag!!.setString("buttonid", it.id.toString())
            }
            item = CraftItemStack.asBukkitCopy(craft)
            if(it is GuiBox){
                for(x in it.fromX..it.toX) {
                    for(y in it.fromY..it.toY) {
                        inv.setItem(toPos(x, y), item)
                    }
                }
            }else {
                it as GuiButton
                inv.setItem(toPos(it.x, it.y), item)
            }
        }
        if(player.openInventory != null)
            ignoreClose = true
        player.openInventory(inv)
    }

    private fun toPos(x: Int, y: Int): Int = (x - 1) * 9 + (y - 1)


    open fun onGuiClose() {}

    @EventHandler
    private fun onGuiClick(e: InventoryClickEvent){
        e.isCancelled = true
        if(e.currentItem != null){

            val craft = CraftItemStack.asNMSCopy(e.currentItem)
            if(craft.tag != null && craft.tag!!.hasKey("buttonid")){
                components.find { it.id == UUID.fromString(craft.tag!!.getString("buttonid")) }!!.action.invoke(e.whoClicked as Player)
            }
        }
    }

    @EventHandler
    private fun onGuiExit(e: InventoryCloseEvent){
        if(e.player.uniqueId == player.uniqueId && !ignoreClose) {
            this.onGuiClose()
            HandlerList.unregisterAll(this)
        }
        if(ignoreClose) ignoreClose = false
    }





}