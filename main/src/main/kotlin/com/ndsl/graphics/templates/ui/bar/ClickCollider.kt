package com.ndsl.graphics.templates.ui.bar

import com.ndsl.graphics.display.Display
import com.ndsl.graphics.display.drawable.non_sync.ui.MouseUIListener
import com.ndsl.graphics.display.mouse.CustomMouseEvent
import com.ndsl.graphics.display.mouse.MouseInputHandler
import com.ndsl.graphics.pos.Pos
import com.ndsl.graphics.pos.Rect

/**
 * this likes Button.
 * but non Drawable
 */
open class ClickCollider(var mouseInput: MouseInputHandler, r: Rect) {
//    var mouseInput: MouseInputHandler = display.mouseInputHandler
    var mouseUIListener: MouseUIListener = ColliderUIListener(r, this)

    init {
        mouseInput.register.add(mouseUIListener)
    }

    open fun onHover(e: CustomMouseEvent) {}
    open fun onClick(e: CustomMouseEvent) {}
    open fun onDrug(e: CustomMouseEvent) {}
    open fun onDoubleClick(e: CustomMouseEvent) {}
    open fun onRelease(e: CustomMouseEvent) {}

}

class ColliderUIListener(var r: Rect, var collider: ClickCollider) : MouseUIListener {
    override fun onHover(p0: CustomMouseEvent?) {
        if (p0 != null) {
            if (r.contain(Pos(p0.event.x, p0.event.y))) {
                collider.onHover(p0)
            }
        }
    }

    override fun onClick(p0: CustomMouseEvent?) {
        if (p0 != null) {
            if (r.contain(Pos(p0.event.x, p0.event.y))) {
                collider.onClick(p0)
            }
        }
    }

    override fun onDrug(p0: CustomMouseEvent?) {
        if (p0 != null) {
            if (r.contain(Pos(p0.event.x, p0.event.y))) {
                collider.onDrug(p0)
            }
        }
    }

    override fun onDoubleClick(p0: CustomMouseEvent?) {
        if (p0 != null) {
            if (r.contain(Pos(p0.event.x, p0.event.y))) {
                collider.onDoubleClick(p0)
            }
        }
    }

    override fun onRelease(p0: CustomMouseEvent?) {
        if (p0 != null) {
            if (r.contain(Pos(p0.event.x, p0.event.y))) {
                collider.onRelease(p0)
            }
        }
    }

    override fun getUIRect(p0: Display?): Rect = r

}


class HookClickCollider(mouseInput: MouseInputHandler, r:Rect, var run:Runnable) : ClickCollider(mouseInput,r) {
    override fun onClick(e: CustomMouseEvent) {
        run.run()
    }
}

class HookReleaseCollider(var run: Runnable, mouseInput: MouseInputHandler, r: Rect) : ClickCollider(mouseInput,r){
    override fun onRelease(e: CustomMouseEvent) {
        run.run()
    }
}