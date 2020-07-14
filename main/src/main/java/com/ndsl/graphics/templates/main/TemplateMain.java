package com.ndsl.graphics.templates.main;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.base.Drawable;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;
import com.ndsl.graphics.templates.ui.bar.HangBar;
import com.ndsl.graphics.templates.ui.bar.StringHangComponent;
import com.ndsl.graphics.templates.util.FontCache;
import com.ndsl.graphics.templates.util.StringDrawUtil;

import java.awt.*;
import java.util.Random;

public class TemplateMain {
    public static void main(String[] args) {
        new TemplateMain().main_test();
//        new TemplateMain().getFontTest();
    }


    public void main_test(){
        Display display=new Display("NDSL/Templates",3,new Rect(100,100,1000,1000));
        display.setDebugMode(true);
        HangBar hangBar=new HangBar("test_hang",new Rect(100,100,300,200),display);
        hangBar.setBackGroundColor(new Color(255, 0, 0, 255));
        StringHangComponent SHC=new StringHangComponent(hangBar,"data","hang_id",new Pos(0,0),display);
        display.addDrawable(new Drawable(hangBar));
        while(true){
            if(display.limiter.onUpdate()) display.update();
        }
    }
    //DO NOT USE Cache!!!!!!!
    //It's not a true Cache!
    public void getFontTest(){
        Random r=new Random();
        long startTime=System.currentTimeMillis();
        for (long i=0;i<10000000;i++){
            FontCache.INSTANCE.getFont(r.nextInt(100));
        }
        System.out.println("Cache:"+(System.currentTimeMillis()-startTime));
        startTime=System.currentTimeMillis();
        for(long i=0;i<10000000;i++){
            new Font(FontCache.Default_Font_String,0,r.nextInt(100));
        }
        System.out.println("NonCache:"+(System.currentTimeMillis()-startTime));
    }
}
