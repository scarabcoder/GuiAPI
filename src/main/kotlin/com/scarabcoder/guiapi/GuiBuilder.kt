package com.scarabcoder.guiapi

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player

fun gui(title: String, player: Player, size: Int, init: GuiScreen.() -> Unit): GuiScreen {
    val g = GuiScreen(title, player, size)
    g.init()
    return g
}

fun GuiScreen.button(x: Int, y: Int, init: GuiButton.() -> Unit = {}): GuiScreen{
    val b = GuiButton(x, y)
    b.init()
    this.components.add(b)
    return this
}


fun GuiScreen.box(fromX: Int, fromY: Int, toX: Int, toY: Int, init: GuiBox.() -> Unit = {}): GuiScreen {
    val b = GuiBox(fromX, fromY, toX, toY)
    b.init()
    this.components.add(b)
    return this
}

fun <T: GuiComponent>T.onClick(action: (Player) -> Unit): T {
    this.action = action
    return this
}

fun <T: GuiComponent>T.item(type: Material, init: ItemBuilder.() -> Unit): T {
    val itemBuilder = ItemBuilder(type)
    itemBuilder.init()
    this.item = itemBuilder
    return this
}