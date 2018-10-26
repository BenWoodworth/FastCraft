package net.benwoodworth.fastcraft.platform.text

interface FcTextBuilder {

    fun text(text: String): FcTextBuilderTyped

    fun translation(key: String): FcTextBuilderTyped

    //fun score(score: FcTextScore): FcTextBuilderTyped
    //fun selector(selector: String): FcTextBuilderTyped
    //fun keybind(keybind: String): FcTextBuilderTyped
}
