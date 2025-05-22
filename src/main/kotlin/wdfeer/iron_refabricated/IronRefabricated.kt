package wdfeer.iron_refabricated

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object IronRefabricated : ModInitializer {
	const val MOD_ID = "iron_refabricated"
    private val logger = LoggerFactory.getLogger("iron_refabricated")

	override fun onInitialize() {
		IronEnchantment.register()
		logger.info("Iron just got refabricated!")
	}
}