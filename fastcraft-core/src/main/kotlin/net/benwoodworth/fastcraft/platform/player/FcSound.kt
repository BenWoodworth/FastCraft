package net.benwoodworth.fastcraft.platform.player

inline class FcSound(val value: Any) {
    interface TypeClass

    interface Factory {
        val uiButtonClick: FcSound
    }
}
