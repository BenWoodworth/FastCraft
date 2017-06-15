package net.benwoodworth.fastcraft.core

/**
 * Created by ben on 6/15/17.
 */
interface FastCraftImplementation<out TInstance : FastCraft<*>> {

    val instance: TInstance
}
