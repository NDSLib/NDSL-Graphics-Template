package com.ndsl.graphics.templates.util;

public enum EasingType {
    EaseInSine(1),
    EaseOutSine(2),
    EaseInOutSine(3),
    EaseInQuad(4),
    EaseOutQuad(5),
    EaseInOutQuad(6),
    EaseInCubic(7),
    EaseOutCubic(8),
    EaseInOutCubic(9),
    EaseInQuart(10),
    EaseOutQuart(11),
    EaseInOutQuart(12),;
    public int Easing_num;
    private EasingType(int i){
        this.Easing_num=i;
    }
}
