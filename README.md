# GuiAPI

This library allows for easier creation of vanilla inventory GUIs in Kotlin. 
[It uses dynamically typed approach](https://kotlinlang.org/docs/reference/type-safe-builders.html), making it easy to tell how a GUI is setup.

## Example
```Kotlin
val gui = gui("${ChatColor.RED}Test GUI", player, 4) {
    box(1, 1, 4, 9) {
        item(Material.STAINED_GLASS_PANE) {
            name = ""
            color = DyeColor.YELLOW
        }
    }
    button(2, 5) {
        item(Material.COMPASS) {
            name = "${ChatColor.GREEN}My Amazing Button"
            onClick { player ->
                player.sendMessage("Button clicked.")
            }
        }
    }
}
gui.refresh()
```
