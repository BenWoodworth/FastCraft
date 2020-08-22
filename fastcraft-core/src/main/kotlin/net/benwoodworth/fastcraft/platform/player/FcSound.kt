package net.benwoodworth.fastcraft.platform.player

inline class FcSound(val value: Any) {
    interface Operations

    interface Factory {
        val uiButtonClick: FcSound
    }
}
