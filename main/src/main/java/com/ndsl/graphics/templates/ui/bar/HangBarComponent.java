package com.ndsl.graphics.templates.ui.bar;

import com.ndsl.graphics.pos.Pos;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface HangBarComponent {
    Rect getSizeRect(Graphics g);
    void onDraw(Graphics g,Rect showingRect);
    String getComponentID();
    void onHover(Pos pos);
    void onClick(Pos pos,int button);
    void non_Hover(Pos pos);
    void non_Click(Pos pos,int button);
}
