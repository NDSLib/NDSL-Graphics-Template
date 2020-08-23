package com.ndsl.graphics.templates.util

import com.ndsl.graphics.pos.Rect
import java.awt.Graphics

fun Graphics?.fillRect(r: Rect){
    this?.fillRect(r.left_up.x,r.left_up.y,r.width,r.height)
}

fun Graphics?.drawRect(r:Rect){
    this?.drawRect(r.left_up.x,r.left_up.y,r.width,r.height)
}