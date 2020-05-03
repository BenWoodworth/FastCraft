package net.benwoodworth.fastcraft.platform.server

interface FcPermission {
    val name: String

    interface Factory {
        fun create(
            name: String,
            description: String? = null,
            children: List<FcPermission> = emptyList(),
        ): FcPermission
    }
}
