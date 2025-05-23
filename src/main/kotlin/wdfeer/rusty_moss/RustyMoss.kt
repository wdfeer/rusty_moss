package wdfeer.rusty_moss

import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.Blocks
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.ItemEntity
import net.minecraft.fluid.Fluids
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.biome.BiomeKeys
import net.minecraft.world.gen.GenerationStep
import wdfeer.rusty_moss.Mod.MOD_ID
import kotlin.random.Random

private val id = Identifier.of(MOD_ID, "rusty_moss")

object RustyMoss : BlockWithEntity(FabricBlockSettings.copy(Blocks.MOSS_BLOCK)) {
    fun register() {
        Registry.register(Registries.BLOCK, id, this)
        Registry.register(Registries.BLOCK_ENTITY_TYPE, id, rustyMossBlockEntityType)
        Registry.register(Registries.ITEM, id, BlockItem(this, Item.Settings()))

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL)
            .register(ModifyEntries { itemGroup: FabricItemGroupEntries ->
                itemGroup.add(this.asItem())
            })

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES),
            GenerationStep.Feature.TOP_LAYER_MODIFICATION, // must happen after vegetal_decoration
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, id)
        )
    }

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity = RustyMossBlockEntity(pos, state)

    override fun <T : BlockEntity?> getTicker(
        world: World?,
        state: BlockState?,
        type: BlockEntityType<T>?
    ): BlockEntityTicker<T>? {
        return checkType(type, rustyMossBlockEntityType, RustyMossBlockEntity::tick)
    }

    @Suppress("OVERRIDE_DEPRECATION")
    override fun getRenderType(state: BlockState?): BlockRenderType = BlockRenderType.MODEL
}

private val rustyMossBlockEntityType = FabricBlockEntityTypeBuilder.create(::RustyMossBlockEntity, RustyMoss).build()

private class RustyMossBlockEntity(
    pos: BlockPos?,
    state: BlockState?
) : BlockEntity(rustyMossBlockEntityType, pos, state), BlockEntityTicker<RustyMossBlockEntity> {
    override fun tick(world: World?, pos: BlockPos?, state: BlockState?, blockEntity: RustyMossBlockEntity?) {
        Companion.tick(world, pos, state, blockEntity)
    }

    companion object {
        @Suppress("UNUSED_PARAMETER")
        fun tick(world: World?, pos: BlockPos?, state: BlockState?, blockEntity: RustyMossBlockEntity?) {
            val serverWorld = world as? ServerWorld ?: return
            if (pos == null) return

            val adjacentPositions = listOf(pos.up(), pos.down(), pos.north(), pos.east(), pos.south(), pos.west())

            val waterPos = adjacentPositions.find { world.getBlockState(it).fluidState.fluid == Fluids.FLOWING_WATER }

            if (waterPos != null && Random.nextFloat() < Config.getDropChance()) {
                val entity =
                    waterPos.toCenterPos().run { ItemEntity(serverWorld, x, y, z, ItemStack(RustyNugget)) }
                world.spawnEntity(entity)
            }
        }
    }
}