package com.ndsl.graphics.templates.util;

import static java.lang.Math.*;

public class Easing {
    public static double EaseInSine(double value){
        return 1 - cos((value * PI) / 2);
    }

    public static double EaseOutSine(double value){
        return sin((value * PI) / 2);
    }

    public static double EaseInOutSine(double value){
        return -(cos(PI * value) - 1) / 2;
    }

    public static double EaseInQuad(double value){
        return value * value;
    }

    public static double EaseOutQuad(double value){
        return 1 - (1 - value) * (1 - value);
    }

    public static double EaseInOutQuad(double value){
        return value < 0.5 ? 2 * value * value : 1 - pow(-2 * value + 2, 2) / 2;
    }

    public static double EaseInCubic(double value){
        return value * value * value;
    }

    public static double EaseOutCubic(double value){
        return 1 - pow(1 - value, 3);
    }

    public static double EaseInOutCubic(double value){
        return value < 0.5 ? 4 * value * value * value : 1 - pow(-2 * value + 2, 3) / 2;
    }

    public static double EaseInQuart(double value){
        return value * value * value * value;
    }

    public static double EaseOutQuart(double value){
        return 1 - pow(1 - value, 4);
    }

    public static double EaseInOutQuart(double value){
        return value < 0.5 ? 8 * value * value * value * value : 1 - pow(-2 * value + 2, 4) / 2;
    }

    public static class EasingHandler{
        public static double cal(int easing_num,double value){
            switch(easing_num){
                case 1:
                    return EaseInSine(value);
                case 2:
                    return EaseOutSine(value);
                case 3:
                    return EaseInOutSine(value);
                case 4:
                    return EaseInQuad(value);
                case 5:
                    return EaseOutQuad(value);
                case 6:
                    return EaseInOutQuad(value);
                case 7:
                    return EaseInCubic(value);
                case 8:
                    return EaseOutCubic(value);
                case 9:
                    return EaseInOutCubic(value);
                case 10:
                    return EaseInQuart(value);
                case 11:
                    return EaseOutQuart(value);
                case 12:
                    return EaseInOutQuart(value);
            }
            return 0;
        }

        public static double cal(EasingType type,double value){
            return cal(type.Easing_num,value);
        }
    }
}
