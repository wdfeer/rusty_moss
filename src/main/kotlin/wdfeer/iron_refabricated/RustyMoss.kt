package wdfeer.iron_refabricated

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import wdfeer.iron_refabricated.IronRefabricated.MOD_ID

object RustyMoss : Block(FabricBlockSettings.create()) {
    // TODO: drop rusty nuggets with flowing water

    fun register() {
        Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, "rusty_moss"), this)
    }
}