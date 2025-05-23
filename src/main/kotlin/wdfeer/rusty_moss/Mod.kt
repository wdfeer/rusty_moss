package wdfeer.rusty_moss

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object Mod : ModInitializer {
	const val MOD_ID = "rusty_moss"
    val logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		Config.run { init() }
		RustyNugget.register()
		RustyMoss.register()

		logger.info("Rusty Moss initialized!")
	}
}