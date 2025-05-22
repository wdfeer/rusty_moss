package wdfeer.iron_refabricated

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier
import wdfeer.iron_refabricated.IronRefabricated.MOD_ID

object IronEnchantment : Enchantment(Rarity.RARE, EnchantmentTarget.WEAPON, arrayOf(EquipmentSlot.MAINHAND)) {
    override fun canAccept(other: Enchantment?): Boolean {
        return super.canAccept(other) && other != Enchantments.LOOTING
    }

    fun register() {
        Registry.register(Registries.ENCHANTMENT, Identifier.of(MOD_ID, "iron_enchantment"), this)

        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register { world, killer, victim ->
            if (killer is LivingEntity) onEntityDeath(world, killer, victim)
        }
    }

    private fun onEntityDeath(world: ServerWorld, killer: LivingEntity, victim: LivingEntity) {
        if (EnchantmentHelper.getEquipmentLevel(this, killer) <= 0) return

        val itemEntity = ItemEntity(world, victim.x, victim.y, victim.z, ItemStack(Items.IRON_NUGGET))
        world.spawnEntity(itemEntity)
    }
}