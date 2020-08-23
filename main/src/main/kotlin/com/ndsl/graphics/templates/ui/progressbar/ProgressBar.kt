package com.ndsl.graphics.templates.ui.progressbar

import com.ndsl.graphics.display.Display
import com.ndsl.graphics.display.drawable.IDrawable
import com.ndsl.graphics.pos.Rect
import java.awt.Color
import java.awt.Graphics
import kotlin.math.roundToInt

class ProgressBar(var r:Rect,var id:String) : IDrawable {
    override fun onDraw(p0: Graphics?, p1: Rect?) {
        p0?.color= backColor
        p0?.fillRect(r.left_up.x,r.left_up.y,r.width,r.height)
        val rr:Rect=cal(progress,r)
        p0?.color= defaultColor
        p0?.fillRect(rr.left_up.x,rr.left_up.y,rr.width,rr.height)
        p0?.color= wipeColor
        p0?.drawRect(r.left_up.x,r.left_up.y,r.width,r.height)
    }

    private fun cal(progress:Double,r:Rect):Rect = Rect(r.left_up.x,r.left_up.y, (r.left_up.x+(r.width * progress / 100)).roundToInt(),r.right_down.y)

    override fun getShowingRect(): Rect = r
    override fun isShowing(p0: Display?): Boolean = p0!!.isShowing(showingRect)
    override fun getID(): String = id

    var progress:Double=0.0

    companion object {
        val defaultColor:Color=Color(80,200,0)
        var wipeColor:Color=Color(90,90,90)
        var backColor:Color=Color(180,180,180)
    }
}