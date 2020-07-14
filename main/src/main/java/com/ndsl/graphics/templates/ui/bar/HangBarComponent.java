package com.ndsl.graphics.templates.ui.bar;

import com.ndsl.graphics.pos.Rect;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface HangBarComponent {
    Rect getSizeRect();
    void onDraw(Graphics g,Rect showingRect);
    String getComponentID();
    void onHover(MouseEvent e);
    void onClick(MouseEvent e);
}
