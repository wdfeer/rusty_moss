package wdfeer.iron_refabricated

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.EquipmentSlot
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import wdfeer.iron_refabricated.IronRefabricated.MOD_ID


object IronEnchantment : Enchantment(Rarity.RARE, EnchantmentTarget.WEAPON, arrayOf(EquipmentSlot.MAINHAND)) {
    // TODO: implement iron nugget drops on kill

    override fun canAccept(other: Enchantment?): Boolean {
        return super.canAccept(other) && other != Enchantments.LOOTING
    }

    fun register() {
        Registry.register(Registries.ENCHANTMENT, Identifier.of(MOD_ID, "iron_enchantment"), this)
    }
}