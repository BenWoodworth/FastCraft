package net.benwoodworth.fastcraft.platform.text

interface FcTextBuilder {

    fun text(text: String): FcTextBuilderTyped<FcTextString>

    fun translation(key: String): FcTextBuilderTyped<FcTextTranslation>

    //fun score(score: FcTextScore): FcTextBuilderTyped<>
    //fun selector(selector: String): FcTextBuilderTyped<>
    //fun keybind(keybind: String): FcTextBuilderTyped<>
}
