package com.ndsl.graphics.templates.audio;

import com.ndsl.al.bun133.clip.AudioClip;
import com.ndsl.al.bun133.clip.FileNotSupportedException;
import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.IDrawable;
import com.ndsl.graphics.display.drawable.img.GImage;
import com.ndsl.graphics.display.mouse.MouseInputHandler;
import com.ndsl.graphics.pos.Rect;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayerBar implements IDrawable {
    public Image STOPPING_IMAGE_FILE;
    public Image PLAYING_IMAGE_FILE;
    public Image SPEAKER_IMAGE_FILE;
    public GImage STOPPING_IMAGE_FILE_GI;
    public GImage PLAYING_IMAGE_FILE_GI;
    public GImage SPEAKER_IMAGE_FILE_GI;
    public Rect rect;
    @NotNull
    public String id;
    public Display display;
    public AudioPlayerBar(Display display,File file,Rect r,String id)
            throws FileNotSupportedException, IOException {
        this(display,new AudioClip(file),r,id);
    }

    public AudioPlayerBar(Display display,AudioClip clip, Rect r, @NotNull String id) throws IOException {
        STOPPING_IMAGE_FILE_GI=GImage.get(new File("main\\src\\main\\resources\\stop_button.gif"));
        PLAYING_IMAGE_FILE_GI=GImage.get(new File("main\\src\\main\\resources\\play_button.gif"));
        SPEAKER_IMAGE_FILE_GI=GImage.get(new File("main\\src\\main\\resources\\speaker_button.gif"));
        double zoom_rate=((double)r.getHeight())/100d;
        STOPPING_IMAGE_FILE=STOPPING_IMAGE_FILE_GI.zoom(zoom_rate).export();
        PLAYING_IMAGE_FILE=PLAYING_IMAGE_FILE_GI.zoom(zoom_rate).export();
        SPEAKER_IMAGE_FILE=SPEAKER_IMAGE_FILE_GI.zoom(zoom_rate).export();
        this.clip=clip;
        this.rect=r;
        this.id=id;
        this.display=display;
        this.PlayButton=new PlayButton(PLAYING_IMAGE_FILE,STOPPING_IMAGE_FILE,new Rect(rect.left_up.x,rect.left_up.y,r.getHeight(),r.getHeight()),id);
        this.BackGround=new BackGround(rect,new Color(130, 130, 130),id);
        this.SeekBar=new SeekBar(display,new Rect(rect.left_up.x+r.getHeight(),rect.left_up.y,rect.right_down.x-(rect.getWidth()/3),rect.right_down.y),id,new Color(130, 130, 130),new Color(255, 255, 255));
        this.AudioBar=new AudioBar(SPEAKER_IMAGE_FILE,new Rect(rect.left_up.x+(rect.getWidth()*2/3),rect.left_up.y,rect.right_down.x,rect.right_down.y),id,new Color(130, 130, 130),new Color(255, 255, 255));
    }

    public AudioClip clip;

    @Override
    public void onDraw(Graphics graphics, Rect rect) {
        BackGround.onDraw(graphics,rect);
        PlayButton.onDraw(graphics);
        SeekBar.onDraw(graphics);
        AudioBar.onDraw(graphics);
    }

    @Override
    public Rect getShowingRect() {
        return rect;
    }

    @Override
    public boolean isShowing(Display display) {
        return display.isShowing(getShowingRect());
    }

    @Override
    public String getID() {
        return id;
    }

    public PlayButton PlayButton;
    private class PlayButton implements IDrawable{
        public Image playing_image;
        public Image stopping_image;
        public Rect button_rect;
        public String id_prefix;
        public PlayButton(Image playing_image,Image stopping_image,Rect button_rect,String id_prefix){
            this.playing_image=playing_image;
            this.stopping_image=stopping_image;
            this.button_rect=button_rect;
            this.id_prefix=id_prefix;
        }


        public void onDraw(Graphics g){
            onDraw(g,button_rect);
        }
        @Override
        public void onDraw(Graphics graphics, Rect rect) {
            if (AudioPlayerBar.this.clip.player.isPlaying){
                graphics.drawImage(playing_image,rect.left_up.x,rect.left_up.y,null);
            }else{
                graphics.drawImage(stopping_image,rect.left_up.x,rect.left_up.y,null);
            }
        }

        @Override
        public Rect getShowingRect() {
            return button_rect;
        }

        @Override
        public boolean isShowing(Display display) {
            return display.isShowing(getShowingRect());
        }

        String id;
        @Override
        public String getID() {
            if(id==null) id=id_prefix+"_play_button";
            return id;
        }
    }

    public BackGround BackGround;
    public class BackGround implements IDrawable{
        public Rect BackGround_Rect;
        public Color BackGround_Color;
        public String id_prefix;
        public BackGround(Rect r,Color c,String id_prefix){
            this.BackGround_Rect=r;
            this.BackGround_Color=c;
            this.id_prefix=id_prefix;
        }

        @Override
        public void onDraw(Graphics graphics, Rect rect) {
            graphics.setColor(BackGround_Color);
            graphics.fillRect(BackGround_Rect.left_up.x,BackGround_Rect.left_up.y,BackGround_Rect.getWidth(),BackGround_Rect.getHeight());
        }

        @Override
        public Rect getShowingRect() {
            return rect;
        }

        @Override
        public boolean isShowing(Display display) {
            return display.isShowing(getShowingRect());
        }

        String id=null;
        @Override
        public String getID() {
            if(id==null) id=id_prefix+"_background";
            return id;
        }
    }

    public SeekBar SeekBar;
    public class SeekBar implements IDrawable{
        public MouseInputHandler mouse_in;
        public AudioClip clip;
        public Rect r;
        public String id_prefix;
        public Color backColor;
        public Color frontColor;
        public SeekBar(Display display,Rect r,String id_prefix,Color backColor,Color frontColor){
            mouse_in = new MouseInputHandler(display);
            this.clip=AudioPlayerBar.this.clip;
            this.r=r;
            this.backColor=backColor;
            this.frontColor=frontColor;
            this.id_prefix=id_prefix;
        }
        public void onDraw(Graphics g){
            onDraw(g,r);
        }
        @Override
        public void onDraw(Graphics graphics, Rect rect) {
            this.MouseHandler.handle();
            graphics.setColor(backColor);
            graphics.fillRect(rect.left_up.x, rect.left_up.y,rect.getWidth(),rect.getHeight());
            graphics.setColor(frontColor);
            Rect front_rect=getPlayedRect();
            graphics.fillRect(front_rect.left_up.x, front_rect.left_up.y, front_rect.getWidth(), front_rect.getHeight());
        }

        @Override
        public Rect getShowingRect() {
            return r;
        }

        @Override
        public boolean isShowing(Display display) {
            return display.isShowing(getShowingRect());
        }

        String id=null;
        @Override
        public String getID() {
            if(id==null) id=id_prefix+"_seekBar";
            return id;
        }

        private Rect getPlayedRect(){
            return new Rect(rect.left_up.x,rect.left_up.y, Math.toIntExact(rect.getWidth() * playedRate()),rect.getHeight());
        }

        private long playedRate(){
            return clip.player.getNowPos()/clip.player.length;
        }

        public MouseHandler MouseHandler = new MouseHandler(mouse_in);
        private class MouseHandler{
            public MouseInputHandler handler;
            public MouseHandler(MouseInputHandler handler){
                this.handler = handler;
            }

            public void handle(){
                //TODO
            }
        }
    }

    public AudioBar AudioBar;
    public class AudioBar implements IDrawable{
        public Rect r;
        public String id_prefix;
        public Image audioImage;
        public Color backColor;
        public Color frontColor;
        public AudioBar(Image audioImage,Rect r,String id_prefix,Color backColor,Color frontColor){
            this.r=r;
            this.id_prefix=id_prefix;
            this.audioImage=audioImage;
            this.backColor=backColor;
            this.frontColor=frontColor;
        }

        public void onDraw(Graphics g){
            onDraw(g,r);
        }

        @Override
        public void onDraw(Graphics graphics, Rect rect) {
            graphics.drawImage(audioImage,rect.left_up.x,rect.left_up.y,null);
            graphics.setColor(backColor);
            graphics.fillRect(rect.left_up.x+r.getHeight(), rect.left_up.y,rect.getWidth()-r.getHeight(),rect.getHeight());
            graphics.setColor(frontColor);
            graphics.fillRect(rect.left_up.x+r.getHeight(), rect.left_up.y, (int) ((rect.getWidth()-r.getHeight())*getAudioRate()),rect.getHeight());
        }

        @Override
        public Rect getShowingRect() {
            return r;
        }

        @Override
        public boolean isShowing(Display display) {
            return display.isShowing(getShowingRect());
        }

        String id=null;
        @Override
        public String getID() {
            if(id==null) id=id_prefix+"_audioBar";
            return id;
        }

        private float getAudioRate(){
            return AudioPlayerBar.this.clip.player.GAIN_Control.getValue()/AudioPlayerBar.this.clip.player.GAIN_Control.getMaximum();
        }
    }
}
