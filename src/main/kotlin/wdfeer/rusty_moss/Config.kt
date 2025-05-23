package wdfeer.rusty_moss

import net.fabricmc.loader.api.FabricLoader
import java.io.File

object Config {
    private const val BASE_CHANCE: Float = 0.0004f
    private var chanceMult = 1f
    fun getDropChance(): Float = BASE_CHANCE * chanceMult

    private fun getConfigFile(): File {
        val path = FabricLoader.getInstance().configDir.resolve(Mod.MOD_ID + ".cfg")
        return path.toFile()
    }

    private const val CHANCE_KEY = "rusty_nugget_drop_chance_multiplier"

    fun Mod.init() {
        val file = getConfigFile()
        if (!file.exists()) {
            logger.info("Config file not found, writing a default config.")
            writeConfig()
            return
        }

        val lines = file.readLines()
        val map = lines.associate { line ->
            line.takeWhile { it != '=' }.trim() to line.takeLastWhile { it != '=' }.trim()
        }

        val chance = map[CHANCE_KEY]?.toFloatOrNull()
        if (chance != null) chanceMult = chance
        else {
            logger.warn("Invalid config found! Creating a default config.")
            writeConfig()
        }
    }

    private fun writeConfig() {
        val str = "$CHANCE_KEY = $chanceMult"

        val file = getConfigFile()

        if (!file.exists())
            file.createNewFile()
        else {
            file.delete()
            file.createNewFile()
        }

        file.writeText(str)
    }
}