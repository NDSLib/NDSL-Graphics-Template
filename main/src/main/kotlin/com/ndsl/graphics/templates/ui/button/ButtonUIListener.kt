package com.ndsl.graphics.templates.ui.button

import com.ndsl.graphics.display.Display
import com.ndsl.graphics.display.drawable.non_sync.ui.MouseUIListener
import com.ndsl.graphics.display.mouse.CustomMouseEvent
import com.ndsl.graphics.pos.Rect

class ButtonUIListener(var button:Button) : MouseUIListener {
    override fun onHover(p0: CustomMouseEvent?) {
    }

    override fun onClick(p0: CustomMouseEvent?) {
    }

    override fun onDrug(p0: CustomMouseEvent?) {
    }

    override fun onDoubleClick(p0: CustomMouseEvent?) {
    }

    override fun onRelease(p0: CustomMouseEvent?) {
        button.handler.onRelease()
    }

    override fun getUIRect(p0: Display?): Rect {
        return button.showingRect
    }
}