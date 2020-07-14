package com.ndsl.graphics.templates.ui.bar;

import com.ndsl.graphics.display.drawable.base.Drawable;
import com.ndsl.graphics.display.drawable.base.DrawableUtil;
import com.ndsl.graphics.display.drawable.non_sync.StringDrawable;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;
import com.ndsl.graphics.templates.util.StringDrawUtil;

import java.awt.*;
import java.awt.event.MouseEvent;

public class StringHangComponent implements HangBarComponent {
    public StringHangComponent(HangBar bar,String data,String hang_id,Rect r){
        this(data, hang_id, r);
        bar.addComp(this);
    }
    public Rect r;
    public String data;
    public String hang_id;
    public Drawable SD;
    public StringHangComponent(String data,String hang_id,Rect r){
        this.r=r;
        this.data=data;
        this.hang_id=hang_id;
        genSD();
    }

    public void genSD(){
        this.SD=new Drawable(new StringDrawable(data,r,"hang_"+hang_id+"_SD"));
    }

    @Override
    public Rect getSizeRect() {
        return r;
    }

    @Override
    public void onDraw(Graphics g, Rect showingRect) {
        StringDrawUtil.drawString(g,data,showingRect.left_up);
    }

    @Override
    public String getComponentID() {
        return hang_id;
    }

    @Override
    public void onHover(Pos pos) {
        data="onHover";
    }

    @Override
    public void onClick(Pos pos, int button) {
        data="onClick";
    }

    @Override
    public void non_Hover(Pos pos) {
        data="non Hover";
    }

    @Override
    public void non_Click(Pos pos, int button) {
        data="non Click";
    }
}
