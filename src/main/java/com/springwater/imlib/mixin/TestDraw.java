package com.springwater.imlib.mixin;

import com.springwater.imlib.core.ImGuiImpl;
import imgui.ImGui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public class TestDraw extends GuiScreen {
    @Inject(method = "drawScreen", at=@At("RETURN"))
    private void injectedDrawScreen(int mouseX, int mouseY, float partialTicks, CallbackInfo ci){
        ImGuiImpl.draw(io -> {
            ImGui.pushFont(ImGuiImpl.defaultFont);
            ImGui.showDemoWindow();
            ImGui.popFont();

            ImGui.begin("卧槽!! ImGui!!");
            ImGui.text("卧槽!! 标签!!");
            ImGui.button("卧槽!! 按钮!!");
            ImGui.end();

            ImGui.getForegroundDrawList().addText(0,0,-1, "卧槽!!!!!!!");
        });
    }
}
