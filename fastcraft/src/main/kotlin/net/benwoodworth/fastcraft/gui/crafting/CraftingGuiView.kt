//package net.benwoodworth.fastcraft.gui.crafting
//
//import net.benwoodworth.fastcraft.platform.gui.mvp.MvpView
//import javax.inject.Inject
//
//class CraftingGuiView @Inject constructor(
//    guiFactory: FcGuiFactory,
//    fastCraftLang: net.benwoodworth.fastcraft.lang.FastCraftLang,
//
//    buttonWorkbenchFactory: ButtonWorkbenchFactory,
//    buttonMultiplierFactory: ButtonMultiplierFactory,
//    buttonRefreshFactory: ButtonRefreshFactory,
//    buttonPageFactory: ButtonPageFactory,
//
//    elementRecipeListFactory: ElementRecipeListFactory
//) : MvpView {
//
//    val elementRecipeList = elementRecipeListFactory.create(FcGuiRegion.Rectangle(0, 0, 8, 6))
//
//    val buttonWorkbench = buttonWorkbenchFactory.create(FcGuiPosition(8, 0))
//    val buttonMultiplier = buttonMultiplierFactory.create(FcGuiPosition(8, 2))
//    val buttonRefresh = buttonRefreshFactory.create(FcGuiPosition(8, 3))
//    val buttonPage = buttonPageFactory.create(FcGuiPosition(8, 5))
//
//    val gui = guiFactory.withSize(
//        9,
//        6,
//        fastCraftLang.guiTitle()
//    ).apply {
//        layout.addElement(elementRecipeList)
//        layout.addElement(buttonWorkbench)
//        layout.addElement(buttonMultiplier)
//        layout.addElement(buttonRefresh)
//        layout.addElement(buttonPage)
//    }
//}
