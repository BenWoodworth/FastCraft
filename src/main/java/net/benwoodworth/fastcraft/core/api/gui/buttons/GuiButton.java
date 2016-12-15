package net.benwoodworth.fastcraft.core.api.gui.buttons;

import net.benwoodworth.fastcraft.core.api.dependencies.event.FcEventListener;
import net.benwoodworth.fastcraft.core.api.gui.events.GuiButtonClickEvent;
import net.benwoodworth.fastcraft.core.api.gui.layouts.GuiLayout;
import org.jetbrains.annotations.NotNull;

/**
 * A button in a GUI.
 */
public class GuiButton<TItem> extends GuiLayout<TItem> {

    @NotNull
    private final FcEventListener<GuiButtonClickEvent<TItem>> clickListener = new FcEventListener<>();

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    /**
     * Get this button's click listener.
     *
     * @return Returns this button's click listener.
     */
    @NotNull
    public final FcEventListener<GuiButtonClickEvent<TItem>> getClickListener() {
        return clickListener;
    }
}