package wdfeer.rusty_moss

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import wdfeer.rusty_moss.Mod.MOD_ID

object RustyNugget : Item(Settings()) {
    fun register() {
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "rusty_nugget"), this)

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
            .register(ItemGroupEvents.ModifyEntries { itemGroup: FabricItemGroupEntries ->
                itemGroup.add(this)
            })
    }
}