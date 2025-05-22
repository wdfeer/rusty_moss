package wdfeer.iron_refabricated

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.EquipmentSlot

object IronEnchantment : Enchantment(Rarity.RARE, EnchantmentTarget.WEAPON, arrayOf(EquipmentSlot.MAINHAND)) {
    // TODO: implement iron nugget drops on kill

    override fun canAccept(other: Enchantment?): Boolean {
        return super.canAccept(other) && other != Enchantments.LOOTING
    }
}