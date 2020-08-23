package com.ndsl.graphics.templates.ui.button;

import com.ndsl.graphics.GraphicsMain;
import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.IDrawable;
import com.ndsl.graphics.display.drawable.non_sync.ui.MouseUIListener;
import com.ndsl.graphics.display.mouse.MouseInputHandler;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Button implements IDrawable {
    public static final Color BACKGROUND_COLOR = new Color(210, 210, 210, 255);
    public static final Color RECT_COLOR = new Color(40, 40, 40, 255);
    public static final Color SKY_BLUE_COLOR = new Color(0, 101, 233);

    public List<ClickListener> listeners = new ArrayList<>();
    public Rect r;
    public String id;
    public IDrawable OnButton;
    public MouseHandler handler;

    public Button(Display display, Rect r, String id, IDrawable OnButton) {
        this.r = r;
        this.id = id;
        this.OnButton = OnButton;
        this.handler = new MouseHandler(display.mouseInputHandler);
    }

    @Override
    public void onDraw(Graphics graphics, Rect rect) {
//        System.out.println("Draw");
        drawBackground(graphics, rect);
        graphics.setColor(GraphicsMain.Default_Color);
        OnButton.onDraw(graphics, rect);
//        if (this.handler.isClicked()) {
//            onClick();
//        }
    }

    public Button addListener(ClickListener listener) {
        this.listeners.add(listener);
        return this;
    }

    protected void onClick() {
        for (ClickListener listener : listeners) {
            listener.onClick(this);
        }
    }

    protected void drawBackground(Graphics g, Rect r) {
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(r.left_up.x, r.left_up.y, r.getWidth(), r.getHeight());
        g.setColor(this.handler.isOver() ? SKY_BLUE_COLOR : RECT_COLOR);
        g.drawRect(r.left_up.x, r.left_up.y, r.getWidth(), r.getHeight());
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

    public class MouseHandler {
        public MouseInputHandler mouseInputHandler;

        public MouseHandler(MouseInputHandler mouseInputHandler) {
            this.mouseInputHandler = mouseInputHandler;
            MouseUIListener uiListener = new ButtonUIListener(Button.this);
            mouseInputHandler.register.add(uiListener);
        }

        public boolean isClicked() {
            return mouseInputHandler.isClicking && r.contain(mouseInputHandler.now_mouse_pos);
        }

        public boolean isReleased(){
            return mouseInputHandler.isClicking && r.contain(mouseInputHandler.now_mouse_pos);
        }

        public boolean isOver() {
            return r.contain(mouseInputHandler.now_mouse_pos);
        }

        public void onRelease(){
            Button.this.onClick();
        }
    }
}
