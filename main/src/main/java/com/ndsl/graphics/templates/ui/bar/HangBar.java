package com.ndsl.graphics.templates.ui.bar;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.IDrawable;
import com.ndsl.graphics.display.drawable.non_sync.ui.MouseInputListener;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
        Pos c_p=new Pos(r.left_up.x,r.left_up.y);
        for (HangBarComponent component : components){
            component.onDraw(graphics,new Rect(c_p,new Pos(c_p.x+component.getSizeRect().getWidth(),c_p.y+component.getSizeRect().getHeight())));
            c_p.shift(component.getSizeRect().getWidth(),0);
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
}
