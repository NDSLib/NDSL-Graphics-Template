package com.ndsl.graphics.templates.main;

import com.ndsl.graphics.display.Display;
import com.ndsl.graphics.display.drawable.base.Drawable;
import com.ndsl.graphics.pos.Rect;
import com.ndsl.graphics.templates.ui.bar.HangBar;
import com.ndsl.graphics.templates.ui.bar.StringHangComponent;

public class TemplateMain {
    public static void main(String[] args) {
        Display display=new Display("NDSL/Templates",3,new Rect(100,100,1000,1000));
        display.setDebugMode(true);
        HangBar hangBar=new HangBar("test_hang",new Rect(100,100,300,200),display);
        StringHangComponent SHC=new StringHangComponent(hangBar,"data","hang_id",new Rect(0,0,200,100));
        display.addDrawable(new Drawable(hangBar));
        while(true){
            if(display.limiter.onUpdate()) display.update();
        }
    }
}
