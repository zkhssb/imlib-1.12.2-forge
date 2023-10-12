package com.springwater.imlib;

import com.springwater.imlib.core.ImGuiImpl;
import com.springwater.imlib.event.GuiEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MOD_ID, version = Tags.VERSION)
public class ImLib{
    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_ID);

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ImGuiImpl.create();
        MinecraftForge.EVENT_BUS.register(new GuiEvent());
    }
}
