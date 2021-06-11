package net.benwoodworth.fastcraft.platform.player

@JvmInline
value class FcSound(val value: Any) {
    interface Operations

    interface Factory {
        val uiButtonClick: FcSound
    }
}
