package com.ndsl.graphics.templates.ui.bar;

import com.ndsl.graphics.GraphicsMain;
import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.IDrawable;
import com.ndsl.graphics.display.drawable.non_sync.ui.MouseInputListener;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class HangBar implements IDrawable {
    public String id;
    public Rect r;
    public HangBar(String id,Rect rect,Display display){
        this.id=id;
        this.r=rect;
        this.listener=new MouseInputListener(display,r);
    }

    public List<HangBarComponent> components=new ArrayList<HangBarComponent>();
    public HangBar addComp(HangBarComponent component){
        components.add(component);
        return this;
    }

    public HangBar removeComp(String id){
        for (int i = 0; i < components.size() - 1; i++) {
            if(components.get(i).getComponentID().equals(id)){
                components.remove(i);
                return this;
            }
        }
        return this;
    }

    public HangBar removeComp(HangBarComponent component){return this.removeComp(component.getComponentID());}

    @Override
    public void onDraw(Graphics graphics, Rect rect) {
        MouseHandle(graphics);
        drawBackGround(graphics,rect);
        Pos c_p=new Pos(r.left_up.x,r.left_up.y);
        for (HangBarComponent component : components){
            graphics.setColor(GraphicsMain.Default_Color);
            component.onDraw(graphics,new Rect(c_p,new Pos(c_p.x+component.getSizeRect(graphics).getWidth(),c_p.y+component.getSizeRect(graphics).getHeight())));
            c_p.shift(component.getSizeRect(graphics).getWidth(),0);
        }
    }

    @Override
    public Rect getShowingRect() {
        return r;
    }

    @Override
    public boolean isShowing(Display display) {
        return display.isShowing(getShowingRect());
    }

    @Override
    public String getID() {
        return id;
    }

    public MouseInputListener listener;

    public void MouseHandle(Graphics g){
        Pos c_p=new Pos(r.left_up.x,r.left_up.y);
        for (HangBarComponent component : components){
            boolean isProcessed = false;
            if (new Rect(c_p,new Pos(c_p.x+component.getSizeRect(g).getWidth(),c_p.y+component.getSizeRect(g).getHeight())).contain(listener.handler.now_mouse_pos)){
                //Mouse Over
                if(listener.isClicking()){
                    component.onClick(listener.handler.now_mouse_pos,listener.handler.Current_Mouse_Button);
                } else{
                    component.onHover(listener.handler.now_mouse_pos);
                }
                isProcessed=true;
            }
            if(!isProcessed){
                if(listener.handler.isClicking){
                    component.non_Click(listener.handler.now_mouse_pos,listener.handler.Current_Mouse_Button);
                } else{
                    component.non_Hover(listener.handler.now_mouse_pos);
                }
            }
            c_p.shift(component.getSizeRect(g).getWidth(),0);
        }
    }

    public Color backGroundColor=null;

    public void drawBackGround(Graphics g,Rect r){
        if(backGroundColor==null) return;
        g.setColor(backGroundColor);
        g.fillRect(r.left_up.x,r.left_up.y,r.getWidth(),r.getHeight());
    }

    public HangBar setBackGroundColor(Color r){
        this.backGroundColor=r;
        return this;
    }
}
