package wdfeer.iron_refabricated

import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import wdfeer.iron_refabricated.IronRefabricated.MOD_ID

object RustyNugget : Item(Settings()) {
    fun register() {
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "rusty_nugget"), this)
    }
}