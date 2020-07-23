package com.ndsl.graphics.templates.main;

import com.ndsl.al.bun133.clip.FileNotSupportedException;
import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.base.Drawable;
import com.ndsl.graphics.display.drawable.non_sync.RectDrawable;
import com.ndsl.graphics.display.drawable.non_sync.StringDrawable;
import com.ndsl.graphics.display.drawable.synced.SyncedRectDrawable;
import com.ndsl.graphics.display.drawable.synced.SyncedStringDrawable;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;
import com.ndsl.graphics.templates.audio.AudioPlayerBar;
import com.ndsl.graphics.templates.ui.bar.HangBar;
import com.ndsl.graphics.templates.ui.bar.StringHangComponent;
import com.ndsl.graphics.templates.util.Easing;
import com.ndsl.graphics.templates.util.FontCache;
import com.ndsl.graphics.templates.util.StringDrawUtil;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class TemplateMain {
    public static void main(String[] args) throws IOException, FileNotSupportedException {
//        new TemplateMain().main_test();
//        new TemplateMain().getFontTest();
        new TemplateMain().EasingTest();
    }

    public void main_test(){
        Display display=new Display("NDSL/Templates",3,new Rect(100,100,1000,1000));
        display.setDebugMode(true);
        HangBar hangBar=new HangBar("test_hang",new Rect(100,100,300,200),display);
        hangBar.setBackGroundColor(new Color(64, 59, 59, 255));
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

    public void audio_bar_test() throws IOException, FileNotSupportedException {
        Display display=new Display("NDSL/Templates",3,new Rect(100,100,1000,1000));
        AudioPlayerBar player=new AudioPlayerBar(display,new File("se1.wav"),new Rect(100,100,500,150),"player");
        display.addDrawable(new Drawable(player));
        player.clip.player.setVolume(-30f);
        player.clip.player.setLoop(true);
        player.clip.player.start();
        while(true){
            if(display.limiter.onUpdate()) display.update();
        }
    }

    public void fileChooser(){

    }

    public void EasingTest(){
        Display display = new Display("NDSL/Templates",3,new Rect(100,100,1020,500));
        Color c=new Color(53, 191, 255, 221);


        SyncedStringDrawable EaseInSine_s=new SyncedStringDrawable("EaseInSine",new Rect(10,120,20,140),"EaseInSine_s");
        SyncedRectDrawable EaseInSine = new SyncedRectDrawable(new Rect(0,140,20,160),c,"EaseInSine");
        display.addDrawable(new Drawable(EaseInSine));
        display.addDrawable(new Drawable(EaseInSine_s));

        SyncedStringDrawable EaseOutSine_s=new SyncedStringDrawable("EaseOutSine",new Rect(10,160,20,180),"EaseOutSine_s");
        SyncedRectDrawable EaseOutSine = new SyncedRectDrawable(new Rect(0,180,20,200),c,"EaseOutSine");
        display.addDrawable(new Drawable(EaseOutSine));
        display.addDrawable(new Drawable(EaseOutSine_s));
        double count=0.0;
        while(true){
            EaseInSine.setRect(new Rect(0,140,20,160).shift((int) (Easing.EaseInSine(count)*900),0));
            EaseOutSine.setRect(new Rect(0,180,20,200).shift((int) (Easing.EaseOutSine(count)*900),0));
            if(display.limiter.onUpdate()) display.update();
            count=display.limiter.FPSCount*0.005;
            count=count%1;
        }
    }
}
