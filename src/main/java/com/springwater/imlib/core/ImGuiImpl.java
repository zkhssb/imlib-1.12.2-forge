package com.springwater.imlib.core;

import com.springwater.imlib.ImLib;
import com.springwater.imlib.impl.ImGuiImplDisplay;
import com.springwater.imlib.impl.ImGuiImplGl2;
import imgui.*;
import imgui.extension.implot.ImPlot;


import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiConfigFlags;
import net.minecraft.client.renderer.GlStateManager;
import org.apache.commons.compress.utils.IOUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.Objects;

public class ImGuiImpl {
    private final static ImGuiImplDisplay imGuiImplDisplay = new ImGuiImplDisplay();
    private final static ImGuiImplGl2 imGuiImplGl2 = new ImGuiImplGl2();

    public static ImFont defaultFont;
    public static ImFont font21;

    public static void create() {
        ImGui.createContext();
        ImPlot.createContext();

        final ImGuiIO data = ImGui.getIO();
        data.setIniFilename("imguilib.ini");
        data.setFontGlobalScale(1F);

        final ImFontAtlas fonts = data.getFonts();
        final ImFontGlyphRangesBuilder rangesBuilder = new ImFontGlyphRangesBuilder();
        //rangesBuilder.addRanges(fonts.getGlyphRangesCyrillic());
        //rangesBuilder.addRanges(fonts.getGlyphRangesDefault());
        rangesBuilder.addRanges(fonts.getGlyphRangesChineseFull());
        final short[] glyphRanges = GetGlyphRangesChineseFull();

        styleDark(ImGui.getStyle());

        final ImFontConfig basicConfig = new ImFontConfig();
        basicConfig.setGlyphRanges(glyphRanges);
        basicConfig.setName("alibaba 18px");
        try {
            defaultFont = fonts.addFontFromMemoryTTF(
                    IOUtils.toByteArray(Objects.requireNonNull(ImLib.class.getResourceAsStream("/assets/imlib/fonts/Alibaba-PuHuiTi-Regular.ttf"))),
                    18,
                    basicConfig,
                    glyphRanges
            );

            font21 = fonts.addFontFromMemoryTTF(
                    IOUtils.toByteArray(Objects.requireNonNull(ImLib.class.getResourceAsStream("/assets/imlib/fonts/Alibaba-PuHuiTi-Regular.ttf"))),
                    21,
                    basicConfig,
                    glyphRanges
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        fonts.build();
        basicConfig.destroy();

        //data.setConfigFlags(ImGuiConfigFlags.DockingEnable);
        // In case you want to enable Viewports on Windows, you have to do this instead of the above line:
        data.setConfigFlags(ImGuiConfigFlags.DockingEnable | ImGuiConfigFlags.ViewportsEnable);

        imGuiImplDisplay.init();
        imGuiImplGl2.init();
    }

    private static void styleDark(ImGuiStyle style) {
        style.setAlpha(0.95f);
        style.setDisabledAlpha(0.6000000238418579f);
        style.setWindowPadding(8.0f, 8.0f);
        style.setWindowRounding(4.0f);
        style.setWindowBorderSize(0.0f);
        style.setWindowMinSize(32.0f, 32.0f);
        style.setWindowTitleAlign(0.5f, 0.5f);
        style.setWindowMenuButtonPosition(1);
        style.setChildRounding(0.0f);
        style.setChildBorderSize(1.0f);
        style.setPopupRounding(4.0f);
        style.setPopupBorderSize(1.0f);
        style.setFramePadding(8.0f, 6.0f);
        style.setFrameRounding(5.5f);
        style.setFrameBorderSize(0.0f);
        style.setItemSpacing(8.0f, 4.0f);
        style.setItemInnerSpacing(4.0f, 4.0f);
        style.setCellPadding(4.0f, 2.0f);
        style.setIndentSpacing(21.0f);
        style.setColumnsMinSpacing(6.0f);
        style.setScrollbarSize(11.0f);
        style.setScrollbarRounding(2.5f);
        style.setGrabMinSize(10.0f);
        style.setGrabRounding(2.0f);
        style.setTabRounding(3.5f);
        style.setTabBorderSize(0.0f);
        style.setTabMinWidthForCloseButton(0.0f);
        style.setColorButtonPosition(2);
        style.setButtonTextAlign(0.5f, 0.5f);
        style.setSelectableTextAlign(0.0f, 0.0f);
        style.setColor(ImGuiCol.Text, 1.0f, 1.0f, 1.0f, 1.0f);
        style.setColor(ImGuiCol.TextDisabled, 0.5921568870544434f, 0.5921568870544434f, 0.5921568870544434f, 1.0f);
        style.setColor(ImGuiCol.WindowBg, 0.1450980454683304f, 0.1450980454683304f, 0.1490196138620377f, 1.0f);
        style.setColor(ImGuiCol.ChildBg, 0.1450980454683304f, 0.1450980454683304f, 0.1490196138620377f, 1.0f);
        style.setColor(ImGuiCol.PopupBg, 0.1450980454683304f, 0.1450980454683304f, 0.1490196138620377f, 1.0f);
        style.setColor(ImGuiCol.Border, 0.3058823645114899f, 0.3058823645114899f, 0.3058823645114899f, 1.0f);
        style.setColor(ImGuiCol.BorderShadow, 0.3058823645114899f, 0.3058823645114899f, 0.3058823645114899f, 1.0f);
        style.setColor(ImGuiCol.FrameBg, 0.2000000029802322f, 0.2000000029802322f, 0.2156862765550613f, 1.0f);
        style.setColor(ImGuiCol.FrameBgHovered, 0.2558823645114899f, 0.2558823645114899f, 0.2558823645114899f, 1.0f);
        style.setColor(ImGuiCol.FrameBgActive, 0.3000000029802322f, 0.3000000029802322f, 0.3156862765550613f, 1.0f);
        style.setColor(ImGuiCol.TitleBg, 0.1450980454683304f, 0.1450980454683304f, 0.1490196138620377f, 1.0f);
        style.setColor(ImGuiCol.TitleBgActive, 0.1450980454683304f, 0.1450980454683304f, 0.1490196138620377f, 1.0f);
        style.setColor(ImGuiCol.TitleBgCollapsed, 0.1450980454683304f, 0.1450980454683304f, 0.1490196138620377f, 1.0f);
        style.setColor(ImGuiCol.MenuBarBg, 0.2000000029802322f, 0.2000000029802322f, 0.2156862765550613f, 1.0f);
        style.setColor(ImGuiCol.ScrollbarBg, 0.2000000029802322f, 0.2000000029802322f, 0.2156862765550613f, 1.0f);
        style.setColor(ImGuiCol.ScrollbarGrab, 0.321568638086319f, 0.321568638086319f, 0.3333333432674408f, 1.0f);
        style.setColor(ImGuiCol.ScrollbarGrabHovered, 0.3529411852359772f, 0.3529411852359772f, 0.3725490272045135f, 1.0f);
        style.setColor(ImGuiCol.ScrollbarGrabActive, 0.3529411852359772f, 0.3529411852359772f, 0.3725490272045135f, 1.0f);
        style.setColor(ImGuiCol.CheckMark, 0.0f, 0.4666666686534882f, 0.7843137383460999f, 1.0f);
        style.setColor(ImGuiCol.SliderGrab, 0.1137254908680916f, 0.5921568870544434f, 0.9254902005195618f, 1.0f);
        style.setColor(ImGuiCol.Button, 0.2000000029802322f, 0.2000000029802322f, 0.2156862765550613f, 1.0f);
        style.setColor(ImGuiCol.ButtonHovered, 0.1137254908680916f, 0.5921568870544434f, 0.9254902005195618f, 1.0f);
        style.setColor(ImGuiCol.ButtonActive, 0.1137254908680916f, 0.5921568870544434f, 0.9254902005195618f, 1.0f);
        style.setColor(ImGuiCol.Header, 0.2000000029802322f, 0.2000000029802322f, 0.2156862765550613f, 1.0f);
        style.setColor(ImGuiCol.HeaderHovered, 0.1137254908680916f, 0.5921568870544434f, 0.9254902005195618f, 1.0f);
        style.setColor(ImGuiCol.HeaderActive, 0.0f, 0.4666666686534882f, 0.7843137383460999f, 1.0f);
        style.setColor(ImGuiCol.Separator, 0.3058823645114899f, 0.3058823645114899f, 0.3058823645114899f, 1.0f);
        style.setColor(ImGuiCol.SeparatorHovered, 0.3058823645114899f, 0.3058823645114899f, 0.3058823645114899f, 1.0f);
        style.setColor(ImGuiCol.SeparatorActive, 0.3058823645114899f, 0.3058823645114899f, 0.3058823645114899f, 1.0f);
        style.setColor(ImGuiCol.ResizeGrip, 0.1450980454683304f, 0.1450980454683304f, 0.1490196138620377f, 1.0f);
        style.setColor(ImGuiCol.ResizeGripHovered, 0.2000000029802322f, 0.2000000029802322f, 0.2156862765550613f, 1.0f);
        style.setColor(ImGuiCol.ResizeGripActive, 0.321568638086319f, 0.321568638086319f, 0.3333333432674408f, 1.0f);
        style.setColor(ImGuiCol.Tab, 0.1450980454683304f, 0.1450980454683304f, 0.1490196138620377f, 1.0f);
        style.setColor(ImGuiCol.TabHovered, 0.1137254908680916f, 0.5921568870544434f, 0.9254902005195618f, 1.0f);
        style.setColor(ImGuiCol.TabActive, 0.0f, 0.4666666686534882f, 0.7843137383460999f, 1.0f);
        style.setColor(ImGuiCol.TabUnfocused, 0.1450980454683304f, 0.1450980454683304f, 0.1490196138620377f, 1.0f);
        style.setColor(ImGuiCol.TabUnfocusedActive, 0.0f, 0.4666666686534882f, 0.7843137383460999f, 1.0f);
        style.setColor(ImGuiCol.TextSelectedBg, 0.0f, 0.4666666686534882f, 0.7843137383460999f, 1.0f);
        style.setColor(ImGuiCol.DragDropTarget, 0.1450980454683304f, 0.1450980454683304f, 0.1490196138620377f, 1.0f);
        style.setColor(ImGuiCol.NavHighlight, 0.1450980454683304f, 0.1450980454683304f, 0.1490196138620377f, 1.0f);
        style.setColor(ImGuiCol.NavWindowingHighlight, 1.0f, 1.0f, 1.0f, 0.699999988079071f);
        style.setColor(ImGuiCol.NavWindowingDimBg, 0.800000011920929f, 0.800000011920929f, 0.800000011920929f, 0.2000000029802322f);
        style.setColor(ImGuiCol.ModalWindowDimBg, 0.1450980454683304f, 0.1450980454683304f, 0.1490196138620377f, 1.0f);
    }

    public static void draw(final RenderInterface runnable) {
        imGuiImplDisplay.newFrame(); // Handle keyboard and mouse interactions
        ImGui.newFrame();
        runnable.render(ImGui.getIO());
        ImGui.render();

        imGuiImplGl2.newFrame();
        imGuiImplGl2.renderDrawData(ImGui.getDrawData());

        if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            GlStateManager.pushAttrib();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            GlStateManager.popAttrib();
        }
    }

    public static short[] GetGlyphRangesChineseFull() {
        char[] ranges = {
                0x0020, 0x00FF,
                0x2000, 0x206F,
                0x3000, 0x30FF,
                0x31F0, 0x31FF,
                0xFF00, 0xFFEF,
                0xFFFD, 0xFFFD,
                0x4e00, 0x9FAF,
                0
        };

        short[] convertedRanges = new short[ranges.length];
        for (int i = 0; i < ranges.length; i++) {
            convertedRanges[i] = (short) ranges[i];
        }

        return convertedRanges;
    }

    public static int fromBufferedImage(BufferedImage image) {
        final int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        final ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                final int pixel = pixels[y * image.getWidth() + x];

                buffer.put((byte) ((pixel >> 16) & 0xFF));
                buffer.put((byte) ((pixel >> 8) & 0xFF));
                buffer.put((byte) (pixel & 0xFF));
                buffer.put((byte) ((pixel >> 24) & 0xFF));
            }
        }

        buffer.flip();

        final int texture = GlStateManager.generateTexture();
        GlStateManager.bindTexture(texture);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

        return texture;
    }

    public static void destroyTexture(int textureId) {
        GlStateManager.deleteTexture(textureId);
    }
}