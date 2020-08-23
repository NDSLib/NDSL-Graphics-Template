package com.ndsl.graphics.templates.ui.bar

import com.ndsl.graphics.display.Display
import com.ndsl.graphics.display.drawable.IDrawable
import com.ndsl.graphics.display.drawable.base.DrawableUtil
import com.ndsl.graphics.display.mouse.MouseInputHandler
import com.ndsl.graphics.pos.Pos
import com.ndsl.graphics.pos.Rect
import java.awt.Graphics

class Hanger(var id: String, var r: Rect) : IDrawable {
    override fun getShowingRect(): Rect = r

    override fun isShowing(p0: Display?): Boolean = p0!!.isShowing(showingRect)

    override fun getID(): String = id

    override fun onDraw(p0: Graphics?, p1: Rect?) {
        if (p0 == null) return
        val pointer: Pos = Pos(r.left_up.x, r.left_up.y)
        for (hang in hangs) {
            hang.draw(p0, pointer)
            pointer.shift(hang.getSizeRect().width, 0)
        }
    }

    fun add(hang: Hangable) {
        hangs.add(hang)
    }

    var hangs: MutableList<Hangable> = mutableListOf()
}

interface Hangable {
    fun draw(g: Graphics, left_up: Pos)
    fun getID(): String
    fun getSizeRect(): Rect
}

abstract class BaseHangable(var mouseInput:MouseInputHandler, var r: Rect, var id: String) : Hangable {
    lateinit var collision: HookReleaseCollider

    override fun draw(g: Graphics, left_up: Pos) {
        r=Rect(left_up.x, left_up.y, left_up.x + r.width, left_up.y + r.height)
        if(!this::collision.isInitialized){collision= HookReleaseCollider(Runnable { onRelease() }, mouseInput, r)}
        onDraw(g,r)
    }

    fun onRelease() {
        onRelease(mouseInput.now_mouse_pos)
    }

    override fun getID(): String = id
    override fun getSizeRect(): Rect = r

    abstract fun onRelease(clickPoint: Pos)
    abstract fun onDraw(g: Graphics, r: Rect)
}

abstract class ButtonHangable(mouseInput: MouseInputHandler, r: Rect, id: String, var run: Runnable) : BaseHangable(mouseInput, r, id) {
    override fun onRelease(clickPoint: Pos) {
        run.run()
    }

    abstract override fun onDraw(g: Graphics, r: Rect)
}

class StringHangable(var data: String, var size: Int = 12, mouseInput: MouseInputHandler, r: Rect, id: String) : BaseHangable(mouseInput, r, id) {
    override fun onRelease(clickPoint: Pos) {}

    override fun onDraw(g: Graphics, r: Rect) {
        DrawableUtil.drawString(data, g, r, size)
    }
}

open class StringButtonHangable(var data: String, var size: Int = 12, mouseInput: MouseInputHandler, r: Rect, id: String, open var run: Runnable) : BaseHangable(mouseInput, r, id) {
    override fun onDraw(g: Graphics, r: Rect) {
        DrawableUtil.drawString(data, g, r, size)
    }

    override fun onRelease(clickPoint: Pos) {
        run.run()
    }
}