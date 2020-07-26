package com.ndsl.graphics.templates.ui.inputbox;

import com.ndsl.graphics.GraphicsMain;
import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.IDrawable;
import com.ndsl.graphics.display.drawable.synced.SyncedStringDrawable;
import com.ndsl.graphics.display.key.KeyInputListener;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;
import java.awt.event.KeyEvent;

public class InputBox implements IDrawable, KeyInputListener {
    public static final Color BACKGROUND_COLOR =new Color(255, 255, 255);
    public static final Color RECT_COLOR = new Color(40, 40, 40, 255);
    public static final Color SKY_BLUE_COLOR=new Color(0, 101, 233);

    public String id;
    public Rect r;
    protected String s;
    protected boolean isFocused=false;
    protected Display d;
    protected SyncedStringDrawable ssd;
    public InputBox(Display d,String id,Rect r){this.d=d;this.id=id;this.r=r;this.s="";
        this.ssd=new SyncedStringDrawable(s,r,id+"_ssd");
        d.keyHandler.typed_listeners.add(this);
    }

    @Override
    public void onDraw(Graphics graphics, Rect rect) {
        focusCheck();
//        keyInCheck();
        drawBackGround(graphics);
        graphics.setColor(GraphicsMain.Default_Color);
        ssd.onDraw(graphics,rect);
    }

    private void keyInCheck() {
        if(!d.keyHandler.active_KeyList.isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append(s);
            for(Integer i:d.keyHandler.active_KeyList){
                sb.append(KeyEvent.getKeyText(i));
            }
            s=sb.toString();
            ssd.setText(s);
        }
    }

    private void focusCheck() {
        if(d.mouseInputHandler.isClicking){
            isFocused=r.contain(d.mouseInputHandler.now_mouse_pos);
        }
    }

    private void drawBackGround(Graphics g) {
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(r.left_up.x, r.left_up.y, r.getWidth(),r.getHeight());
        g.setColor(isFocused?SKY_BLUE_COLOR:RECT_COLOR);
        g.drawRect(r.left_up.x, r.left_up.y, r.getWidth(),r.getHeight());
    }

    @Override
    public Rect getShowingRect() {return r;}
    @Override
    public boolean isShowing(Display display) {return display.isShowing(getShowingRect());}
    @Override
    public String getID() {return id;}

    @Override
    public void onTyped(KeyEvent keyEvent) {
        Character c=keyEvent.getKeyChar();
        if(c.equals('\b')){
            backSpace();
        }else{
            s+=c;
        }
        ssd.setText(s);
    }

    private void backSpace() {
        if(s.length()==0) return;
        StringBuilder sb=new StringBuilder(s);
        sb.deleteCharAt(sb.length()-1);
        s=sb.toString();
    }

    //Nothing.
    @Override
    public void onPressed(KeyEvent keyEvent) {}
    @Override
    public void onReleased(KeyEvent keyEvent) {}
}
