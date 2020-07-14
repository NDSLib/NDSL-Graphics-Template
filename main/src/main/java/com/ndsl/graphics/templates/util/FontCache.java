package com.ndsl.graphics.templates.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FontCache {
    public static final FontCache INSTANCE = new FontCache();

    public static final String Default_Font_String = "Meiryo UI";
    public static final int Default_Font_Size = 12;

    public FontCache(){
        getFont(Default_Font_String,Default_Font_Size);
    }

    public Font getDefaultFont(){
        return getFont(Default_Font_String,Default_Font_Size);
    }

    public Font genFont(String font_name,int font_size){
        return new Font(font_name, 0, font_size);
    }

    public Font genFont(int font_size){
        return genFont(Default_Font_String,font_size);
    }

    public Map<String,Map<Integer,Font>> font_cache = new HashMap<String, Map<Integer, Font>>();

    public Font getFont(String font_name,Integer size) {
        if (font_cache.containsKey(font_name)){
            if(font_cache.get(font_name).containsKey(size)){
                return font_cache.get(font_name).get(size);
            }
        }
        setCache(genFont(font_name, size));
        return font_cache.get(font_name).get(size);
    }

    public void setCache(Font font){
        setCache(font,font.getSize());
    }

    public void setCache(Font font,int size){
        setCache(font,font.getName(),font.getSize());
    }

    public void setCache(Font font,String font_name,int size){
        if (!this.font_cache.containsKey(font_name)) {
            this.font_cache.put(font_name, new HashMap<Integer, Font>());
        }
        this.font_cache.get(font_name).put(size,font);
    }
}