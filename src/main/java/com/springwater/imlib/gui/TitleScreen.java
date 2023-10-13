package com.springwater.imlib.gui;

import com.springwater.imlib.Tags;
import com.springwater.imlib.core.ImGuiImpl;
import imgui.*;
import imgui.flag.ImGuiCol;
import net.minecraft.client.gui.GuiMainMenu;

import java.io.IOException;

public class TitleScreen extends GuiMainMenu {
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        ImGuiImpl.draw(io -> {
            ImDrawList draw = ImGui.getForegroundDrawList();
            ImFont font = ImGuiImpl.defaultFont;

            // 字体大小计算 & 绘制
            ImGui.pushFont(font);
            ImVec2 fontSizeTitle = ImGui.calcTextSize(Tags.NAME);
            ImVec2 fontSizeVersion = ImGui.calcTextSize(Tags.VERSION);

            float maxTextWidth = Math.max(fontSizeTitle.x, fontSizeVersion.x); // 计算标题和版本号的最大宽度
            float frameHeight = fontSizeTitle.y + fontSizeVersion.y + 10; // 计算绘制框的高度

            draw.addRectFilled(10, 10, 10 + maxTextWidth + 20, 10 + frameHeight, ImColor.rgba(30, 31, 34, 200), 5); // 根据文本大小确定绘制框的大小
            ImVec2 textPosition = new ImVec2(20, 15); // 文本的起始位置
            draw.addText(textPosition.x, textPosition.y, ImGui.getColorU32(ImGuiCol.Text), Tags.NAME); // 绘制标题
            textPosition.y += fontSizeTitle.y; // 更新文本位置
            draw.addText(textPosition.x, textPosition.y, ImColor.rgb("#aeb0b3"), Tags.VERSION); // 绘制版本号

            //ImGui.showDemoWindow();

            ImGui.popFont();
        });

    }

    @Override
    public void handleKeyboardInput() throws IOException {
        super.handleKeyboardInput();
        ImGuiImpl.handleKey();
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        ImGuiImpl.handleMouse();
    }
}
