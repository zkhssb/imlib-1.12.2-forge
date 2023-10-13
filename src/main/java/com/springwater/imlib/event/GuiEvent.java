package com.springwater.imlib.event;

import com.springwater.imlib.gui.TitleScreen;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiEvent {

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        if (event.getGui() instanceof GuiMainMenu) {
            event.setGui(new TitleScreen());
        }

        //ImLib.LOGGER.info("Gui Open:{}", event.getGui().getClass().getName());

    }
}
