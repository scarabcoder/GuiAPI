package com.scarabcoder.guiapi

import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.*

sealed class GuiComponent {

    val id: UUID = UUID.randomUUID()
    var action: (Player) -> Unit = {}
    var item: ItemBuilder = ItemBuilder(Material.STONE)

}

class GuiButton(var x: Int, var y: Int): GuiComponent() {


}

class GuiBox(var fromX: Int, var fromY: Int, var toX: Int, var toY: Int): GuiComponent() {



}