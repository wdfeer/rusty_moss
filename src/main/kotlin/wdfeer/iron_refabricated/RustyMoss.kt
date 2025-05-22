package wdfeer.iron_refabricated

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import wdfeer.iron_refabricated.IronRefabricated.MOD_ID

object RustyMoss : BlockWithEntity(FabricBlockSettings.create()) {
    fun register() {
        Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, "rusty_moss"), this)
    }

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity = RustyMossBlockEntity(pos, state)

    override fun <T : BlockEntity?> getTicker(
        world: World?,
        state: BlockState?,
        type: BlockEntityType<T>?
    ): BlockEntityTicker<T>? {
        return checkType(type, rustyMossBlockEntityType, RustyMossBlockEntity::tick);
    }
}

private val rustyMossBlockEntityType = FabricBlockEntityTypeBuilder.create(::RustyMossBlockEntity, RustyMoss).build()
    .also { Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MOD_ID, "rusty_moss"), it) }

private class RustyMossBlockEntity(
    pos: BlockPos?,
    state: BlockState?
) : BlockEntity(rustyMossBlockEntityType, pos, state), BlockEntityTicker<RustyMossBlockEntity> {
    override fun tick(world: World?, pos: BlockPos?, state: BlockState?, blockEntity: RustyMossBlockEntity?) {
        TODO("drop rusty nuggets with flowing water")
    }
}