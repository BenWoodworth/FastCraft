package net.benwoodworth.fastcraft.dependencies.api.text

interface FcTextScore {

    val name: String
    val objective: String
    val value: String?

    interface Factory {

        fun create(name: String, objective: String, value: String? = null): FcTextScore

        fun create(name: String, objective: String): FcTextScore {
            return create(name, objective, null)
        }
    }
}