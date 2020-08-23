package com.ndsl.graphics.templates.ui.bar;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Deprecated
public class BaseHangComponent extends StringHangComponent{
    public HangList hangs;
    public BaseHangComponent(HangBar bar, String data, String hang_id, Pos left_up, Display display,HangList hangs) {
        super(bar, data, hang_id, left_up, display);
        this.hangs=hangs;
    }

    public BaseHangComponent(String data, String hang_id, Pos left_up, Display display,HangList hangs) {
        super(data, hang_id, left_up, display);
        this.hangs=hangs;
    }

    @Override
    public void onHover(Pos pos) {
    }

    @Override
    public void onClick(Pos pos, int button) {
        hangs.onClick(r, pos, button);
        isAllShown=true;
    }

    @Override
    public void non_Hover(Pos pos) {
    }

    @Override
    public void non_Click(Pos pos, int button) {
    }

    boolean isAllShown=false;

    @Override
    public void onDraw(Graphics g, Rect showingRect) {
        super.onDraw(g, showingRect);
        if(isAllShown){
            hangs.draw(g,r);
        }
    }

    @Override
    public Rect getSizeRect(Graphics g) {
        if(!isAllShown){
            return super.getSizeRect(g);
        }
        Pos left_up=new Pos(this.r.x,this.r.y);
        int max_x=0;
        int max_y=0;
        for(StringHangComponent component:hangs.component){
            Rect r=component.getSizeRect(g).shift(left_up.x,left_up.y);
            if(r.right_down.x>max_x){
                max_x=r.right_down.x;
            }
            if(r.right_down.y>max_y){
                max_y=r.right_down.y;
            }
        }
        return new Rect(left_up.shift(-left_up.x,-left_up.y),new Pos(max_x,max_y).shift(-left_up.x,-left_up.y));
    }

    public class HangList{
        public List<StringHangComponent> component=new ArrayList();
        public HangList(StringHangComponent... data){
            component= Arrays.asList(data);
        }

        public void draw(Graphics g, Pos left_up){
            Pos c_p=new Pos(left_up.x,left_up.y);
            for(StringHangComponent c:component){
                c.onDraw(g,new Rect(c_p));
                c_p.shift(c.getSizeRect(g).getWidth(),0);
            }
        }

        public void onClick(Pos left_up,Pos pos,int button){
            for(StringHangComponent c:component){
                Rect r=c.getSizeRect().shift(left_up.x,left_up.y);
                if(r.contain(pos)){
                    c.onClick(pos,button);
                }
            }
        }
    }
}
