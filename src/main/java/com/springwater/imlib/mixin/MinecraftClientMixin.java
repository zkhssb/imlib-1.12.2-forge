package com.springwater.imlib.mixin;

import com.springwater.imlib.core.ImGuiImpl;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "createDisplay", at=@At("RETURN"))
    private void injectedCreateDisplay(CallbackInfo ci){
        Display.setTitle("Minecraft 1.12.2 (ImGuiLib)");
        ImGuiImpl.create();
    }


}
