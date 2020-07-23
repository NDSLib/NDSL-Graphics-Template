package com.ndsl.graphics.templates.ui.bar;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.base.Drawable;
import com.ndsl.graphics.display.drawable.non_sync.StringDrawable;
import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;
import com.ndsl.graphics.templates.util.StringDrawUtil;

import java.awt.*;

public class StringHangComponent implements HangBarComponent {
    public StringHangComponent(HangBar bar,String data,String hang_id,Pos left_up,Display display){
        this(data, hang_id, left_up,display);
        bar.addComp(this);
    }
    public Pos r;
    public String data;
    public String hang_id;
    public Drawable SD;
    public StringHangComponent(String data, String hang_id, Pos left_up, Display display){
        this.r=left_up;
        this.data=data;
        this.hang_id=hang_id;
        genSD(display.getGraphic());
    }

    public void genSD(Graphics g){
        this.SD=new Drawable(new StringDrawable(data,getSizeRect(g),"hang_"+hang_id+"_SD"));
    }

    public Rect latest_r;
    @Override
    public Rect getSizeRect(Graphics g) {
        latest_r=StringDrawUtil.getStringBounds(g,g.getFont(),data).shift(r.x,r.y);
        return latest_r;
    }

    public Rect getSizeRect(){
        return latest_r;
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
        onClick(button);
    }

    @Override
    public void non_Hover(Pos pos) {
        data="non Hover";
    }

    @Override
    public void non_Click(Pos pos, int button) {
        data="non Click";
    }

    protected void onClick(int button){
        //SomeThing
    }
}
