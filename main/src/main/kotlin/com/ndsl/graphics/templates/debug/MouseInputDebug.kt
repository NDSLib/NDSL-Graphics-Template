package com.ndsl.graphics.templates.debug

import com.ndsl.graphics.display.Display
import com.ndsl.graphics.display.drawable.non_sync.ui.MouseUIListener
import com.ndsl.graphics.display.mouse.CustomMouseEvent
import com.ndsl.graphics.pos.Rect

class MouseInputDebug : MouseUIListener {
    override fun onHover(p0: CustomMouseEvent?) {
    }

    override fun onClick(p0: CustomMouseEvent?) {
        println("Click")
    }

    override fun onDrug(p0: CustomMouseEvent?) {
    }

    override fun onDoubleClick(p0: CustomMouseEvent?) {
    }

    override fun onRelease(p0: CustomMouseEvent?) {
        println("Release")
    }

    override fun getUIRect(p0: Display?): Rect {
        return Rect(0,0,0,0)
    }
}