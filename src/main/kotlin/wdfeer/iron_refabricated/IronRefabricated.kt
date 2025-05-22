package wdfeer.iron_refabricated

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object IronRefabricated : ModInitializer {
    private val logger = LoggerFactory.getLogger("iron_refabricated")

	override fun onInitialize() {
		logger.info("Hello Fabric world!")
	}
}