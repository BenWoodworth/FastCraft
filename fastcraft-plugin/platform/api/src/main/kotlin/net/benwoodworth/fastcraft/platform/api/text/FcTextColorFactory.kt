package net.benwoodworth.fastcraft.platform.api.text

interface FcTextColorFactory {

    fun  getBlack(): FcTextColor

    fun  getDarkBlue(): FcTextColor

    fun  getDarkGreen(): FcTextColor

    fun  getDarkAqua(): FcTextColor

    fun  getDarkRed(): FcTextColor

    fun  getDarkPurple(): FcTextColor

    fun  getGold(): FcTextColor

    fun  getGray(): FcTextColor

    fun  getDarkGray(): FcTextColor

    fun  getBlue(): FcTextColor

    fun  getGreen(): FcTextColor

    fun  getAqua(): FcTextColor

    fun  getRed(): FcTextColor

    fun  getLightPurple(): FcTextColor

    fun  getYellow(): FcTextColor

    fun  getWhite(): FcTextColor

    fun  getReset(): FcTextColor

    fun  getNone(): FcTextColor
}