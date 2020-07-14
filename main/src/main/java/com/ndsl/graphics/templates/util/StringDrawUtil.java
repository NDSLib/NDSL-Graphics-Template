package com.ndsl.graphics.templates.util;

import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class StringDrawUtil {
    public static void drawString(Graphics graphics, String string, Pos left_up){
        drawString(graphics,FontCache.INSTANCE.getDefaultFont(),string,left_up);
    }

    public static void drawString(Graphics graphics,int size,String string,Pos left_up){
        drawString(graphics,FontCache.INSTANCE.getFont(FontCache.Default_Font_String,size),string,left_up);
    }

    public static void drawString(Graphics graphics,String font_name,int size,String string,Pos left_up){
        drawString(graphics,FontCache.INSTANCE.getFont(font_name,size),string,left_up);
    }

    public static void drawString(Graphics graphics,Font f,String string, Pos left_up){
        graphics.setFont(f);
        graphics.drawString(string,left_up.x,left_up.y+f.getSize());
    }

    public static Rect getStringBounds(Graphics g, Font font, String s){
        return new Rect(font.getStringBounds(s,g.getFontMetrics(font).getFontRenderContext()));
    }

    public static int getStringWidth(Graphics g,Font f,String s){
        return getStringBounds(g,f,s).getWidth();
    }

    public static int getStringHeight(Graphics g,Font f,String s){
        return getStringBounds(g, f, s).getHeight();
    }
}
